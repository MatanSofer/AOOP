package IO;

import java.util.ArrayList;

import IO.LogFile.Memento;

public class CareTaker {
	
	private LogFile LF; //Aggregation logfile
	private ArrayList<Memento>pathList=new ArrayList<Memento>(); //all files history 
		public CareTaker(LogFile LF)		{
			this.LF=LF;
		}
		public void addMemento(Memento m){
			pathList.add(m);
		}
		public void getMemento()		{ //call it from button reaction for UNDO then write to prev. path
			if(pathList.size()>1) {
			Memento m = pathList.get(pathList.size()-1);
			LF.setMemento(m);
			pathList.remove(pathList.size()-1);
			LF.changeFile(pathList.get(pathList.size()-1));
			}
			else {
				System.out.println("You cant restore because you have reached to the first PATH!!!");
			}
			
		}
	

}
