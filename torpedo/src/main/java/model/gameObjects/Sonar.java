package model.gameObjects;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import communication.CommException;
import communication.Communication;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameObjects.entities.SubmarineDataHolder;

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
	private Map<EntityType,List<EntityDataHolder>> entities = new LinkedHashMap<EntityType, List<EntityDataHolder>>();	
	
	public Sonar(long GameID, long submarineID, Gson gsonObject){
		this.GameID = GameID;
		this.submarineID = submarineID;
		
		//URL TAG összeállítása
		this.URL_TAG = "game/" + this.GameID +
					"/submarine/" + this.submarineID + "/sonar/";
		
		this.gsonRef = gsonObject;
		
		//listák inicializálása
		this.entities.put(EntityType.Submarine, new LinkedList<EntityDataHolder>());
		this.entities.put(EntityType.Torpedo, new LinkedList<EntityDataHolder>());
	}
		
	/**
	 * Felderíti a környező hajókat és torpedókat. A szervertől kapott választ feldolgozza:
	 *  típus alapján készít a lista elemekből egy dataholder objektumot, majd a típus alapján
	 *   a submarines vagy torpedos listába elmenti.
	 * 
	 * Hiba esetén előre definiált hibakóddal exception-t dob.
	 * @return egy map, amelyben Entity típusonként van lista
	 * az érzékelt Entity objektumokról (torpedókról, tengeralattjárókról)
	 * @throws CommException 
	 */
	public Map<EntityType, List<EntityDataHolder>> scan() throws CommException {
		String JsonString = Communication.get(URL_TAG);
		
		processJsonStringToSubmarineJSON(JsonString);
		
		return entities;
	}
	
	/**
	 * Bekapcsolja az aktív szonárt, ezáltal pár kör erejéig megnövekszik a szonár hatósugara.
	 *  Mellékhatása, hogy a hajónkat "az aktív szonár hatósugarával megegyező távolságról" észlelni tudja a többi hajó.
	 * @throws CommException 
	 * @throws NumberFormatException 
	 */
	public void activate() throws CommException{
		String JsonString = Communication.post(URL_TAG);
		
		JsonObject job = gsonRef.fromJson(JsonString, JsonObject.class);
		
		CommException.communicationcheck(job);		
	}	
	
	/**
	 * feldolgozza a paraméterül kapott Json stringet.
	 * @throws CommException 
	 */
	private void processJsonStringToSubmarineJSON(String jsonString) throws CommException {
		//parse-olás
		JsonObject job = gsonRef.fromJson(jsonString, JsonObject.class);
		//hibaellenőrzés
		CommException.communicationcheck(job);
		
		@SuppressWarnings("unchecked")
		List<EntityDataHolder> entityDataHolderList = parseJson(job);

		
		//szétszedjük a különböző listákba a különböző típusú entity-ket
		sortEntities(entityDataHolderList);
	}

	/**
	 * Listát csinál a beolvasott adatokból.
	 * FIXME: not sure about that.
	 */
	private List<EntityDataHolder> parseJson(JsonObject job) {
		JsonElement jes = job.get("entities");
		
		Type listType = new TypeToken<List<EntityDataHolder>>() {}.getType();

		List<EntityDataHolder> newEntitiesData = gsonRef.fromJson(jes,listType);
		
		return newEntitiesData;
	}

	/**
	 *	Szétosztja a kapott entity-ket a különböző listákba. 
	 */
	private void sortEntities(List<EntityDataHolder> list) {
		for(int i = 0; i < list.size(); i++){
			EntityDataHolder entity = list.get(i);
			if(EntityType.Submarine.equals(entity.getType())){
				List<EntityDataHolder> subList = entities.get(EntityType.Submarine);
				if (subList == null) {
					return;
				}
				subList.clear();
				subList.add(entity);				
			}else{
				List<EntityDataHolder> torpedoList = entities.get(EntityType.Torpedo);
				if(torpedoList == null)
					return;
				torpedoList.clear();
				torpedoList.add(entity);
			}
		}
	}
}