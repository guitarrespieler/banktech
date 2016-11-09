package model.gameconfig;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import communication.CommException;
import communication.Communication;

/**
 * Ez az osztály felelős a GameInfo szervertől való lekérdezéséért / frissítéséért,
 *  valamint azért, hogy ezeket az információkat a többi osztály rajta keresztül elérhesse.
 */
public class GameInfoRefresher {
	
	private static final String PRE_urltag = "game/";
	
	/**
	 * Frissíti a paraméterül kapott dataHolder objektumot
	 * @throws CommException 
	 */
	public static void refreshGameInfoDataHolder(GameInfoDataHolder oldDataHolder, Gson gsonRef) throws CommException{
		
		//Url tag összeállítása
		String URL_TAG = createUrlTag(oldDataHolder.getId());
		
		//http kérés
		String jsonStr = Communication.get(URL_TAG);
		
		//parse-olás
		JsonObject job = gsonRef.fromJson(jsonStr, JsonObject.class);
		
		//hibaellenőrzés
		CommException.communicationcheck(job);
		
		//frissítés
		oldDataHolder = createNewDataHolder(gsonRef, job);		
	}

	/**
	 * A régi dataHoldert az újra cseréli
	 * @return 
	 */
	private static GameInfoDataHolder createNewDataHolder(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("Game");
		
		GameInfoDataHolder newDataHolder = gsonRef.fromJson(jes, GameInfoDataHolder.class);
		
		return newDataHolder;
	}

	/**
	 * Előállítja a http kéréshez szükséges url taget
	 * @param id
	 * @return
	 */
	private static String createUrlTag(int id) {
		return PRE_urltag + id;
	}
}