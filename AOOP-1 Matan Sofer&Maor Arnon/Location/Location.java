//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Location;

public class Location
{
   private Point position;
   private Size size;
   
   public Location(Size size , Point position) ////constructor for all fields
   {
	   this.size= new Size(size.getWidth() ,size.getHeight() ); 
	   this.position= new Point(position.getX() ,position.getY() );
	   
   }
   
   public Location(Location other) //copy constructor 
   {
	  this.size= new Size (other.getSize().getWidth() , other.getSize().getHeight()); 
	  this.position= new Point(other.getPoint().getX() ,other.getPoint().getY());
   }

	public Size getSize()  //return the Size field
	{
		return this.size;
	}
	public Point getPoint()  //return the Point field
	{
		return this.position;
	}
	public String toString() //to string
	{
		return  position.toString() 
					+'\n'+ size.toString();				
	}
	public boolean isEqual(Location other)  //is equal
	{
		return this.getPoint().isEqual(other.getPoint()) && this.getSize().isEqual(other.getSize()) ;
	}
		
}
