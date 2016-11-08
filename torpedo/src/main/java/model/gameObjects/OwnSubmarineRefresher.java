/**
 * 
 */
package model.gameObjects;

import java.util.List;

import com.google.gson.Gson;

import communication.CommException;
import communication.Communication;

/**
 * A tengeralattjárók adatainak frissítéséhez
 * szükséges http kérés lebonyolításáért felelős.
 * 
 * @author tibor
 */
public class OwnSubmarineRefresher {

	private static final String PRE_urltag = "/game/";
	private static final String POST_urltag = "/submarine";
	
	/**
	 * Lekéri a saját tengeralattjáróink értékeit
	 * és frissíti is azok adatait.
	 * @param submarineList a frissítendő tengeralattjárók listája
	 * @throws CommException 
	 */
	public static void refreshTheseSubmarines(long gameID,List<Submarine> submarineList, Gson gsonRef) throws CommException{
		String URL_TAG = createUrlTag(gameID);
		
		String jsonStr = Communication.get(URL_TAG);
		
		SubmarinesJSON job = gsonRef.fromJson(jsonStr, SubmarinesJSON.class);
		
		int errorCode = job.getCode();		
		if(errorCode > 0) throw new CommException(errorCode);
		
		refreshTheseSubmarines(submarineList, job);
	}

	/**
	 * @param submarineList ezeket a hajók dataHoldereit frissíti
	 * @param job az ebben található értékekkel
	 */
	private static void refreshTheseSubmarines(List<Submarine> submarineList, SubmarinesJSON job) {
		for (Entity newEntityDataHolder : job.getSubmarines()) {
			for (Submarine submarine : submarineList) {
				if(newEntityDataHolder.equals(submarine.getDataHolder())){
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
