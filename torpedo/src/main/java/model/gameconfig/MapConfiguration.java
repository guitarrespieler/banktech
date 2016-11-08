
package model.gameconfig;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import model.gameObjects.Position;

@Generated("org.jsonschema2pojo")
public class MapConfiguration {

    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("Positions")
    @Expose
    private List<Position> Positions = new ArrayList<Position>();
    @SerializedName("teamCount")
    @Expose
    private int teamCount;
    @SerializedName("submarinesPerTeam")
    @Expose
    private int submarinesPerTeam;
    @SerializedName("torpedoDamage")
    @Expose
    private double torpedoDamage;
    @SerializedName("torpedoHitScore")
    @Expose
    private double torpedoHitScore;
    @SerializedName("torpedoDestroyScore")
    @Expose
    private double torpedoDestroyScore;
    @SerializedName("torpedoHitPenalty")
    @Expose
    private double torpedoHitPenalty;
    @SerializedName("torpedoCooldown")
    @Expose
    private int torpedoCooldown;
    @SerializedName("sonarRange")
    @Expose
    private double sonarRange;
    @SerializedName("extendedSonarRange")
    @Expose
    private double extendedSonarRange;
    @SerializedName("extendedSonarRounds")
    @Expose
    private int extendedSonarRounds;
    @SerializedName("extendedSonarCooldown")
    @Expose
    private int extendedSonarCooldown;
    @SerializedName("torpedoSpeed")
    @Expose
    private double torpedoSpeed;
    @SerializedName("torpedoExplosionRadius")
    @Expose
    private double torpedoExplosionRadius;
    @SerializedName("roundLength")
    @Expose
    private long roundLength;
    @SerializedName("islandSize")
    @Expose
    private double islandSize;
    @SerializedName("submarineSize")
    @Expose
    private double submarineSize;
    @SerializedName("rounds")
    @Expose
    private int rounds;
    @SerializedName("maxSteeringPerRound")
    @Expose
    private double maxSteeringPerRound;
    @SerializedName("maxAccelerationPerRound")
    @Expose
    private double maxAccelerationPerRound;
    @SerializedName("maxSpeed")
    @Expose
    private double maxSpeed;
    @SerializedName("torpedoRange")
    @Expose
    private double torpedoRange;
    @SerializedName("rateLimitedPenalty")
    @Expose
    private double rateLimitedPenalty;

    /**
     * 
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The Positions
     */
    public List<Position> getPositions() {
        return Positions;
    }

    /**
     * 
     * @param Positions
     *     The Positions
     */
    public void setPositions(List<Position> Positions) {
        this.Positions = Positions;
    }

    /**
     * 
     * @return
     *     The teamCount
     */
    public int getTeamCount() {
        return teamCount;
    }

    /**
     * 
     * @param teamCount
     *     The teamCount
     */
    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 
     * @return
     *     The submarinesPerTeam
     */
    public int getSubmarinesPerTeam() {
        return submarinesPerTeam;
    }

    /**
     * 
     * @param submarinesPerTeam
     *     The submarinesPerTeam
     */
    public void setSubmarinesPerTeam(int submarinesPerTeam) {
        this.submarinesPerTeam = submarinesPerTeam;
    }

    /**
     * 
     * @return
     *     The torpedoDamage
     */
    public double getTorpedoDamage() {
        return torpedoDamage;
    }

    /**
     * 
     * @param torpedoDamage
     *     The torpedoDamage
     */
    public void setTorpedoDamage(double torpedoDamage) {
        this.torpedoDamage = torpedoDamage;
    }

    /**
     * 
     * @return
     *     The torpedoHitScore
     */
    public double getTorpedoHitScore() {
        return torpedoHitScore;
    }

    /**
     * 
     * @param torpedoHitScore
     *     The torpedoHitScore
     */
    public void setTorpedoHitScore(double torpedoHitScore) {
        this.torpedoHitScore = torpedoHitScore;
    }

    /**
     * 
     * @return
     *     The torpedoDestroyScore
     */
    public double getTorpedoDestroyScore() {
        return torpedoDestroyScore;
    }

    /**
     * 
     * @param torpedoDestroyScore
     *     The torpedoDestroyScore
     */
    public void setTorpedoDestroyScore(double torpedoDestroyScore) {
        this.torpedoDestroyScore = torpedoDestroyScore;
    }

    /**
     * 
     * @return
     *     The torpedoHitPenalty
     */
    public double getTorpedoHitPenalty() {
        return torpedoHitPenalty;
    }

    /**
     * 
     * @param torpedoHitPenalty
     *     The torpedoHitPenalty
     */
    public void setTorpedoHitPenalty(double torpedoHitPenalty) {
        this.torpedoHitPenalty = torpedoHitPenalty;
    }

    /**
     * 
     * @return
     *     The torpedoCooldown
     */
    public int getTorpedoCooldown() {
        return torpedoCooldown;
    }

    /**
     * 
     * @param torpedoCooldown
     *     The torpedoCooldown
     */
    public void setTorpedoCooldown(int torpedoCooldown) {
        this.torpedoCooldown = torpedoCooldown;
    }

    /**
     * 
     * @return
     *     The sonarRange
     */
    public double getSonarRange() {
        return sonarRange;
    }

    /**
     * 
     * @param sonarRange
     *     The sonarRange
     */
    public void setSonarRange(double sonarRange) {
        this.sonarRange = sonarRange;
    }

    /**
     * 
     * @return
     *     The extendedSonarRange
     */
    public double getExtendedSonarRange() {
        return extendedSonarRange;
    }

    /**
     * 
     * @param extendedSonarRange
     *     The extendedSonarRange
     */
    public void setExtendedSonarRange(double extendedSonarRange) {
        this.extendedSonarRange = extendedSonarRange;
    }

    /**
     * 
     * @return
     *     The extendedSonarRounds
     */
    public int getExtendedSonarRounds() {
        return extendedSonarRounds;
    }

    /**
     * 
     * @param extendedSonarRounds
     *     The extendedSonarRounds
     */
    public void setExtendedSonarRounds(int extendedSonarRounds) {
        this.extendedSonarRounds = extendedSonarRounds;
    }

    /**
     * 
     * @return
     *     The extendedSonarCooldown
     */
    public int getExtendedSonarCooldown() {
        return extendedSonarCooldown;
    }

    /**
     * 
     * @param extendedSonarCooldown
     *     The extendedSonarCooldown
     */
    public void setExtendedSonarCooldown(int extendedSonarCooldown) {
        this.extendedSonarCooldown = extendedSonarCooldown;
    }

    /**
     * 
     * @return
     *     The torpedoSpeed
     */
    public double getTorpedoSpeed() {
        return torpedoSpeed;
    }

    /**
     * 
     * @param torpedoSpeed
     *     The torpedoSpeed
     */
    public void setTorpedoSpeed(double torpedoSpeed) {
        this.torpedoSpeed = torpedoSpeed;
    }

    /**
     * 
     * @return
     *     The torpedoExplosionRadius
     */
    public double getTorpedoExplosionRadius() {
        return torpedoExplosionRadius;
    }

    /**
     * 
     * @param torpedoExplosionRadius
     *     The torpedoExplosionRadius
     */
    public void setTorpedoExplosionRadius(double torpedoExplosionRadius) {
        this.torpedoExplosionRadius = torpedoExplosionRadius;
    }

    /**
     * 
     * @return
     *     The roundLength
     */
    public long getRoundLength() {
        return roundLength;
    }

    /**
     * 
     * @param roundLength
     *     The roundLength
     */
    public void setRoundLength(long roundLength) {
        this.roundLength = roundLength;
    }

    /**
     * 
     * @return
     *     The islandSize
     */
    public double getIslandSize() {
        return islandSize;
    }

    /**
     * 
     * @param islandSize
     *     The islandSize
     */
    public void setIslandSize(double islandSize) {
        this.islandSize = islandSize;
    }

    /**
     * 
     * @return
     *     The submarineSize
     */
    public double getSubmarineSize() {
        return submarineSize;
    }

    /**
     * 
     * @param submarineSize
     *     The submarineSize
     */
    public void setSubmarineSize(double submarineSize) {
        this.submarineSize = submarineSize;
    }

    /**
     * 
     * @return
     *     The rounds
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * 
     * @param rounds
     *     The rounds
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     * 
     * @return
     *     The maxSteeringPerRound
     */
    public double getMaxSteeringPerRound() {
        return maxSteeringPerRound;
    }

    /**
     * 
     * @param maxSteeringPerRound
     *     The maxSteeringPerRound
     */
    public void setMaxSteeringPerRound(double maxSteeringPerRound) {
        this.maxSteeringPerRound = maxSteeringPerRound;
    }

    /**
     * 
     * @return
     *     The maxAccelerationPerRound
     */
    public double getMaxAccelerationPerRound() {
        return maxAccelerationPerRound;
    }

    /**
     * 
     * @param maxAccelerationPerRound
     *     The maxAccelerationPerRound
     */
    public void setMaxAccelerationPerRound(double maxAccelerationPerRound) {
        this.maxAccelerationPerRound = maxAccelerationPerRound;
    }

    /**
     * 
     * @return
     *     The maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 
     * @param maxSpeed
     *     The maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 
     * @return
     *     The torpedoRange
     */
    public double getTorpedoRange() {
        return torpedoRange;
    }

    /**
     * 
     * @param torpedoRange
     *     The torpedoRange
     */
    public void setTorpedoRange(double torpedoRange) {
        this.torpedoRange = torpedoRange;
    }

    /**
     * 
     * @return
     *     The rateLimitedPenalty
     */
    public double getRateLimitedPenalty() {
        return rateLimitedPenalty;
    }

    /**
     * 
     * @param rateLimitedPenalty
     *     The rateLimitedPenalty
     */
    public void setRateLimitedPenalty(double rateLimitedPenalty) {
        this.rateLimitedPenalty = rateLimitedPenalty;
    }

}
