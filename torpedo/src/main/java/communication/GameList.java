package communication;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GameList {

	/**
	 * Lekéri a játékok listáját amibe a csapat csatlakozhat, és még nem csatlakozott, és visszaadja azt visszatérési értékként.
	 */
	public List<Long> getRunningGameIds() {
		Communication comm = new Communication();
		String response = comm.get("http://195.228.45.100:8080/jc16-srv/game");
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		communicationcheck(object);
		String IDs = object.get("game").toString();
		StringTokenizer st = new StringTokenizer(IDs, ",");
		ArrayList<Long> returnlist = new ArrayList<Long>();
		while (st.hasMoreElements()){
			returnlist.add(Long.parseLong(st.nextElement().toString()));
		}
		return returnlist;
		
	}

	/**
	 * Megadja, hogy a paraméterül kapott ID-jű játék létezik-e. True, ha létezik, false, ha nem.
	 * @param gameID
	 */
	public boolean isExistingGameID(long gameID) {
		Communication comm = new Communication();
		String response = comm.get("http://195.228.45.100:8080/jc16-srv/game");
		Gson parser = new Gson();
		JsonObject object = parser.fromJson(response,JsonObject.class);
		communicationcheck(object);
		String IDs = object.get("game").toString();
		StringTokenizer st = new StringTokenizer(IDs, ",");
		ArrayList<Long> lostofIDs = new ArrayList<Long>();
		while (st.hasMoreElements()){
			lostofIDs.add(Long.parseLong(st.nextElement().toString()));
		}
		for(int i=0;i<lostofIDs.size();i++){
			if (lostofIDs.get(i)== gameID )
				return true;
		}
		return false;
	}
	
	private void communicationcheck(JsonObject object){
		//TODO Ha Tibi megcsinálta a hiba osztályt ezeket a kommenteket ki kell törölni.
		//Addig azért van bent hogy ne legyen hiba a pushban.
		//if(object.get("code").toString() != "0")
			//throw new UnsupportedOperationException(Long.parseLong(object.get("code").toString()));
	}

}