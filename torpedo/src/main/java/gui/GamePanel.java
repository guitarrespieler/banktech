package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.gameObjects.Position;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.SubmarineDataHolder;
import model.gameconfig.MapConfigDataHolder;

public class GamePanel extends JPanel {

	private static final Color BackgroundColor = new Color(207,236,242);
	private static final Color IslandColor = new Color(101,184,88);
	private static final Color ownSubmarineColor = new Color(255,193,37);
	
	private static final Color enemySubmarineColor = new Color(255,0,255);
	
	private static final Color torpedoColor = new Color(255,0,0);
	
	

	private static final long serialVersionUID = 298144871000160168L;
	
	private static final double xScale = 0.5;
	private static final double yScale = -0.5;
	private static final double xTranslate = 0;
	private static final double yTranslate = -800;

	private MapConfigDataHolder mapData;
	
	/**
	 * Saját tengeralattjárókat mentjük ide
	 */
	List<SubmarineDataHolder> ownSubmarines = new LinkedList<SubmarineDataHolder>();
	
	/**
	 * szonárral felderített elemek
	 */
	private List<EntityDataHolder> otherEntities = new LinkedList<EntityDataHolder>();
	
	
	public GamePanel(Dimension windowDim,MapConfigDataHolder dataholder) {		
		super();
		mapData = dataholder;
		
		setPreferredSize(windowDim);
		
		setOpaque(true);//átlátszatlan lesz így
		
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;		
		g2d.scale(xScale, yScale);	//skálázni kell, mert nem fér bele a HD ablakméretbe
		g2d.translate(xTranslate, yTranslate);//el kell tolni, mert rossz irányban van
		
		if(!isPreferredSizeSet())
			setSize(getPreferredSize());
		
		setBackground(BackgroundColor);
		super.paintComponent(g2d);
		
		paintIslands(g2d);
		
		for (EntityDataHolder entityDataHolder : otherEntities) {
			drawThisEntity(entityDataHolder);
		}
		for (SubmarineDataHolder submarine : ownSubmarines) {
			drawThisEntity(submarine);
		}
	}

	private void paintIslands(Graphics2D g2d) {
		List<Position> positions = mapData.getPositions();
		
		int islandSize = (int) Math.round(mapData.getIslandSize());
		
		for (Position position : positions) {
			drawThisCircle(position, islandSize, IslandColor,g2d,"");
		}		
	}
	
	private void drawThisEntity(EntityDataHolder entity) {
		switch (entity.getType()) {
		case Torpedo:
			drawThisCircle(entity.getPosition(), mapData.getTorpedoExplosionRadius(),
					torpedoColor,(Graphics2D)getGraphics(),entity.getOwner());
			break;
		case Submarine:
			Color color = null;
			if(entity.getOwner().equals("HowHaveTimeForLife")){
				color = ownSubmarineColor;
			}else{
				color = enemySubmarineColor;
			}			
			drawThisCircle(entity.getPosition(), mapData.getSubmarineSize(), 
					color,(Graphics2D)getGraphics(),entity.getOwner());			
			break;
		default:
			break;
		}		
	}

	/**
	 * Adott pozícióra(középpont) rajzol egy kört + kiírja a paraméterül kapott Stringet
	 */
	private void drawThisCircle(Position pos, double size,Color color, Graphics2D g2d,String str) {
		double xPos = pos.getX();
		double yPos = pos.getY();
		
		//eltoljuk a középpontba
		xPos = xPos-(size/2);
		yPos = yPos-(size/2);
		
		int x = (int) Math.round(xPos);
		int y = (int) Math.round(yPos);
		
		int siz = (int) Math.round(size);
		
		g2d.setColor(color);
		g2d.fillOval(x, y, siz, siz);
		
		if(!(str == null || str.isEmpty())){
			AffineTransform orig = g2d.getTransform();
			g2d.scale(1, -1);
			g2d.translate(0,-2*yPos);
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 25)); 

			g2d.drawString(str, x, y);	
			
			g2d.setTransform(orig);
		}
			
	}

	public void setOwnSubmarineList(List<SubmarineDataHolder> list){
		ownSubmarines = list;
		invalidate();
		repaint();
	}

	public void addOtherEntities(List<EntityDataHolder> entities) {
		this.otherEntities = entities;
		repaint();
	}
	
}
