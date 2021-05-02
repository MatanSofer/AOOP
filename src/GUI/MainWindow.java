
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JSlider;




public class MainWindow extends JFrame
{

		private MenuBar bar;
		private static JSlider slider;
		private GraphicMap graphic_map;
		
		public MainWindow(StatWindow statwindow)
		{	
			super("Main Window");

			this.setLayout(new BorderLayout());    //set all layout and add to frame
	
			JPanel north= new JPanel();
			JPanel center = new JPanel();
			JPanel south =  new JPanel();
			
			center.setLayout(new BoxLayout(center , BoxLayout.LINE_AXIS));
			north.setLayout(new BoxLayout(north , BoxLayout.LINE_AXIS));
			south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
			
			setJMenuBar(new MenuBar(statwindow));
			
											//north
			north.add(new JMenuBar());
			
											//center 
			center.add(graphic_map = new GraphicMap(statwindow));
			
			south.add(new JLabel("Fast "));								//south
			south.add(slider = new JSlider(1,10,5));
			south.add(new JLabel("Slow"));
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(1);
			slider.setMajorTickSpacing(5);
			
			
			this.add(center , BorderLayout.CENTER);
			this.add(north , BorderLayout.NORTH);
			this.add(south , BorderLayout.SOUTH);
			
			
		
			
			center.setBorder(BorderFactory.createCompoundBorder(  //create title for graphic map
					BorderFactory.createTitledBorder("Graphic Map"),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			    
			center.setPreferredSize(new Dimension(850,850));
			setResizable(false);
			this.pack();
			this.setVisible(true);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				repaint();
				}
				});
		}
		
	
		
		public static int getSliderValue() {	//get value from slider
			return (int)slider.getValue();
		}
		
		public GraphicMap getGraphicMap() { //returns graphic map
			return graphic_map;
		}
		
		
		public void updateMap() {       //for simulation update
			
			getGraphicMap().repaint();
		}
		

		
	}
