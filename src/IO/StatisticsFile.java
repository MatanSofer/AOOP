package IO;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
import javax.swing.JTable;

public abstract class StatisticsFile
{
	
	
	public static void SaveTable(String fileName , JTable currentTable)
	{
		File file = saveFileFunc(fileName);
		 try (FileWriter out = new FileWriter(file)) {
			
			for (int col = 0; col < currentTable.getColumnCount(); col++) {
					if(col!=0)
						out.write(",");
        			out.write((String)currentTable.getColumnName(col));
    			}

			
			for (int row = 0; row < currentTable.getRowCount(); row++) {
				out.append("\n");
    			for (int col = 0; col < currentTable.getColumnCount(); col++) {
					if(col!=0)
						out.write(",");
    			    	out.write(currentTable.getValueAt(row, col).toString());
    			}
			
			}	
		 }
		 catch (IOException e) {
		 System.err.println(e.getMessage());
		 }
	}
	
	
	private static File saveFileFunc(String fileName) {
		// Instead of "(Frame) null" use a real frame, when GUI is learned
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.SAVE);
		fd.setFile(fileName+".csv");
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		System.out.println(f.getPath());
		return f;
	}
}


