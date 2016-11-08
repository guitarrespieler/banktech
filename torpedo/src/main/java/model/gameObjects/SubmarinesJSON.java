
package model.gameObjects;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SubmarinesJSON {

	/**
	 * Amikor a szonár kérdezi le a környezet adatait,
	 * akkor a tengeralattjárók és torpedók értékei 
	 * ebbe a listába mentődnek.
	 */
	@SerializedName("entities")
    @Expose
    private List<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * Amikor a saját tengeralattjáróink értékét kérdezzük le
	 * (Submarines http kérés), akkor ebbe a listába fognak mentődni
	 * az adatok.
	 */
	@SerializedName("submarines")
    @Expose
    private List<Entity> submarines = new ArrayList<Entity>();
	
    @SerializedName("message")
    @Expose
    private String message;
    
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * 
     * @return
     *     The entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * 
     * @param entities
     *     The entities
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
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

	public List<Entity> getSubmarines() {
		return submarines;
	}

	public void setSubmarines(List<Entity> submarines) {
		this.submarines = submarines;
	}

}
