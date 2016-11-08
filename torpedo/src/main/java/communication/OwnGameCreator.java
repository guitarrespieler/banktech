package communication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 * Új saját játék létrehozásáért felelős osztály.
 * Létrehoz egy új játékot a csapat számára. A létrehozott játék azonosítója a válasz "id" mezőjében található.  
 * Egyszerre egy csapat csak egy játékot hozhat létre. Ha már létezik játék akkor annak az azonosítóját adja vissza
 */
public class OwnGameCreator {

	/**
	 * Ebben az attribútumban mentjük el a létrehozott játék ID-ját.
	 */
	private long ID = 0;
	/**
	 * Ezen a címen érjük el a szerver oldalt.
	 */
	private static String URL_TAG = "game";

	/**
	 * Saját játékot hoz létre. Az új játék ID-ját elmenti.
	 */
	public void createOwnGame() {
		Communication comm = new Communication();
		String response = comm.post("http://server-adress:port/jc16-srv/game");
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		ID = Long.parseLong(object.get("id").toString());
		throw new UnsupportedOperationException();
	}
	
	public long getID() {
		return this.ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

}