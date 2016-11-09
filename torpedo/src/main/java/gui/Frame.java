package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import model.gameconfig.MapConfigDataHolder;
import torpedoGame.Main;

public class Frame extends JFrame {
	
	private static final Dimension prefferedDim = new Dimension(1366, 768);
	private Dimension gamePanelDimension;

	private MapConfigDataHolder mapconfig;
	
	public Frame(MapConfigDataHolder mapconfigdata) {
		
		
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
		
		
		GamePanel gp = new GamePanel(gamePanelDimension);
		
		add(gp);
		
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		MapConfigDataHolder mc = new MapConfigDataHolder();
		
		mc.setHeight(400);
		mc.setWidth(850);
		new Frame(mc);
	}

}
