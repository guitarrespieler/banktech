package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.gameObjects.Position;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.MapConfigDataHolder;

public class GamePanel extends JPanel {

	private static final Color BackgroundColor = new Color(207,236,242);

	private static final long serialVersionUID = 298144871000160168L;
	
	private static final double xScale = 0.5;
	private static final double yScale = -0.5;
	private static final double xTranslate = 0;
	private static final double yTranslate = -800;

	private MapConfigDataHolder mapData;
	
	List<SubmarineDataHolder> ownSubmarines;
	
	
	public GamePanel(Dimension windowDim,MapConfigDataHolder dataholder,List<SubmarineDataHolder> submarines) {		
		mapData = dataholder;
		ownSubmarines = submarines;
		setPreferredSize(windowDim);
		
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;		
		g2d.scale(xScale, yScale);	//skálázni kell, mert nem fér bele a HD ablakméretbe
		g2d.translate(xTranslate, yTranslate);//el kell tolni, mert rossz irányban van
		
		g2d.setBackground(BackgroundColor);
		
		if(!isPreferredSizeSet())
			setSize(getPreferredSize());
		
		paintIslands(g2d);
		
	}

	private void paintIslands(Graphics2D g2d) {
		List<Position> positions = mapData.getPositions();
		
		int islandSize = (int) Math.round(mapData.getIslandSize());
		
		for (Position position : positions) {
			drawThisCircle(position, islandSize, Color.GREEN,g2d);
		}
		
	}

	/**
	 * Adott pozícióra rajzol egy kört.
	 */
	private void drawThisCircle(Position pos, int size,Color color, Graphics2D g2d) {
		int xPos = (int) Math.round(pos.getX());
		int yPos = (int) Math.round(pos.getY());
		
		xPos = xPos-(size/2);
		yPos = yPos-(size/2);
		
		g2d.fillOval(xPos, yPos, size, size);
		
		
	}

}
