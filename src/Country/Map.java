package Country;

public class Map 
{

  private Settlement [] settlement;
  
  public Map(Settlement [] other)	
  {
	  this.settlement =new Settlement[other.length];
	  for (int i = 0 ; i <other.length ; i++)
	  {
		  this.settlement[i]=other[i];
	  }
			  
  }
}
