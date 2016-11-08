package communication;

import com.google.gson.Gson;

import model.gameconfig.Game;
import model.gameconfig.GameInfoJSON;
import model.gameconfig.MapConfiguration;

/**
 * Ez az osztály felelős a GameInfo szervertől való lekérdezéséért / frissítéséért,
 *  valamint azért, hogy ezeket az információkat a többi osztály rajta keresztül elérhesse.
 */
public class GameInfoRefresher {
	
	long gameID = 0L;
	public String URL_TAG = "/game/";
	
	Gson gsonRef;
	
	GameInfoJSON recievedJson;
	
	Game gameData;
	
	MapConfiguration mapconfigData;
	
	/**
	 * @param gameID - az aktuális játék ID-ja
	 * @param gsonObj - Gson parserre referencia
	 */
	public GameInfoRefresher(long gameID,Gson gsonObj){
		this.gameID = gameID;
		URL_TAG = URL_TAG + this.gameID; //hozzáfűzzük a TAG-hez az ID-t
		
		gsonRef = gsonObj;
	}

	/**
	 * Frissíti a GameInfo-t.
	 * @throws CommException 
	 */
	public void refreshData() throws CommException {
		String jsonStr = Communication.get(URL_TAG);
		
		recievedJson = gsonRef.fromJson(jsonStr, GameInfoJSON.class);
		
		int errorCode = recievedJson.getCode();
		if(errorCode > 0)
			throw new CommException(errorCode);
		
		gameData = recievedJson.getGame();
		mapconfigData = recievedJson.getGame().getMapConfiguration();
	}

	public Game getGameData() {
		return gameData;
	}

	public MapConfiguration getMapconfigData() {
		return mapconfigData;
	}
}