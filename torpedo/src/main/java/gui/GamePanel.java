package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	public GamePanel(Dimension windowDim) {
		setSize(windowDim);
		
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 400);
		g2d.scale(1.4, 1.4);
		
		
		g2d.fillRoundRect(50, 50, 50, 50, 100, 100);
		
	}

}
