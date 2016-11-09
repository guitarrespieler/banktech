package torpedo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.gameObjects.entities.SubmarineDataHolder;

public class SubmarineListJSONParserTest {

	public static void main(String[] args) {
		String jsonStr = null;
		try {
			jsonStr = readIn();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		
		JsonObject job = gson.fromJson(jsonStr, JsonObject.class);
		
		List<SubmarineDataHolder> list = parseJson(gson, job);

	}

	/**
	 * Listát csinál az új infókból.
	 */
	private static List<SubmarineDataHolder> parseJson(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("submarines");
		
		Type listType = new TypeToken<List<SubmarineDataHolder>>() {}.getType();

		List<SubmarineDataHolder> newSubmarineData = gsonRef.fromJson(jes,listType);
		return newSubmarineData;
	}

	private static String readIn() throws FileNotFoundException {

		BufferedReader buf = new BufferedReader(new FileReader(new File("../torpedo/input2.txt")));

		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			// huehuehuehue
			while ((line = buf.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

}
