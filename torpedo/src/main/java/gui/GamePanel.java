package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 298144871000160168L;
	
	private static final double xScale = 0.5;
	private static final double yScale = -0.5;
	private static final double yTranslate = -800;
	
	
	public GamePanel(Dimension windowDim) {		
		setPreferredSize(windowDim);
		
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;		
		g2d.scale(xScale, yScale);	
		g2d.translate(0, yTranslate);
		
		if(!isPreferredSizeSet())
			setSize(getPreferredSize());
		
		g2d.fillRoundRect(50, 50, 50, 50, 100, 100);
		
	}

}
