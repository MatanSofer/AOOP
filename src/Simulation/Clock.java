//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Simulation;

public class Clock 
{
	private static long time =0;  //static time of the Class
	private static long ticksPerDay = 1;
	
	public static long now() // return the current time
	{
		return time;
	}

	public static void nextTick()  //add 1 time unit the the current time
	{
		time += 1;
	}

	public static long diffrence(long newTime) { // Gives time difference
		return (long) Math.ceil((time - newTime) / ticksPerDay) ;
	}
	public void setticksPerDay(long ticksPerDay)
	{
		Clock.ticksPerDay = ticksPerDay;
	}
	public void settime(long time)
	{
		Clock.time = time;
	}
}