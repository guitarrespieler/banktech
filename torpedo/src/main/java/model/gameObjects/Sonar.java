package model.gameObjects;

import java.io.IOException;
import java.net.ConnectException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import communication.CommException;
import communication.Communication;

public class Sonar {
	
	Gson gsonRef;

	/**
	 * Aktuális játék ID-je
	 */
	private long GameID = 0L;
	
	/**
	 * Tengeralattjáró ID-je
	 */
	private long submarineID = 0L;
	
	private String URL_TAG = "";
	
	/**
	 * Map kulcsa: entity típusa ( submarine / torpedo )
	 * érték: a környéken levő(észlelt), adott típusú entity-k listája
	 */
	private Map<EntityType,List<Entity>> entities = new LinkedHashMap<EntityType, List<Entity>>();	
	
	public Sonar(long GameID, long submarineID, Gson gsonObject){
		this.GameID = GameID;
		this.submarineID = submarineID;
		
		//URL TAG összeállítása
		this.URL_TAG = "/game/" + this.GameID +
					"/submarine/" + this.submarineID + "/sonar";
		
		this.gsonRef = gsonObject;
		
		//listák inicializálása
		this.entities.put(EntityType.Submarine, new LinkedList<Entity>());
		this.entities.put(EntityType.Torpedo, new LinkedList<Entity>());
	}
		
	/**
	 * Felderíti a környező hajókat és torpedókat. A szervertől kapott választ feldolgozza:
	 *  típus alapján készít a lista elemekből egy dataholder objektumot, majd a típus alapján
	 *   a submarines vagy torpedos listába elmenti.
	 * 
	 * Hiba esetén előre definiált hibakóddal exception-t dob.
	 * @throws CommException 
	 */
	public void scan() throws CommException {
		String JsonString = Communication.get(URL_TAG);
		
		processJsonStringToSubmarineJSON(JsonString);
		// TODO - implement Sonar.scan
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Bekapcsolja az aktív szonárt, ezáltal pár kör erejéig megnövekszik a szonár hatósugara.
	 *  Mellékhatása, hogy a hajónkat "az aktív szonár hatósugarával megegyező távolságról" észlelni tudja a többi hajó.
	 */
	public void activate(){
		String JsonString = Communication.post(URL_TAG);
		
		JsonObject job = gsonRef.fromJson(JsonString, JsonObject.class);
		
		//hibakezelés
		
	}
	/**
	 * Visszaadja az érzékelt tengeralattjárók listáját.
	 */
	public List<Entity> getNearbySubmarines() {
		return entities.get(EntityType.Submarine);
	}

	/**
	 * Visszaadja az érzékelt torpedók listáját.
	 */
	public List<Entity> getNearbyTorpedos() {
		return entities.get(EntityType.Torpedo);
	}
	
	/**
	 * feldolgozza a paraméterül kapott Json stringet.
	 * @throws CommException 
	 */
	private void processJsonStringToSubmarineJSON(String jsonString) throws CommException {
		SubmarinesJSON parsedJson = gsonRef.fromJson(jsonString, SubmarinesJSON.class);
		
		int errorCode = parsedJson.getCode();
		if(errorCode > 0) throw new CommException(errorCode);
		
		//szétszedjük a különböző listákba a különböző típusú entity-ket
		sortEntities(parsedJson.getEntities());
	}

	/**
	 *	Szétosztja a kapott entity-ket a különböző listákba. 
	 */
	private void sortEntities(List<Entity> list) {
		for (Entity entity : list) {
			if(EntityType.Submarine.equals(entity.getType())){
				entities.get(EntityType.Submarine).add(entity);				
			}else{
				entities.get(EntityType.Torpedo).add(entity);
			}
		}
	}
}