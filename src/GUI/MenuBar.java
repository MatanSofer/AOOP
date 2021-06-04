package GUI;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.CyclicBarrier;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Simulation.Clock;
import Simulation.Main;

public class MenuBar extends JMenuBar {

	// Where the GUI is created:
	JMenu menu, submenu;
	JMenuItem menuItem, loadButton, playButton, pauseButton, stopButton;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	private StatWindow statwindow;
	private MainWindow mainwindow;
	private Map map;

	public MenuBar(StatWindow statwindow, MainWindow mainwindow, Map map) {

		// Create the menu bar.
		super();        
		this.statwindow = statwindow;
		this.mainwindow = mainwindow;
		this.map = map;
		menu = new JMenu("File"); // The first menu on the menu bar - File.
		add(menu);

		// Load button
		loadButton = new JMenuItem("Load", new ImageIcon("img/load.png"));

		loadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimulationFile simulationfile = new SimulationFile(loadFileFunc());

				map.setMap(simulationfile.getMap()); // return value from simulation ; reference to map.
				map.setStop(false);

				System.out.println(map); // print all settle string
				
				for (Settlement settle : map)  //for each settlement add map reference
					settle.addReference(map);

				map.cyclic = new CyclicBarrier(map.getSettlements().size(), new Runnable() {  //cyclic barrier with all required updates
					public void run() {
						map.updateAll(mainwindow, statwindow);
						Clock.nextTick();// move the clock one tick forward
						System.out.println("\"############################################   Simulation number : " + (Clock.now())
								+ "    ############################################"); // the number of simulation
					
						
						try {
							Thread.sleep(250 * MainWindow.getSliderValue());  //sleep up to slider value
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				});

				map.spawn_all(); //create threads

				loadButton.setEnabled(false);
				pauseButton.setEnabled(false);
				playButton.setEnabled(true);

			}
		});
		menu.add(loadButton);
		menu.addSeparator();// The line between the buttons.

		// LogFile button
		menuItem = new JMenuItem("LogFile", new ImageIcon("img/log.png"));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IO.LogFile.SaveTable("Log");
			}
		});
		menu.add(menuItem);
		menu.addSeparator();// The line between the buttons.

		
		// UndoLogFile button
		menuItem = new JMenuItem("UndoLogFile");

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//for check
				
				IO.LogFile.getMemento();

				
			}
		});
		menu.add(menuItem);
		menu.addSeparator();// The line between the buttons.
		
		// Stats button
		menuItem = new JMenuItem("Statistics", new ImageIcon("img/stats.png")); // opens statistic window
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statwindow.getallStats().newFilter1("");
				statwindow.setVisible(true);

			}
		});
		menu.add(menuItem);
		menu.addSeparator();// The line between the buttons.

		// Edit Mutation button
		menuItem = new JMenuItem("Edit Mutations", new ImageIcon("img/virus.png")); // opens mutation frame as dialog
																					// frame
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame topFrame1 = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				new MutationDialog(topFrame1);

			}
		});
		menu.add(menuItem);
		menu.addSeparator();// The line between the buttons.

		// Exit button
		menuItem = new JMenuItem("Exit", new ImageIcon("img/exit.png")); // exit program
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		menu.add(menuItem);

		// The 2nd menu on the menu bar - Simulation.
		menu = new JMenu("Simulation");
		add(menu);

		// Play button
		playButton = new JMenuItem("Play", new ImageIcon("img/play.png"));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setPlaying(true);
				map.updateAll(mainwindow, statwindow);
				synchronized (map) {
					map.notifyAll();
				}
				
				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				stopButton.setEnabled(true);
			}
		});
		playButton.setEnabled(false);
		menu.add(playButton);
		menu.addSeparator();// The line between the buttons.

		// Pause button
		pauseButton = new JMenuItem("Pause", new ImageIcon("img/pause.png"));
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				map.setPlaying(false);
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
				stopButton.setEnabled(true);

			}
		});
		pauseButton.setEnabled(false);
		menu.add(pauseButton);
		menu.addSeparator();// The line between the buttons.

		// Stop button
		stopButton = new JMenuItem("Stop", new ImageIcon("img/stop.png"));
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(map)
				{
					map.notifyAll();
				}
				map.setStop(true);
				map.setPlaying(false);
				loadButton.setEnabled(true);
				playButton.setEnabled(false);
				pauseButton.setEnabled(false);
				stopButton.setEnabled(false);
			}
		});
		stopButton.setEnabled(false);
		menu.add(stopButton);
		menu.addSeparator();// The line between the buttons.

		// Set Ticks Per Day button
		menuItem = new JMenuItem("Set Ticks Per Day", new ImageIcon("img/ticks.png"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame2 = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				new SetClicks(topFrame2);
			}
		});
		menu.add(menuItem);

		// The 3rd menu on the menu bar - Help
		menu = new JMenu("Help");
		add(menu);

		// Help button
		menuItem = new JMenuItem("Help", new ImageIcon("img/help.png"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame1 = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				new HelpDialog(topFrame1);
			}
		});
		menu.add(menuItem);
		menu.addSeparator();// The line between the buttons.

		// About button
		menuItem = new JMenuItem("About", new ImageIcon("img/about.png"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(getParent());
				new AboutDialog(topFrame);
			}
		});
		menu.add(menuItem);

	}

	private class AboutDialog extends JDialog { // dialog for about button non modal
		public AboutDialog(Frame window) {
			super(window, "About", false);
			this.setLayout(new BorderLayout());
			JLabel dateCreated = new JLabel("The code for this assignment was wriiten between 21/4 - 29/4");
			JLabel imgLabel = new JLabel();
			JLabel imgLabel2 = new JLabel();
			ImageIcon moon = new ImageIcon("img/maor.jpg");
			ImageIcon moon2 = new ImageIcon("img/matan.jpg");

			Image image = moon.getImage(); // transform it
			Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

			moon = new ImageIcon(newimg); // transform it back

			image = moon2.getImage(); // transform it
			newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			moon2 = new ImageIcon(newimg); // transform it back

			imgLabel.setIcon(moon); // The name moon is derived from the lab we copied this code from
			imgLabel2.setIcon(moon2);// i'm just too lazy to change it... but not too lazy to explain apperantly.
			this.add(imgLabel);

			JLabel text = new JLabel("The Authors are :\n Matan sofer \n Maor Arnon ");

			this.add(text, BorderLayout.NORTH);
			this.add(imgLabel, BorderLayout.WEST);
			this.add(imgLabel2, BorderLayout.EAST);
			this.add(dateCreated, BorderLayout.SOUTH);

			pack();
			setVisible(true);

		}
	}

	private class HelpDialog extends JDialog { // dialog for help button is modal

		public HelpDialog(Frame window) {
			super(window, "Help", true); // Sets a modal dialog.
			JLabel imgLabel = new JLabel();
			ImageIcon moon = new ImageIcon("img/background.jpg");
			Image image = moon.getImage(); // transform it
			Image newimg = image.getScaledInstance(1267, 950, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			moon = new ImageIcon(newimg); // transform it back
			imgLabel.setIcon(moon);
			this.add(imgLabel);
			pack();
			setVisible(true);
		}

	}

	private class SetClicks extends JDialog { // dialog for set click (Ticks*) per day spinner.

		public SetClicks(Frame window) {
			super(window, "Set Click per day", true);
			this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
			JSpinner jSpinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
			this.add(jSpinner1);
			JButton ok = new JButton("Click to set");
			this.add(ok);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int currentValue = (int) jSpinner1.getValue();
					Long l = Long.valueOf(currentValue);
					Clock.setticksPerDay(l);
				}
			});

			pack();
			setVisible(true);
		}

	}

	public File loadFileFunc() {  //choosing file path
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		System.out.println(f.getPath());
		return f;
	}

	public void updateAll() {  //update gui and table 

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainwindow.updateMap();
				statwindow.updateTable();
			}
		});

	
	}
	
	
	

//A function to change image size ... Here for future refrence

//	private Image getScaledImage(Image srcImg, int w, int h){           //Change image size in about dialog.
//  		  BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//  		  Graphics2D g2 = resizedImg.createGraphics();
//
//  		  g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//   		  g2.drawImage(srcImg, 0, 0, w, h, null);
// 	      g2.dispose();
//
//   		  return resizedImg;
//}

}