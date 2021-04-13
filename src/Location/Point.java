package Location;

public class Point
{
	
	private int x;
	private int y;
	
	public Point(int x , int y) //constructor for all fields
	{
		this.x=x;
		this.y=y;
	}
	public Point(Point other)  //copy constructor 
	{
		this.x=other.x;
		this.y=other.y;
	}
	public int getX() //return x
	{
		return this.x;
	}
	public int getY()  //return y
	{
		return this.y;
	}
	public String toString() //to string
	{
		
		return " X is : " + x +
				" Y is : " +y;
	}

	public boolean isEqual(Point other) //is equal
	{
		return this.getX() == other.getX() && this.getY() == other.getY();
	}
}
