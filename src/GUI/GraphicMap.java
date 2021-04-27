package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import Country.*;
import Simulation.Main;

public class GraphicMap extends JPanel {

	
	public GraphicMap (){
		super();

	}
	
	public void Screen() {
		repaint();
	}
	
	public void paintComponent(Graphics g ) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			for (Settlement settlement2 : settlement.getconnectedSettlements()) {
				gr.setColor(Color.BLACK);
				
				int y=settlement.getLocation().getPoint().getY();
				int x=settlement.getLocation().getPoint().getX();
				int width=settlement.getLocation().getSize().getWidth();
				int height=settlement.getLocation().getSize().getHeight();
				int center1_x= x+width/2;
				int center1_y= y+height/2;
						
				int y2=settlement2.getLocation().getPoint().getY();
				int x2=settlement2.getLocation().getPoint().getX();
				int width2=settlement2.getLocation().getSize().getWidth();
				int height2=settlement2.getLocation().getSize().getHeight();
				int center2_x= x2+width2/2;
				int center2_y= y2+height2/2;
				
				gr.drawLine(center1_x,center1_y,center2_x,center2_y);
			}
			
		}
		
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			int y=settlement.getLocation().getPoint().getY();
			int x=settlement.getLocation().getPoint().getX();
			int width=settlement.getLocation().getSize().getWidth();
			int height=settlement.getLocation().getSize().getHeight();
			
			gr.setColor(settlement.getColor().getColor());
			
			gr.fillRect(x, y, width, height);
			
			gr.setColor(Color.BLACK);

			gr.drawRect(x,y,width,height);
			

	
		}
		
		gr.setColor(Color.BLACK);
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			int y=settlement.getLocation().getPoint().getY();
			int x=settlement.getLocation().getPoint().getX();
			gr.drawString(settlement.getName(),x, y);
	
		}
		
		
	}
}

