//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Simulation;

import GUI.MainWindow;

public class Clock 
{
	private static long time =0;  //static time of the Class
	private static long ticksPerDay = 1;
	private static boolean playing = false;
	
	public static long now() // return the current time
	{
		return time;
	}

	public static void nextTick()  //add 1 time unit the the current time
	{
		while(!playing) {
			Thread.yield();
		}
		
			try {

				Thread.sleep(1_000*MainWindow.getSliderValue());

				}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			time += 1;
			
		
	}

	public static long diffrence(long newTime) { // Gives time difference
		return (long) Math.ceil((time - newTime) / ticksPerDay) ;
	}
	public static void setticksPerDay(long ticksPerDay)
	{
		Clock.ticksPerDay = ticksPerDay;
	}
	public static long getticksPerDay()
	{
		return ticksPerDay;
	}
	public static void settime(long time)
	{
		Clock.time = time;
	}
	public static void setPlaying(boolean playing)
	{
		Clock.playing = playing;
	}
	
	public static boolean getPlay()
	{
		return playing;
	}
	
	
}