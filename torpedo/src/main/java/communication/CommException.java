/**
 * 
 */
package communication;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonObject;

/**
 * @author tibor
 *
 */
public class CommException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private int errorCode = 0;
	
	/**
	 * Ebből a map-ből kinyerhető, hogy mely hibakódhoz milyen 
	 * hibaüzenet tartozik.
	 */
	public static Map<Integer,ErrorCodes> errors= new LinkedHashMap<Integer,ErrorCodes>(){
		private static final long serialVersionUID = 1L;

	{
		put(1,ErrorCodes.team_not_invited);
		put(2,ErrorCodes.already_running_game);
		put(3,ErrorCodes.gameID_not_exist);
		put(4,ErrorCodes.no_permission_to_manage_this_submarine);
		put(7,ErrorCodes.torpedo_cooling_down);
		put(8,ErrorCodes.call_before_reload);
		put(9,ErrorCodes.game_is_not_running);
		put(10,ErrorCodes.submarine_has_already_moved_in_this_round);
		put(11,ErrorCodes.too_much_acceleration);
		put(11,ErrorCodes.too_sharp_curve);		
	}};
	
	
	/**
	 * Paraméter: szervertől kapott errorCode
	 */
	public CommException(int errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return
	 * 		visszaadja a hibakódot
	 */
	public int getErrorCode(){
		return errorCode;
	}
	
	public static void communicationcheck(JsonObject object) throws CommException{
		if(object.get("code").toString() != "0")
			throw new CommException(Integer.parseInt(object.get("code").toString()));
	}
}
