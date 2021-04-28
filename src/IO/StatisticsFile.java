package IO;

import java.io.*;
import javax.swing.JTable;

public class StatisticsFile
{
	
	
	public StatisticsFile(File file , JTable currentTable)
	{

		 try (FileWriter out = new FileWriter(file)) {
			for (int row = 0; row < currentTable.getRowCount(); row++) {
    			for (int col = 0; col < currentTable.getColumnCount(); col++) {
        			out.write(currentTable.getColumnName(col));
       			 	out.write(": ");
    			    out.write((String)currentTable.getValueAt(row, col));
					out.write("/n");
    			}
			}	
		 }
		 catch (IOException e) {
		 System.err.println(e.getMessage());
		 }
	}
}


