
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		private Map map;
		private static JSlider slider;
		private static GraphicMap graphic_map;
		
		public MainWindow()
		{	
			super("Main Window");
			this.map=Main.getMap();

			this.setLayout(new BorderLayout());
	
			
			JPanel north= new JPanel();
			JPanel center = new JPanel();
			JPanel south =  new JPanel();
			
			center.setLayout(new BoxLayout(center , BoxLayout.LINE_AXIS));
			north.setLayout(new BoxLayout(north , BoxLayout.LINE_AXIS));
			south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
			
			setJMenuBar(new MenuBar());
			
			//north
			north.add(new JMenuBar());
			
			//center 
			center.add(new GraphicMap());
			
			//south
			south.add(slider = new JSlider(1,30,1));
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(1);
			slider.setMajorTickSpacing(5);
			
			this.add(center , BorderLayout.CENTER);
			this.add(north , BorderLayout.NORTH);
			this.add(south , BorderLayout.SOUTH);
			
		
			
			center.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("Display Phase"),
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
		
		
		public MainWindow(Map map)
		{	
			
			super("Main Window");
			this.map=map;

			this.setLayout(new BorderLayout());
	
			
			JPanel north= new JPanel();
			JPanel center = new JPanel();
			JPanel south =  new JPanel();
			
			center.setLayout(new BoxLayout(center , BoxLayout.LINE_AXIS));
			north.setLayout(new BoxLayout(north , BoxLayout.LINE_AXIS));
			south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
			
			setJMenuBar(new MenuBar());
			
			//north
			north.add(new JMenuBar());
			
			//center 
			center.add(new GraphicMap());
			
			//south
			south.add(slider = new JSlider(1,30,1));
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(1);
			slider.setMajorTickSpacing(5);
			
			this.add(center , BorderLayout.CENTER);
			this.add(north , BorderLayout.NORTH);
			this.add(south , BorderLayout.SOUTH);
			
		
			
			center.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("Display Phase"),
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
		
		public static GraphicMap getGraphicMap() {
			return graphic_map;
		}
		
		
		
	}
