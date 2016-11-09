/**
 * 
 */
package torpedo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import communication.CommException;
import communication.GameJoiner;
import communication.GameList;
import communication.OwnGameCreator;
import model.gameObjects.OwnSubmarineRefresher;
import model.gameObjects.Submarine;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameObjects.entities.SubmarineDataHolder;

/**
 * Modelltesztelésére létrehozott osztály by Beni.
 * 
 * @author raszt
 *
 */
public class ModelTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OwnGameCreator creator = new OwnGameCreator();
		try {
			Gson gsonobject = new Gson();
			creator.createOwnGame();
			System.out.println("Create Lefutott");
			System.out.println("Kapott ID:" + creator.getID());
			GameJoiner joiner = new GameJoiner();

			//System.out.println("Join Lefutott");
			OwnSubmarineRefresher subrefresher = new OwnSubmarineRefresher();
			List<SubmarineDataHolder> submarinesdata = subrefresher.refreshTheseSubmarines(creator.getID(), gsonobject);
			Submarine submarine = new Submarine(creator.getID(), gsonobject, submarinesdata.get(0));
			System.out.println("A submarine létrehozása sikeres");
			submarine.move(1.0, 10.0);
			System.out.println("A submarine mozgása sikeres");
			//submarine.shoot(10.0);
			System.out.println("A submarine shoot sikeres");
			Map<EntityType, List<EntityDataHolder>> entities =  submarine.usePassiveSonar();
			if(entities.containsKey(EntityType.Submarine))
				System.out.println("A submarine sonar lat egy hajot melynek ID-ja"+entities.get(EntityType.Submarine).get(0).getId());
			System.out.println("A submarine sonar sikeres");
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
