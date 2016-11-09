package torpedo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

		JsonArray jar = jes.getAsJsonArray();
		
		List<SubmarineDataHolder> newSubmarineData = new LinkedList<SubmarineDataHolder>();
		
		for (JsonElement jsonElement : jar) {
			JsonObject job2 = jes.getAsJsonObject();
			SubmarineDataHolder dataholder = gsonRef.fromJson(job2, SubmarineDataHolder.class);
			newSubmarineData.add(dataholder);
		}
//		// FIXME not sure about this one...
//		@SuppressWarnings("unchecked")
//		List<SubmarineDataHolder> newSubmarineData = gsonRef.fromJson(jar,
//				(new ArrayList<SubmarineDataHolder>()).getClass());
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
