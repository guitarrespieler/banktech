
package JSONClasses.GameInfoJSON;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GameInfoJSON {

    @SerializedName("game")
    @Expose
    private Game game;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * 
     * @return
     *     The game
     */
    public Game getGame() {
        return game;
    }

    /**
     * 
     * @param game
     *     The game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The code
     */
    public int getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(int code) {
        this.code = code;
    }

}
