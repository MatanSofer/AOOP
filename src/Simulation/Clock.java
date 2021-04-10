package Simulation;

public class Clock 
{
	private static long time;
	
	public static long now()
	{
		return time;
	}

	public static void nextTick()
	{
		time++;
	}
}
