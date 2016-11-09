package torpedo;

import java.io.IOException;

import communication.CommException;
import communication.GameJoiner;
import communication.OwnGameCreator;
import model.gameObjects.Submarine;

/**
 * Modell tesztelésére létrehozott osztály
 * @author tibor
 *
 */
public class ModelTest {

	public static void main(String[] args) {
		OwnGameCreator creator = new OwnGameCreator();
		try {
			creator.createOwnGame();
			System.out.println("Create Lefutott");
			System.out.println("Kapott ID:"+creator.getID());
			GameJoiner joiner = new GameJoiner();
			joiner.joinToThisGame(creator.getID());
			System.out.println("Join Lefutott");
			Submarine submarine = new Submarine(submarineData, creator.getID(), gsonObject)
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
