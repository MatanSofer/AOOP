package GUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

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
				// TODO Auto-generated method stub
				
			}
		});
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		
		menuItem = new JMenuItem("Exit",new ImageIcon("img/exit.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("About",new ImageIcon("img/about.png"));
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("About");
				f.add(new JLabel("about text"));
				f.setVisible(true);
				
			}
		});
		menu.add(menuItem);
		
		add(menu);
		
		
		
	}
	private class AboutDialog extends JDialog {
		
		
		public AboutDialog(Frame window) {
				super(window, "Login", true);
				this.add(new JLabel("The Authors are :\n Matan sofer \n Maor arnon"));
				
		}
		
		


}


}