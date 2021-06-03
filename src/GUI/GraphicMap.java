package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import Country.*;
import Simulation.Main;

public class GraphicMap extends JPanel {
	
	static double resulotionX=1,resulotionY=1;
	private StatWindow statwindow;
	private Map map;
	
	class CoonnectionDecorator extends Settlement{ //a decorator for settlement
		Color color;//the connection color
		CoonnectionDecorator(Settlement settle){
			super(settle); //using copy constructor
		}
		public Color updateConnectionColor(Settlement neibor) {//updates the connection color and returns it
			int red = (super.getColor().getColor().getRed() + neibor.getColor().getColor().getRed()) / 2;
			int green = (super.getColor().getColor().getGreen() + neibor.getColor().getColor().getGreen()) / 2;
			int blue = (super.getColor().getColor().getBlue() + neibor.getColor().getColor().getBlue()) / 2;
			return color = new Color(red, green, blue);
		}
		
		public void drawSettlementConnections(Graphics2D gr,double resulotionX,double resulotionY) {
			int x,y,width,height,center1_x,center1_y,y2,x2,width2,height2,center2_x,center2_y;
			for (Settlement settlement2 : super.getconnectedSettlements()) {
				
				updateConnectionColor(settlement2);//set the RGB average color for the connection
				gr.setColor(this.color);
				
				y=this.getLocation().getPoint().getY()+10;
				x=this.getLocation().getPoint().getX();
				width=this.getLocation().getSize().getWidth();
				height=this.getLocation().getSize().getHeight();
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
	}
	
	
	public GraphicMap (StatWindow statwindow,Map map){  //create graphic panel and add mouse listener
		
		super();
		this.statwindow = statwindow;
		this.map=map;
		this.addMouseListener(new CustomMouseListener());
	}
	

	
	public void paintComponent(Graphics g ) {  //paint component to draw map by lines and squares/rectengles from settlement data
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int maxX=0,maxY=0,x,y,width,height,center1_x,center1_y,y2,x2,width2,height2,center2_x,center2_y;
		
		for (Settlement settlement : map) //finds the farthest settlement from the point (0,0) and sets the resolution accordingly
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
		
		
		for (Settlement settlement : map)//Draws the connections
		{
			new CoonnectionDecorator(settlement).drawSettlementConnections(gr,resulotionX,resulotionY); 
			//Would have used private statics values and sent only settlement if we weren't told not to use statics
		}
		
		for (Settlement settlement : map)//Draws the settlements
		{
			drawSettlement(gr,resulotionX,resulotionY,settlement);
			//Would have used private statics values and sent only settlement if we weren't told not to use statics
		}
		
		gr.setColor(Color.BLACK);
		for (Settlement settlement :map)
		{
			y=settlement.getLocation().getPoint().getY()+10;
			x=settlement.getLocation().getPoint().getX();
			gr.drawString(settlement.getName(),(int)(x/resulotionX), (int)(y/resulotionY));
	
		}
		
		
	}
	
	
	
	
	
	
	private class CustomMouseListener implements MouseListener{   //if user click on specific settle

	    public void mouseClicked(MouseEvent e) {
	    	
	    		 int xFrame=e.getX();
	    		 int yFrame=e.getY();
	    		 
	    		 for (Settlement settlement : map)
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
	
	public static void drawSettlement(Graphics2D gr,double resulotionX,double resulotionY,Settlement settlement) {
		int x,y,width,height;
		y=settlement.getLocation().getPoint().getY()+10;
		x=settlement.getLocation().getPoint().getX();
		width=settlement.getLocation().getSize().getWidth();
		height=settlement.getLocation().getSize().getHeight();
		
		gr.setColor(settlement.getColor().getColor());
		
		gr.fillRect((int)(x/resulotionX), (int)(y/resulotionY), (int)(width/resulotionX), (int)(height/resulotionY));
		
		gr.setColor(Color.BLACK);

		gr.drawRect((int)(x/resulotionX),(int)(y/resulotionY),(int)(width/resulotionX),(int)(height/resulotionY));
	}
	

	
	
}

