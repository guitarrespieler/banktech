package model.gameObjects.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmarineDataHolder extends EntityDataHolder {
	
	/**
     * Tengeralattjáróknál lesz érdekes
     */
    @SerializedName("hp")
    @Expose
    private Integer hp;
    @SerializedName("sonarCooldown")
    @Expose
    private Integer sonarCooldown;
    @SerializedName("torpedoCooldown")
    @Expose
    private Integer torpedoCooldown;
    @SerializedName("sonarExtended")
    @Expose
    private Integer sonarExtended;    
    
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Integer getSonarCooldown() {
		return sonarCooldown;
	}
	public void setSonarCooldown(Integer sonarCooldown) {
		this.sonarCooldown = sonarCooldown;
	}
	public Integer getTorpedoCooldown() {
		return torpedoCooldown;
	}
	public void setTorpedoCooldown(Integer torpedoCooldown) {
		this.torpedoCooldown = torpedoCooldown;
	}
	public Integer getSonarExtended() {
		return sonarExtended;
	}
	public void setSonarExtended(Integer sonarExtended) {
		this.sonarExtended = sonarExtended;
	}
}
