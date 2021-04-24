//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Population;
import Location.*;
import Country.*;
import Virus.*;
public abstract class Person
{
	private int age;	  //private fields for person ( private as requested )
	private Point location;
	private Settlement settlement;
	 
	public abstract double contagionProbability(); // abstract function returns the contagion probability
	public abstract Person contagion(IVirus other);  // abstract function returns the new sick person
	public abstract String getType();	
	public Person(int age , Point location , Settlement settlement)  //constructor for all fields
	{
		this.age=age;
		this.location = new Point(location);
		this.settlement = settlement;
	}
		   	
	public int getAge()  //return age
	{
		return age;
	}
	public Settlement getSettlement()  //return settlement
	{
		return settlement;
	}
	public Point getLocation()	//return location
	{
		return location;
	}
	public void setSettlement(Settlement set) //to set settlement
	{
		this.settlement=set;
	}

	public double getDistance(Point otherLocation) //calculate distance between to points objects
	{
		return Math.sqrt((Math.pow(location.getX()-otherLocation.getX(),2))+(Math.pow(location.getY()-otherLocation.getY(),2)));
	}

	public String toString()  //to string
	{
		return "The age of the Person is "+age+"\n"+
	    "his health condition :"+ getType() +" \\n"+
		settlement.toString()+"\n";
		 
	}
	
	public boolean isEqual(Person other) // is equal
	{
		return this.getAge() == other.getAge() &&
			   this.getLocation().isEqual(other.getLocation()) &&
			   this.getSettlement().isEqual(other.getSettlement());
	}
	   
}
