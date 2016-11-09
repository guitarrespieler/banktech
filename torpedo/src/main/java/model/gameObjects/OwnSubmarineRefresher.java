/**
 * 
 */
package model.gameObjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	 * Lekéri a saját tengeralattjáróink értékeit
	 * és frissíti is azok adatait.
	 * @param submarineList a frissítendő tengeralattjárók listája
	 * @throws CommException 
	 */
	public static void refreshTheseSubmarines(long gameID,List<Submarine> submarineList, Gson gsonRef) throws CommException{
		//url tag elkészítése
		String URL_TAG = createUrlTag(gameID);
		//http kérés
		String jsonStr = Communication.get(URL_TAG);
		//json parse-olás
		JsonObject job = gsonRef.fromJson(jsonStr, JsonObject.class);
		//hibaellenőrzés
		CommException.communicationcheck(job);
		
		@SuppressWarnings("unchecked")
		List<SubmarineDataHolder> newSubmarineData = parseJson(gsonRef, job);
		
		
		refreshTheseSubmarines(submarineList, newSubmarineData);
	}

	/**
	 * Listát csinál az új infókból.
	 */
	private static List<SubmarineDataHolder> parseJson(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("submarines");
		
		//FIXME not sure about this one...
		@SuppressWarnings("unchecked")
		List<SubmarineDataHolder> newSubmarineData = gsonRef.fromJson(jes, (new ArrayList<SubmarineDataHolder>()).getClass());
		return newSubmarineData;
	}

	/**
	 * @param submarineList ezeket a hajók dataHoldereit frissíti
	 * @param newSubmarineData az ebben található értékekkel
	 */
	private static void refreshTheseSubmarines(List<Submarine> submarineList, List<SubmarineDataHolder> newSubmarineData) {
		for (SubmarineDataHolder newEntityDataHolder : newSubmarineData) {
			for (Submarine submarine : submarineList) {
				SubmarineDataHolder dataHolder = submarine.getDataHolder();
				
				if(dataHolder == null || newEntityDataHolder.equals(dataHolder)){
					submarine.refreshData(newEntityDataHolder);
				}
			}
		}
	}

	/**
	 * A http kéréshez szükséges URL_TAG-et állítja elő.
	 */
	private static String createUrlTag(long gameID) {
		return PRE_urltag + gameID + POST_urltag;
	}
}
