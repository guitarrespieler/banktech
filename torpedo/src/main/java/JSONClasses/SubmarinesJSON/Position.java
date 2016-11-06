
package JSONClasses.SubmarinesJSON;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Position {

    @SerializedName("x")
    @Expose
    private double x;
    @SerializedName("y")
    @Expose
    private double y;

    /**
     * 
     * @return
     *     The x
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    public double getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    public void setY(double y) {
        this.y = y;
    }

}
