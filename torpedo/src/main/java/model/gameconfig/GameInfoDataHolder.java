	package model.gameconfig;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import communication.CommException;
import communication.Communication;
import model.gameObjects.Position;
import model.gameObjects.entities.SubmarineDataHolder;

public class GameInfoDataHolder {
	
	private static final String PRE_urltag = "/game/";

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("round")
    @Expose
    private int round;
    @SerializedName("scores")
    @Expose
    private JsonObject scores;
    @SerializedName("connectionStatus")
    @Expose
    private JsonObject connectionStatus;
    @SerializedName("mapConfiguration")
    @Expose
    private MapConfigDataHolder mapConfiguration;
    @SerializedName("status")
    @Expose
    private GameStatus status;

	/**
	 * Lekéri a GameInfo-t a szerverről és visszaadja az újat.
	 * @throws CommException 
	 */
	public static GameInfoDataHolder refreshGameInfoDataHolder(long GameID,Gson gsonRef) throws CommException{
		
		//Url tag összeállítása
		String URL_TAG = createUrlTag(GameID);
		
		//http kérés
		String jsonStr = Communication.get(URL_TAG);
		
		//parse-olás
		JsonObject job = gsonRef.fromJson(jsonStr, JsonObject.class);
		
		//hibaellenőrzés
		CommException.communicationcheck(job);
		
		//frissítés
		GameInfoDataHolder newData = createNewDataHolder(gsonRef, job);		
		
		return newData;
	}

	/**
	 * A régi dataHoldert az újra cseréli
	 * @return 
	 */
	private static GameInfoDataHolder createNewDataHolder(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("game");
		
		GameInfoDataHolder newDataHolder = gsonRef.fromJson(jes, GameInfoDataHolder.class);
		
		JsonObject mapdataJob = job.get("mapConfiguration").getAsJsonObject();
		
		JsonElement islands = mapdataJob.get("Positions");
		
		Type listType = new TypeToken<List<Position>>() {}.getType();

		List<Position> islandPositions = gsonRef.fromJson(islands,listType);
		
		newDataHolder.getMapConfiguration().setPositions(islandPositions);
		
		return newDataHolder;
	}

	/**
	 * Előállítja a http kéréshez szükséges url taget
	 * @param id
	 * @return
	 */
	private static String createUrlTag(long gameId) {
		return PRE_urltag + gameId;
	}
    
    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The round
     */
    public int getRound() {
        return round;
    }

    /**
     * 
     * @param round
     *     The round
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * 
     * @return
     *     Egy lista a meghívott csapatokból,
     *     amely kulcs-érték párokban tartalmazza,
     *     hogy melyik csapatnak hány pontja van.
     */
    public List<Entry<String, Double>> getScores() {
    	List<Entry<String,Double>> list = new ArrayList();
    	
    	JsonElement jel = connectionStatus.get("scores");
    	
    	JsonObject job = jel.getAsJsonObject();
    	
    	Set<Entry<String, JsonElement>> entryset = job.entrySet();
    	
    	/**
		 * Kulcs-érték pár:
		 * 		-kulcs: csapat neve
		 * 		-érték: pontszám
		 */
		Entry<String, Double> entry = null;
    	
    	for(Entry<String, JsonElement> JsonEntry: entryset){
    		entry = new AbstractMap.SimpleEntry(JsonEntry.getKey(), JsonEntry.getValue().getAsDouble());
    		list.add(entry);
    	}
        return list;
    }

    /**
     * 
     * @param JsonObject
     *     The JsonObject
     */
    public void setJsonObject(JsonObject scores) {
        this.scores = scores;
    }

    /**
     * 
     * @return egy lista a játékba meghívott csapatokról.
     * Az Entry-ben kulcs-érték párként szerepel
     * a csapat neve és hogy csatlakozott-e (true, ha csatlakozott,
     * false,ha nem.
     *     
     */
    public List<Entry<String, Boolean>> getConnectionStatus() {
    	List<Entry<String,Boolean>> list = new ArrayList();
    	
    	JsonElement jel = connectionStatus.get("connected");
    	
    	JsonObject job = jel.getAsJsonObject();
    	
    	Set<Entry<String, JsonElement>> entryset = job.entrySet();
    	
    	/**
		 * Kulcs-érték pár:
		 * 		-kulcs: csapat neve
		 * 		-érték: true:kapcsolódott
		 * 				false: nem kapcsolódott
		 */
		Entry<String, Boolean> entry = null;
    	
    	for(Entry<String, JsonElement> JsonEntry: entryset){
    		entry = new AbstractMap.SimpleEntry(JsonEntry.getKey(), JsonEntry.getValue().getAsBoolean());
    		list.add(entry);
    	}
        return list;
    }

    /**
     * 
     * @param connectionStatus
     *     The connectionStatus
     */
    public void setConnectionStatus(JsonObject connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    /**
     * 
     * @return
     *     The mapConfiguration
     */
    public MapConfigDataHolder getMapConfiguration() {
        return mapConfiguration;
    }

    /**
     * 
     * @param mapConfiguration
     *     The mapConfiguration
     */
    public void setMapConfiguration(MapConfigDataHolder mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    /**
     * 
     * @return
     *     The status
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

}
