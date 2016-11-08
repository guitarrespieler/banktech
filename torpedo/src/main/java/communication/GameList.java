package communication;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GameList {
	
	String URL_TAG = "/game";

	/**
	 * Lekéri a játékok listáját amibe a csapat csatlakozhat, és még nem csatlakozott, és visszaadja azt visszatérési értékként.
	 * @throws CommException 
	 * @throws NumberFormatException 
	 */
	public List<Long> getRunningGameIds() throws NumberFormatException, CommException {
		Communication comm = new Communication();
		String response = comm.get(URL_TAG);
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
	 * @throws CommException 
	 * @throws NumberFormatException 
	 */
	public boolean isExistingGameID(long gameID) throws NumberFormatException, CommException {
		String response = Communication.get(URL_TAG);
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
	
	private void communicationcheck(JsonObject object) throws NumberFormatException, CommException{
		if(object.get("code").toString() != "0")
			throw new CommException(Integer.parseInt(object.get("code").toString()));
	}

}