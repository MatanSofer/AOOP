package IO;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Country.Settlement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class LogFile {

	private static File file = null;

	public static File SaveTable(String fileName) {
		/**
		 * This function opens save file dialog using saveFileFunc for the deaths logs to be saved and writing a header.
		 * @param fileName : The default name for the file.
		 * @return The the file that was selected by the user.
		 */

		file = saveFileFunc(fileName);
		System.out.println("Created a Log File\n");
		try (FileWriter out = new FileWriter(file,true)) {
			out.append("Log File");
			out.append('\n');
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return file;
	}

	public static void logSettlement(Settlement settlement) {
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

	private static File saveFileFunc(String fileName) {
		/**
		 * This function opens save file dialog.
		 * @param fileName : The default name for the file.
		 * @return The the file that was selected by the user.
		 */
			
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.SAVE);
		fd.setFile(fileName + ".txt");
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		System.out.println(f.getPath());
		return f;
	}
}
