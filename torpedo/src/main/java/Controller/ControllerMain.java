package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import communication.CommException;
import communication.Communication;
import communication.GameJoiner;
import communication.GameList;
import communication.OwnGameCreator;
import gui.Frame;
import model.gameObjects.OwnSubmarineRefresher;
import model.gameObjects.Submarine;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.GameInfoDataHolder;
import model.gameconfig.GameStatus;

public class ControllerMain {

	private OwnGameCreator gameCreator;
	private Gson gsonobject;
	private GameInfoDataHolder gameInfo;
	private List<Submarine> ownSubmarines = new ArrayList<Submarine>();
	private Map<Submarine, Map<EntityType, List<EntityDataHolder>>> subMarinesSonarData;
	private Frame gameFrame;

	public ControllerMain() {
		gameCreator = new OwnGameCreator();
		gsonobject = new Gson();

		subMarinesSonarData = new HashMap<Submarine, Map<EntityType, List<EntityDataHolder>>>();
	}

	public static void main(String[] args) {
		
		ControllerMain cM = new ControllerMain();
		Communication.mainURL="http://"+args[0]+"/jc16-srv/";
		try {
			cM.startGame();
			cM.refreshAll();
			long lastRound = cM.gameInfo.getRound();
			cM.gameFrame = new Frame(cM.gameInfo.getMapConfiguration());

			// Control-loop
			while (cM.ownSubmarines.size() > 0
					&& (cM.gameInfo.getRound() != cM.gameInfo.getMapConfiguration().getRounds())) {
				try{
					int currentRound = cM.gameInfo.getRound();
					System.out.println("Max rounds: " + cM.gameInfo.getMapConfiguration().getRounds() + " Last Round: "
							+ lastRound + "\t Current Round: " + currentRound);
					if (lastRound != currentRound) {
						cM.refreshAll();
						lastRound = cM.gameInfo.getRound();
						
						cM.refreshSonarDataForSubmarines();
						
						cM.gameFrame.paintTheseEnemies(cM.subMarinesSonarData);
						
						cM.moveSubmarines();
	
						cM.shootTorpedos();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				cM.refreshActualGameInfo();
				cM.drawGame();
			}

			cM.drawGame();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(CommException.errors.get(e.getErrorCode()).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Vege");

	}

	/**
	 * Használja a szonárt, majd lő egyet valamerre.
	 */
	private void shootTorpedos() {
		for (Submarine submarine : ownSubmarines) {
			try {
				if (submarine.getDataHolder().getTorpedoCooldown() > 0)
					continue;
				submarine.shoot(Shooter.angleToShoot(submarine, subMarinesSonarData.get(submarine)));
			} catch (CommException e) {
				System.out.println(e.errors.get(e.getErrorCode()));
				e.printStackTrace();
			}
		}

	}

	/**
	 * Létrhoz egy játékot, ha csatlakoznia kell hozzá csatlakozik.
	 * 
	 * @throws IOException
	 * @throws CommException
	 * @throws NumberFormatException
	 */
	private void startGame() throws NumberFormatException, CommException, IOException {

		gameCreator.createOwnGame();

		System.out.println("Create Lefutott");
		System.out.println("Kapott ID:" + gameCreator.getID());
		GameJoiner joiner = new GameJoiner();
		GameInfoDataHolder info = GameInfoDataHolder.refreshGameInfoDataHolder(gameCreator.getID(), gsonobject);

		if (GameList.getRunningGameIds().contains((gameCreator.getID()))) {
			try {
				joiner.joinToThisGame(gameCreator.getID());
				System.out.println("Join Lefutott");
			} catch (CommException e) {
				if (e.getErrorCode() == 2) {
					List<Long> gameids = GameList.getRunningGameIds();

					for (Long gameId : gameids) {
						GameInfoDataHolder gameData = GameInfoDataHolder.refreshGameInfoDataHolder(gameId, gsonobject);
						if (gameData.getStatus().equals(GameStatus.WAITING)) {
							joiner.joinToThisGame(gameId);
						}
					}
				}
			}
		}

	}

	private void refreshAll() throws CommException {
		refreshActualGameInfo();
		refreshOwnSubmarines();
	}

	private void refreshActualGameInfo() throws CommException {
		gameInfo = GameInfoDataHolder.refreshGameInfoDataHolder(gameCreator.getID(), gsonobject);
	}

	private void refreshOwnSubmarines() throws CommException {
		ownSubmarines.clear();
		OwnSubmarineRefresher subrefresher = new OwnSubmarineRefresher();
		List<SubmarineDataHolder> submarinesdata = subrefresher.refreshTheseSubmarines(gameCreator.getID(), gsonobject);

		for (SubmarineDataHolder submarineDataHolder : submarinesdata) {
			ownSubmarines.add(new Submarine(gameCreator.getID(), gsonobject, submarineDataHolder));
		}
	}

	private void refreshSonarDataForSubmarines() throws CommException {
		for(int i = 0; i < ownSubmarines.size(); i++){
			Submarine submarine = ownSubmarines.get(i);
			if (submarine.getDataHolder().getSonarCooldown() == 0) {
				if (subMarinesSonarData.containsKey(submarine))
					subMarinesSonarData.get(submarine).clear();
				subMarinesSonarData.put(submarine, submarine.usePassiveSonar());
			}
		}
	}

	private void moveSubmarines() {
		for (Submarine submarine : ownSubmarines) {

			try {
				SubmarineController.moveSubmarine(submarine, gameInfo);
			} catch (CommException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void drawGame() {
		// Frame gameFrame = new Frame(gameInfo.getMapConfiguration());
		List<SubmarineDataHolder> ownSubmarinesData = new ArrayList<SubmarineDataHolder>();
		for (Submarine submarine : ownSubmarines) {
			ownSubmarinesData.add(submarine.getDataHolder());
		}

		gameFrame.paintOwnSubmarines(ownSubmarinesData);
	}

}
