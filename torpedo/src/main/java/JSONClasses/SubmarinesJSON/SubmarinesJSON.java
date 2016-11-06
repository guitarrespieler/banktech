
package JSONClasses.SubmarinesJSON;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SubmarinesJSON {

    @SerializedName("entities")
    @Expose
    private List<Entity> entities = new ArrayList<Entity>();
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private long code;

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
    public long getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(long code) {
        this.code = code;
    }

}
