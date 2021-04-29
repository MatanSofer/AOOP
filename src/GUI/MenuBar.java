package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.html.ImageView;

import IO.SimulationFile;
import Simulation.Clock;
import Simulation.Main;

public class MenuBar extends JMenuBar {

	//Where the GUI is created:
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	
	public MenuBar() {
	
		//Create the menu bar.
		super();

		
		//Build the first menu (File).
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		      "The only menu in this program that has menu items");
		add(menu);
		
		menuItem = new JMenuItem("Load",new ImageIcon("img/load.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimulationFile simulationfile = new SimulationFile(Main.loadFileFunc());
				Main.setMap(simulationfile.getMap()); //return value from simulation ; reference to map.
				System.out.println(Main.getMap()); //print all settle string
				Main.getMap().SickPplInnit();//Initialize 1% (infect population).
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription(
		      "This doesn't really do anything");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Statistics",new ImageIcon("img/stats.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menu.addSeparator();
		
		menuItem = new JMenuItem("Edit Mutations",new ImageIcon("img/virus.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame topFrame1 = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				MutationDialog about = new MutationDialog(topFrame1);

			}
		});
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		
		menuItem = new JMenuItem("Exit",new ImageIcon("img/exit.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
			
		

		
		//Build second menu (Simulation) in the menu bar.
		
		menu = new JMenu("Simulation");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		      "The only menu in this program that has menu items");
		add(menu);
		
		menuItem = new JMenuItem("Play",new ImageIcon("img/play.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Clock.setPlaying(true);
				
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription(
		      "This doesn't really do anything");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Pause",new ImageIcon("img/pause.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Clock.setPlaying(false);
			}
		});
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Set Ticks Per Day",new ImageIcon("img/ticks.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		
			
		//Build third menu in the menu bar (Help).
		
		
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		      "This menu does nothing");
		
		menuItem = new JMenuItem("Help",new ImageIcon("img/help.png"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame1 = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				HelpDialog about = new HelpDialog(topFrame1);
				
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("About",new ImageIcon("img/about.png"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				AboutDialog about = new AboutDialog(topFrame);
			}
		});
		menu.add(menuItem);
		
		add(menu);
		
		
		
	}
	
	private class AboutDialog extends JDialog {
		
		
		public AboutDialog(Frame window) {
			    

				super(window, "About",true);
				this.setLayout(new BorderLayout());
				JLabel dateCreated = new JLabel("The code for this assignment was wriiten between 21/4 - 29/4");
			    JLabel imgLabel =new JLabel();
				JLabel imgLabel2 =new JLabel();
				ImageIcon moon = new ImageIcon("img/maor.jpg");
				ImageIcon moon2 = new ImageIcon("img/matan.jpg");

			    
				Image image = moon.getImage(); // transform it 
				Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				moon = new ImageIcon(newimg);  // transform it back
				
				image = moon2.getImage(); // transform it 
				newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				moon2 = new ImageIcon(newimg);  // transform it back
				
				
				imgLabel.setIcon(moon);
				imgLabel2.setIcon(moon2);
				this.add(imgLabel);
				
				
			    JLabel text = new JLabel("The Authors are :\n Matan sofer \n Maor Arnon ");
				
				this.add(text,BorderLayout.NORTH);
				this.add(imgLabel,BorderLayout.WEST);
				this.add(imgLabel2,BorderLayout.EAST);
				this.add(dateCreated,BorderLayout.SOUTH);
				
				pack();
				setVisible(true);
				
		}
	}
		
	


	private class HelpDialog extends JDialog {
	
			public HelpDialog(Frame window) {
				    super(window, "Help",false);
				    JLabel imgLabel =new JLabel();
				    ImageIcon moon = new ImageIcon("img/background.jpg");
				    Image image = moon.getImage(); // transform it 
					Image newimg = image.getScaledInstance(1267, 950,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					moon = new ImageIcon(newimg);  // transform it back
					imgLabel.setIcon(moon);
					this.add(imgLabel);
					pack();
					setVisible(true);		
			}


}


	private Image getScaledImage(Image srcImg, int w, int h){
  		  BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
  		  Graphics2D g2 = resizedImg.createGraphics();

  		  g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
   		  g2.drawImage(srcImg, 0, 0, w, h, null);
 	      g2.dispose();

   		  return resizedImg;
}

}