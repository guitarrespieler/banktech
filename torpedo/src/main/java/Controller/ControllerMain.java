package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import communication.CommException;
import communication.GameJoiner;
import communication.GameList;
import communication.OwnGameCreator;
import gui.Frame;
import model.gameObjects.OwnSubmarineRefresher;
import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.GameInfoDataHolder;

public class ControllerMain {
	
	private OwnGameCreator gameCreator;
	private Gson gsonobject;
	private GameInfoDataHolder gameInfo;
	private List<Submarine> ownSubmarines = new ArrayList<Submarine>();
	private Map<Submarine,Map<EntityType, List<EntityDataHolder>>> subMarinesSonarData;
	private Frame gameFrame;
	
	public ControllerMain() {
		gameCreator = new OwnGameCreator();
		gsonobject = new Gson();
	}

	public static void main(String[] args) {
		ControllerMain cM = new ControllerMain();
		try {
			cM.startGame();
			cM.refreshAll();
			long lastRound=cM.gameInfo.getRound();
			cM.gameFrame = new Frame(cM.gameInfo.getMapConfiguration());
			while(cM.ownSubmarines.size()>0 && (cM.gameInfo.getRound()!=cM.gameInfo.getMapConfiguration().getRounds()))
			{
				cM.refreshActualGameInfo();
				int currentRound = cM.gameInfo.getRound();
				//System.out.println("Max rounds: "+cM.gameInfo.getMapConfiguration().getRounds()+" Last Round: "+lastRound+" Current Round"+currentRound);
				if(lastRound!=currentRound)
				{
					cM.refreshAll();
					lastRound=cM.gameInfo.getRound();
					
					cM.moveSubmarines();					
					
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
	 * Létrhoz egy játékot, ha csatlakoznia kell hozzá csatlakozik.
	 * @throws IOException 
	 * @throws CommException 
	 * @throws NumberFormatException 
	 */
	private void startGame() throws NumberFormatException, CommException, IOException
	{
		
		gameCreator.createOwnGame();
		
		System.out.println("Create Lefutott");
		System.out.println("Kapott ID:" + gameCreator.getID());
		GameJoiner joiner = new GameJoiner();
		GameInfoDataHolder info = GameInfoDataHolder.refreshGameInfoDataHolder(gameCreator.getID(), gsonobject);
		
		if(GameList.getRunningGameIds().contains((gameCreator.getID())))
		{
					joiner.joinToThisGame(gameCreator.getID());
					System.out.println("Join Lefutott");
		}		
		
	}
	
	private void refreshAll() throws CommException
	{
		refreshActualGameInfo();
		refreshOwnSubmarines();
	}
	
	private void refreshActualGameInfo() throws CommException
	{
		gameInfo= GameInfoDataHolder.refreshGameInfoDataHolder(gameCreator.getID(), gsonobject);
	}
	
	private void refreshOwnSubmarines() throws CommException
	{	
		ownSubmarines.clear();
		OwnSubmarineRefresher subrefresher = new OwnSubmarineRefresher();
		List<SubmarineDataHolder> submarinesdata = subrefresher.refreshTheseSubmarines(gameCreator.getID(), gsonobject);
		
		for (SubmarineDataHolder submarineDataHolder : submarinesdata) {
			ownSubmarines.add(new Submarine(gameCreator.getID(), gsonobject, submarineDataHolder));
		}
	}
	
	private void refreshSonarDataForSubmarines() throws CommException
	{
		for(Submarine submarine:ownSubmarines)
		{
			if(submarine.getDataHolder().getSonarCooldown()==0)
			{				
				subMarinesSonarData.put(submarine, submarine.usePassiveSonar());
			}					
			
		}
	}
	
	private void moveSubmarines() throws CommException
	{
		for(Submarine submarine : ownSubmarines)
		{
			
			SubmarineController.moveSubmarine(submarine,gameInfo);
		}
	}
	
	private void drawGame()
	{
		//Frame gameFrame = new Frame(gameInfo.getMapConfiguration());
		List<SubmarineDataHolder> ownSubmarinesData = new ArrayList<SubmarineDataHolder>();
		for(Submarine submarine : ownSubmarines)
		{
			ownSubmarinesData.add(submarine.getDataHolder());
		}
		
		gameFrame.paintOwnSubmarines(ownSubmarinesData);
	}
	

}
