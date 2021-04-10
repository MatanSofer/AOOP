package Location;

public class Size 
{

	private int width;
	private int height;
	
	public Size(int width , int height)
	{
		this.width=width;
		this.height=height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public String toString() 
	{
		return "Width is : " + width +
				"Height is : " +height;
	}
}
