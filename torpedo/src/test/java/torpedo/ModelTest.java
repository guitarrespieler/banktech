package torpedo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import communication.CommException;
import communication.GameJoiner;
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
			System.out.println("Create Lefutott");
			System.out.println("Kapott ID:"+creator.getID());
			GameJoiner joiner = new GameJoiner();
			//joiner.joinToThisGame(creator.getID());
			System.out.println("Join Lefutott");
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
