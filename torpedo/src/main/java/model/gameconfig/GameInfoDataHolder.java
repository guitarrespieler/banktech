package model.gameconfig;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HandshakeCompletedEvent;

import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameInfoDataHolder {

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
