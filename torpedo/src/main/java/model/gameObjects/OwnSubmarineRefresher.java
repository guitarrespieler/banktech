/**
 * 
 */
package model.gameObjects;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import communication.CommException;
import communication.Communication;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.SubmarineDataHolder;

/**
 * A tengeralattjárók adatainak frissítéséhez
 * szükséges http kérés lebonyolításáért felelős.
 * 
 * @author tibor
 */
public class OwnSubmarineRefresher {

	private static final String PRE_urltag = "game/";
	private static final String POST_urltag = "submarine/";
	
	/**
	 * Lekéri a saját tengeralattjáróink értékeit és visszatérési értékként megadja az újat.
	 * @param submarineList a frissítendő tengeralattjárók listája
	 * @throws CommException 
	 */
	public static List<SubmarineDataHolder> refreshTheseSubmarines(long gameID, Gson gsonRef) throws CommException{
		//url tag elkészítése
		String URL_TAG = createUrlTag(gameID);
		//http kérés
		String jsonStr = Communication.get(URL_TAG);
		//json parse-olás
		JsonObject job = gsonRef.fromJson(jsonStr, JsonObject.class);
		//hibaellenőrzés
		CommException.communicationcheck(job);

		List<SubmarineDataHolder> newSubmarineData = parseJson(gsonRef, job);
		
		return newSubmarineData;
	}

	/**
	 * Listát csinál az új infókból.
	 */
	private static List<SubmarineDataHolder> parseJson(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("submarines");
		
		Type listType = new TypeToken<List<SubmarineDataHolder>>() {}.getType();

		List<SubmarineDataHolder> newSubmarineData = gsonRef.fromJson(jes,listType);
		return newSubmarineData;
	}

	/**
	 * A http kéréshez szükséges URL_TAG-et állítja elő.
	 */
	private static String createUrlTag(long gameID) {
		return PRE_urltag + gameID + "/"+ POST_urltag;
	}
}
