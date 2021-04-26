package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import Country.*;

public class GraphicMap extends JPanel {
	
	private Map map;
	
	public GraphicMap(Map map){
		super();
		this.map = map;
	}
	
	public void paintComponent(Graphics g ) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Settlement settlement : map.getSettlements())
		{
			gr.setColor(settlement.getColor().getColor());
			int y=settlement.getLocation().getPoint().getY();
			int x=settlement.getLocation().getPoint().getX();
			int width=settlement.getLocation().getSize().getWidth();
			int height=settlement.getLocation().getSize().getHeight();
			gr.drawRect(x,y,width,height);
			gr.fillRect(x, y, width, height);
			
		}
		
		
	}
}

