package communication;

import model.gameconfig.Game;
import model.gameconfig.GameInfoJSON;
import model.gameconfig.MapConfiguration;

/**
 * Ez az osztály felelős a GameInfo szervertől való lekérdezéséért / frissítéséért, valamint azért, hogy ezeket az információkat a többi osztály rajta keresztül elérhesse.
 */
public class GameInfoRefresher {
	
	GameInfoJSON recievedJson;
	
	Game gameData;
	
	MapConfiguration mapconfigData;

	/**
	 * Frissíti a GameInfo-t.
	 */
	public void refreshData() {
		// TODO - implement GameInfoRefresher.refreshData
		throw new UnsupportedOperationException();
	}

	/**
	 * A kapott Stringből GameInfo objektumot készít.
	 * @param JsonString
	 */
	private GameInfoJSON parseJSONtoObject(String JsonString) {
		// TODO - implement GameInfoRefresher.parseJSONtoObject
		throw new UnsupportedOperationException();
	}

}