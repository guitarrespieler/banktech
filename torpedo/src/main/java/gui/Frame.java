package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.gameObjects.Position;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.MapConfigDataHolder;
import torpedoGame.Main;

public class Frame extends JFrame {
	
	private static final Dimension prefferedDim = new Dimension(1366, 768);
	private Dimension gamePanelDimension;

	private MapConfigDataHolder mapconfig;
	private GamePanel gamePanel;
	
	public Frame(MapConfigDataHolder mapconfigdata) {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(prefferedDim);
		setSize(getPreferredSize());
		
		setResizable(false);
		
		setTitle("Torpedo Game");
		
		init(mapconfigdata);
	}
	
	

	private void init(MapConfigDataHolder mapconfigdata) {
		setLocationRelativeTo(null);
		
		mapconfig = mapconfigdata;
		gamePanelDimension = new Dimension(mapconfig.getWidth(), mapconfig.getHeight());
		
		
		gamePanel = new GamePanel(gamePanelDimension,mapconfig);
		
		add(gamePanel);

		setVisible(true);
		
	}
	
	public void paintTheseEnemies(List<EntityDataHolder> entities){
		gamePanel.addOtherEntities(entities);
	}
	
	public void paintOwnSubmarines(List<SubmarineDataHolder>subs){
		gamePanel.setOwnSubmarineList(subs);
	}
	
	public static void main(String[] args) {
		MapConfigDataHolder mc = new MapConfigDataHolder();
		{//most beállítom kézzel, hogy ki tudjam próbálni
			mc.setHeight(800);
			mc.setWidth(1700);
			
			List<Position> posList = new LinkedList<Position>();
			posList.add(new Position(){{
				setX(0.0);
				setY(0.0);
			}});
			
			posList.add(new Position(){{
				setX(1700.0);
				setY(800.0);
			}});
			
			mc.setIslandSize(500);
			mc.setPositions(posList);
			
			mc.setSubmarineSize(100);
		}
		
		List<SubmarineDataHolder> submarines = makeSomeSubmarineFromJson();
		
		Frame f = new Frame(mc);
		
		f.paintOwnSubmarines(submarines);
	}

	private static List<SubmarineDataHolder> makeSomeSubmarineFromJson() {
		String jsonStr = null;
		try {
			jsonStr = readIn();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		
		JsonObject job = gson.fromJson(jsonStr, JsonObject.class);
		
		List<SubmarineDataHolder> list = parseJson(gson, job);
		
		return list;
	}
	
	/**
	 * Listát csinál az új infókból.
	 */
	private static List<SubmarineDataHolder> parseJson(Gson gsonRef, JsonObject job) {
		JsonElement jes = job.get("submarines");
		
		java.lang.reflect.Type listType = new TypeToken<List<SubmarineDataHolder>>() {}.getType();

		List<SubmarineDataHolder> newSubmarineData = gsonRef.fromJson(jes,listType);
		return newSubmarineData;
	}

	private static String readIn() throws FileNotFoundException {

		BufferedReader buf = new BufferedReader(new FileReader(new File("../torpedo/submarines.txt")));

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
