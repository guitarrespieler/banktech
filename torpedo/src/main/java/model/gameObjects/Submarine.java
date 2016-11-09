package model.gameObjects;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import communication.CommException;
import communication.Communication;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;

import java.util.ArrayList;
import org.apache.http.NameValuePair;

public class Submarine {
	
	/**
	 * Itt tároljuk a tengeralattjárónkhoz tartozó adatokat.
	 */
	private SubmarineDataHolder dataHolder = null;
	
	private Sonar sonar;
	
	private Gson gsonRef;

	private long gameID;

	/**
	 * Kulcs: milyen Entity (torpedó vagy tengeralattjáró)
	 * Érték: Lista az érzékelt objektumokból
	 */
	private Map<EntityType, List<EntityDataHolder>> scannedEntities;
	
	private String URL_TAG="game/";
	
	public Submarine(long gameID,Gson gsonObject,SubmarineDataHolder data){		
		this.gameID = gameID;
		gsonRef = gsonObject;
		dataHolder = data;
		sonar = new Sonar(this.gameID, dataHolder.getId(), gsonRef);
	}

	/**
	 * A tengeralattjárót mozgathatjuk vele. Paraméterül megadhatjuk neki az elfordulás szögét és a gyorsítás / lassítás mértékét ( tehát nem a kívánt sebességre állítjuk, hanem növeljük vagy csökkentjük azt!)
	 * @param speed
	 * @param turnAngle
	 * @throws CommException 
	 */
	public void move(Double speed, Double turnAngle) throws CommException {
		List<NameValuePair> urlParameters = speedparameterlistparse(speed, turnAngle);
		String response = Communication.postwithparams(URL_TAG+gameID+"/submarine"+"/" +dataHolder.getId()+"/move",urlParameters);
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		CommException.communicationcheck(object);
	}

	private List<NameValuePair> speedparameterlistparse(Double speed, Double turnAngle) {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("speed", speed.toString()));
		urlParameters.add(new BasicNameValuePair("turn", turnAngle.toString()));
		return urlParameters;
	}

	/**
	 * A megadott irány felé indít egy torpedót. A megadott irány fok-ban értendő, ahol a:  
	 * Keleti irány a 0 fok, Északi 90, Nyugati 180, Déli 270.  
	 *     
	 * Irányt, szám érték megadásával állíthatunk:  
	 * angle: 90.0 (Körök végi kiértékeléskor, a megadott irányba torpedót indít)  
	 * @param shootAngle
	 * @throws CommException 
	 */
	public void shoot(double shootAngle) throws CommException {
		List<NameValuePair> urlParameters = shootparameterlistparse(shootAngle);
		String response = Communication.postwithparams(URL_TAG+"/"+gameID+"/submarine"+"/"+ dataHolder.getId()+"/move",urlParameters);
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		CommException.communicationcheck(object);
	}
	
	private List<NameValuePair> shootparameterlistparse(Double turnAngle) {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("angle", turnAngle.toString() ));
		return urlParameters;
	}

	public Map<EntityType, List<EntityDataHolder>> usePassiveSonar() throws CommException {
		this.setScannedEntities(sonar.scan());
		
		return this.getScannedEntities();
	}

	public void activateSonar() throws CommException {
		sonar.activate();
	}
	
	/**
	 * A dataHolder-t a paraméterül kapottra frissíti.
	 */
	public void refreshData(SubmarineDataHolder submarineData){
		this.dataHolder = submarineData;
	}
	
	public SubmarineDataHolder getDataHolder(){
		return dataHolder;
	}

	public Map<EntityType, List<EntityDataHolder>> getScannedEntities() {
		//biztos, ami biztos, kezeljük le itt.
		if(scannedEntities == null){
			scannedEntities = new LinkedHashMap<EntityType, List<EntityDataHolder>>();
			scannedEntities.put(EntityType.Submarine, new LinkedList<EntityDataHolder>());
			scannedEntities.put(EntityType.Torpedo, new LinkedList<EntityDataHolder>());
		}			
		
		return scannedEntities;
	}

	private void setScannedEntities(Map<EntityType, List<EntityDataHolder>> scannedEntities) {
		if(scannedEntities == null)
			return;
		if(!scannedEntities.containsKey(EntityType.Submarine)
				|| !scannedEntities.containsKey(EntityType.Torpedo))
			return;
		if(scannedEntities.get(EntityType.Submarine) == null 
				|| scannedEntities.get(EntityType.Torpedo) == null)
			return;
		
		this.scannedEntities = scannedEntities;
	}
}