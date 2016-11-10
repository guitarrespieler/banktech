package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.MapConfigDataHolder;

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

	/**
	 * A kapott listát odaadja a gamePanelnek, ami kirajzolja a benne lévő entity-ket.
	 */
	public void paintTheseEnemies(Map<Submarine, Map<EntityType, List<EntityDataHolder>>> subMarinesSonarData) {
		Set<Submarine> keys = subMarinesSonarData.keySet();

		List<EntityDataHolder> entities = new LinkedList<EntityDataHolder>();
		
		for (Submarine submarine : keys) {
			Map<EntityType,List<EntityDataHolder>>map = subMarinesSonarData.get(submarine);
			
			entities.addAll(map.get(EntityType.Submarine));
			entities.addAll(map.get(EntityType.Torpedo));
			
		}
		gamePanel.addOtherEntities(entities);
	}

}
