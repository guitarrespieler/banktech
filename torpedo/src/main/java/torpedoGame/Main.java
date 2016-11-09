package torpedoGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.text.html.parser.Entity;

import com.google.gson.Gson;

import communication.Communication;

/**
 * @author zsigatibor
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static String urlpart;
	public static void main(String[] args) {
		Communication.mainURL="http://"+args[0]+"/jc16-srv/";
		String jsonstr = null;
		try {
			jsonstr = readIn();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		
//		GameInfoJSON gameinfo = gson.fromJson(jsonstr, GameInfoJSON.class);
//		
//		for(Entry<String, Boolean> entry : gameinfo.getGame().getConnectionStatus()){
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
		
			
		Entity obj = gson.fromJson(jsonstr, Entity.class);
		
		
		
		System.out.println("\n\t Ain't nobody have time fo' that!!! :'( ");

	}

private static String readIn() throws FileNotFoundException {
		
		BufferedReader buf = new BufferedReader(new FileReader(new File("../torpedo/input2.txt")));
		
		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			//huehuehuehue
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
