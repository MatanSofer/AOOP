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
	double resulotionX=1,resulotionY=1;

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
		
		int maxX=0,maxY=0,x,y,width,height,center1_x,center1_y,y2,x2,width2,height2,center2_x,center2_y;
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			y=settlement.getLocation().getPoint().getY()+10;
			x=settlement.getLocation().getPoint().getX();
			width=settlement.getLocation().getSize().getWidth();
			height=settlement.getLocation().getSize().getHeight();
			if(y+height>maxY)
				maxY=y+height;
			if(x+width>maxX)
				maxX=x+width;
			resulotionX=(double)maxX/810;
			resulotionY=(double)maxY/810;
		}
		
		
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			for (Settlement settlement2 : settlement.getconnectedSettlements()) {
				gr.setColor(Color.BLACK);
				
				y=settlement.getLocation().getPoint().getY()+10;
				x=settlement.getLocation().getPoint().getX();
				width=settlement.getLocation().getSize().getWidth();
				height=settlement.getLocation().getSize().getHeight();
				center1_x= x+width/2;
				center1_y= y+height/2;
						
				y2=settlement2.getLocation().getPoint().getY()+10;
				x2=settlement2.getLocation().getPoint().getX();
				width2=settlement2.getLocation().getSize().getWidth();
				height2=settlement2.getLocation().getSize().getHeight();
				center2_x= x2+width2/2;
				center2_y= y2+height2/2;
				
				gr.drawLine((int)(center1_x/resulotionX),(int)(center1_y/resulotionY),(int)(center2_x/resulotionX),(int)(center2_y/resulotionY));
			}
			
		}
		
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			y=settlement.getLocation().getPoint().getY()+10;
			x=settlement.getLocation().getPoint().getX();
			width=settlement.getLocation().getSize().getWidth();
			height=settlement.getLocation().getSize().getHeight();
			
			gr.setColor(settlement.getColor().getColor());
			
			gr.fillRect((int)(x/resulotionX), (int)(y/resulotionY), (int)(width/resulotionX), (int)(height/resulotionY));
			
			gr.setColor(Color.BLACK);

			gr.drawRect((int)(x/resulotionX),(int)(y/resulotionY),(int)(width/resulotionX),(int)(height/resulotionY));
			

	
		}
		
		gr.setColor(Color.BLACK);
		for (Settlement settlement : Main.getMap().getSettlements())
		{
			y=settlement.getLocation().getPoint().getY()+10;
			x=settlement.getLocation().getPoint().getX();
			gr.drawString(settlement.getName(),(int)(x/resulotionX), (int)(y/resulotionY));
	
		}
		
		
	}
	
	
	
	
	
	
	private class CustomMouseListener implements MouseListener{

	    public void mouseClicked(MouseEvent e) {
	    	
	    		 int xFrame=e.getX();
	    		 int yFrame=e.getY();
	    		 
	    		 for (Settlement settlement : Main.getMap().getSettlements())
	    			{
	    				int y=(int)((settlement.getLocation().getPoint().getY()+10)/resulotionY);
	    				int x=(int)((settlement.getLocation().getPoint().getX())/resulotionX);
	    				int width=(int)((settlement.getLocation().getSize().getWidth())/resulotionX);
	    				int height=(int)((settlement.getLocation().getSize().getHeight())/resulotionY);
	    		 
	    					if( (xFrame>=x && xFrame<=width+x) &&(yFrame>=y && yFrame<=height+y) )
	    					{
	    						String name = settlement.getName();
	    						statwindow.getallStats().newFilter1(name);
	    						statwindow.setVisible(true);
	    						
	    					
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

