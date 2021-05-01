
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JMenuBar;
import javax.swing.JSlider;


import Country.Map;
import Simulation.Main;

public class MainWindow extends JFrame
{

//		private final JLabel lb1,lb2;
		private MenuBar bar;
		private static JSlider slider;
		private GraphicMap graphic_map;
		//private StatWindow statwindow;
		public MainWindow(StatWindow statwindow)
		{	
			super("Main Window");

			this.setLayout(new BorderLayout());
	
			
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
			
			//south
			south.add(slider = new JSlider(1,10,5));
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(1);
			slider.setMajorTickSpacing(5);
			
			this.add(center , BorderLayout.CENTER);
			this.add(north , BorderLayout.NORTH);
			this.add(south , BorderLayout.SOUTH);
			
		
			
			center.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("Graphic Map"),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			    
			center.setPreferredSize(new Dimension(800,800));
			setResizable(false);
			this.pack();
			this.setVisible(true);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				repaint();
				}
				});
		}
		
	
		
		public static int getSliderValue() {	
			return (int)slider.getValue();
		}
		
		public GraphicMap getGraphicMap() {
			return graphic_map;
		}
		
		
		public void updateMap() {
			
			graphic_map.repaint();
		}
		

		
	}
