package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import Country.*;
import Simulation.Clock;
import Simulation.Main;

public class GraphicMap extends JPanel {

	private StatWindow statwindow;
	public GraphicMap (StatWindow statwindow){
		super();
		this.statwindow = statwindow;
		 this.addMouseListener(new CustomMouseListener());
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
				
				int y=settlement.getLocation().getPoint().getY()+10;
				int x=settlement.getLocation().getPoint().getX();
				int width=settlement.getLocation().getSize().getWidth();
				int height=settlement.getLocation().getSize().getHeight();
				int center1_x= x+width/2;
				int center1_y= y+height/2;
						
				int y2=settlement2.getLocation().getPoint().getY()+10;
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
			int y=settlement.getLocation().getPoint().getY()+10;
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
			int y=settlement.getLocation().getPoint().getY()+10;
			int x=settlement.getLocation().getPoint().getX();
			gr.drawString(settlement.getName(),x, y);
	
		}
		
		
	}
	
	
	
	
	
	
	private class CustomMouseListener implements MouseListener{

	    public void mouseClicked(MouseEvent e) {
	    	if(Main.getPlay())
	    	{
	    		 int xFrame=e.getX();
	    		 int yFrame=e.getY();
	    		 
	    		 for (Settlement settlement : Main.getMap().getSettlements())
	    			{
	    				int y=settlement.getLocation().getPoint().getY()+10;
	    				int x=settlement.getLocation().getPoint().getX();
	    				int width=settlement.getLocation().getSize().getWidth();
	    				int height=settlement.getLocation().getSize().getHeight();
	    		 
	    					if( (xFrame>=x && xFrame<=width+x) &&(yFrame>=y && yFrame<=height+y) )
	    					{
	    						String name = settlement.getName();
	    						statwindow.getallStats().newFilter1(name);
	    						statwindow.setVisible(true);
	    						
	    					}
	    			}
	    		  
	    	}
	    }

	    public void mousePressed(MouseEvent e) {
	    	// TODO Auto-generated method stub
	    }

	    public void mouseReleased(MouseEvent e) {
	    	// TODO Auto-generated method stub
	    }

	    public void mouseEntered(MouseEvent e) {
	    	// TODO Auto-generated method stub
	    }

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	 
	 }
	
	
	
}

