
package model.gameObjects;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class EntityDataHolder {

    @SerializedName("type")
    @Expose
    private EntityType type;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("position")
    @Expose
    private Position position;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("velocity")
    @Expose
    private double velocity;
    @SerializedName("angle")
    @Expose
    private double angle;
    
    /**
     * Torpedóknál érdekes.
     */
    @SerializedName("roundsMoved")
    @Expose
    private long roundsMoved;

    /**
     * 
     * @return
     *     The type
     */
    public EntityType getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(EntityType type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The id
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * 
     * @param velocity
     *     The velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * 
     * @return
     *     The angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * 
     * @param angle
     *     The angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * 
     * @return
     *     The roundsMoved
     */
    public long getRoundsMoved() {
        return roundsMoved;
    }

    /**
     * 
     * @param roundsMoved
     *     The roundsMoved
     */
    public void setRoundsMoved(long roundsMoved) {
        this.roundsMoved = roundsMoved;
    }

    /**
     * @return true, ha típus és id egyezik; false egyébként.
     */
    public boolean equals(EntityDataHolder other){
    	if(this.getType().equals(other.getType())){
    		if(this.getId() == other.getId()){
    			return true;
    		}    		
    	}
    	return false;    	   	
    }
}
