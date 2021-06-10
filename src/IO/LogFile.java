package IO;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Country.Settlement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;



public class LogFile { //originator

	private  File file = null;
	private  int fileCounter=0;
	private  String path;
	public  void setPath(String path1){path=path1;}
	

	public  void setMemento(Memento path1) {//update this path to memento path		
		path=path1.getPath();
		
	}
	public  Memento createMemento() {//create new memento to hold path
		return new Memento(path);
	}
	
	/////Memento object which holds path
	public  class Memento{
		private  String path;
		public Memento(String path1) {
			path=path1;
		}
		public String getPath() {return path;}
	}
	

	//after we restore last file location , we are changing the FILE path for writing	
	public  void changeFile(Memento m) {
		setPath(m.getPath());
		file = new File(path);

		
	}
	public  File SaveTable(String fileName)  {
		/**
		 * This function opens save file dialog using saveFileFunc for the deaths logs to be saved and writing a header.
		 * @param fileName : The default name for the file.
		 * @return The the file that was selected by the user.
		 */
		
		file = saveFileFunc(fileName);
		setPath(fileName+fileCounter+".log");
		System.out.println("Created a Log File\n");
		try (FileWriter out = new FileWriter(file,true);) {
			out.append("Log File");
			out.append('\n');
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return file;
	}

	public  void logSettlement(Settlement settlement) {
		/**
		 * This function save the deaths on the log file that was previously selected.
		 * @param settlement : The settlement that need to be logged.
		 * @return void.
		 */
		
			if (file != null) {
				synchronized(file) {
				String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
				int numberOfSick = settlement.getSickPeopleSize();
				int numberOfDead = settlement.getdeadPopulation();
				String name = settlement.getName();

				try (FileWriter out = new FileWriter(file,true)) {
					out.append("Log: [" + time  + "] Settlement Name- " + name + " Sick population- " + numberOfSick + " Dead population- " + numberOfDead);
					out.append('\n');
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
				
			}
		}
		
	}

	private  File saveFileFunc(String fileName) {
		/**
		 * This function opens save file dialog.
		 * @param fileName : The default name for the file.
		 * @return The the file that was selected by the user.
		 */
		
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.SAVE);
		fileCounter++;
		fd.setFile(fileName+fileCounter+".log");
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		System.out.println(f.getPath());
		return f;
	}
}
