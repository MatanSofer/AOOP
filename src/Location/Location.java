package Location;

public class Location
{
   private Point position;
   private Size size;
   
   public Location(Size size , Point position)
   {
	   this.size= new Size(size.getWidth() ,size.getHeight() ); 
	   this.position= new Point(position.getX() ,position.getY() );
	   
   }
   
   public Location(Location other)
   {
	  this.size= new Size (other.getSize().getWidth() , other.getSize().getHeight()); 
	  this.position= new Point(other.getPoint().getX() ,other.getPoint().getY());
   }
   public String toString() 
   {
		
		return  position.toString() 
				+'\n'+ size.toString();
				
		
	}
	public Size getSize()
	{
		return this.size;
	}
	public Point getPoint()
	{
		return this.position;
	}
   
}
