package model.gameObjects;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import communication.CommException;
import communication.Communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

public class Submarine {
	
	/**
	 * Itt tároljuk a tengeralattjárónkhoz tartozó adatokat.
	 */
	private Entity dataHolder;
	
	private Sonar sonar;
	
	private Gson gsonRef;

	private long gameID;
	
	private String URL_TAG="/game";
	
	public Submarine(Entity submarineData,long gameID,Gson gsonObject){
		dataHolder = submarineData;
		
		this.gameID = gameID;
		
		gsonRef = gsonObject;
		
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
		String response = Communication.postwithparams(URL_TAG+gameID+"/submarine"+ dataHolder.getId()+"/move",urlParameters);
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
		String response = Communication.postwithparams(URL_TAG+gameID+"/submarine"+ dataHolder.getId()+"/move",urlParameters);
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		CommException.communicationcheck(object);
	}
	
	private List<NameValuePair> shootparameterlistparse(Double turnAngle) {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("angle", turnAngle.toString() ));
		return urlParameters;
	}

	public void usePassiveSonar() throws CommException {
		sonar.scan();
	}

	public void activateSonar() throws CommException {
		sonar.activate();
	}
	
	/**
	 * A dataHolder-t a paraméterül kapottra frissíti.
	 */
	public void refreshData(Entity submarineData){
		this.dataHolder = submarineData;
	}
	
	public Entity getDataHolder(){
		return dataHolder;
	}
}