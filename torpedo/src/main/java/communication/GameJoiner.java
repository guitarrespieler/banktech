package communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Játékhoz való csatlakozás kezeléséért felelős osztály.
 */
public class GameJoiner {

	private String URL_TAG = "game/";
	/**
	 * A paraméterül kapott ID-jű játékhoz csatlakoztató metódus. Visszatérési értékként megadja az ErrorCode-ot.
	 * @param gameID
	 * @throws CommException 
	 * @throws NumberFormatException 
	 */
	public void joinToThisGame(long gameID) throws NumberFormatException, CommException {
		String response = Communication.post(URL_TAG+gameID);
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		CommException.communicationcheck(object);
	}
}