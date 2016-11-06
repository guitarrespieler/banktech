package torpedoGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import JSONClasses.GameInfoJSON.GameInfoJSON;

/**
 * @author zsigatibor
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String jsonstr = null;
		try {
			jsonstr = readIn();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		
		GameInfoJSON gameinfo = gson.fromJson(jsonstr, GameInfoJSON.class);
		
		for(Entry<String, Boolean> entry : gameinfo.getGame().getConnectionStatus()){
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

private static String readIn() throws FileNotFoundException {
		
		BufferedReader buf = new BufferedReader(new FileReader(new File("../torpedo/input.txt")));
		
		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			while((line = buf.readLine()) != null){
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
		return sb.toString();
	}
}
