package torpedo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import communication.CommException;
import communication.GameJoiner;
import communication.GameList;
import communication.OwnGameCreator;
import model.gameObjects.OwnSubmarineRefresher;
import model.gameObjects.Submarine;
import model.gameObjects.entities.SubmarineDataHolder;

/**
 * Modell tesztelésére létrehozott osztály
 * @author tibor
 *
 */
public class ModelTest {

	public static void main(String[] args) {
		OwnGameCreator creator = new OwnGameCreator();
		try {
			Gson gsonobject = new Gson();
			creator.createOwnGame();
			System.out.println(creator.getID());
			creator.createOwnGame();
			System.out.println(creator.getID());
			creator.createOwnGame();
			System.out.println(creator.getID());
			GameList list = new GameList();
			//GameJoiner joiner = new GameJoiner();
			joiner.joinToThisGame(creator.getID());
			List<Long> runninggames = list.getRunningGameIds();
			for (int i = 0; i < runninggames.size(); i++) {
				System.out.println("futó jatek " + runninggames.get(i));
			}
			System.out.println("Create Lefutott");
			System.out.println("Kapott ID:"+creator.getID());			
			
			OwnSubmarineRefresher subrefresher = new OwnSubmarineRefresher();
			List<SubmarineDataHolder> submarinesdata = subrefresher.refreshTheseSubmarines(creator.getID(), gsonobject);
			Submarine submarine = new Submarine(creator.getID(),gsonobject,submarinesdata.get(0));
			System.out.println("A submarine létrehozása sikeres");
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
	}

}
