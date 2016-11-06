package model.gameconfig;

import com.google.gson.annotations.SerializedName;

public enum GameStatus {
	@SerializedName("WAITING")
	WAITING,
	@SerializedName("RUNNING")
	RUNNING,
	@SerializedName("ENDED")
	ENDED
}
