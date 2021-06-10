//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Population;
import Country.*;
import Location.*;
import Simulation.Clock;
import Virus.*;

public class Sick extends Person {
	private long contagiousTime;
	private IVirus virus;
	
	public Sick(int age , Point location , Settlement settlement,long contagiousTime,IVirus virus) { // constructor for all fields
		super(age,location,settlement);
		this.contagiousTime=contagiousTime;
		this.virus=virus;
	}
	public double contagionProbability() { // return the contagionProbability static double
		return 1; 
	}
    public Sick contagion(IVirus other) { //Different from the other classes , now we can't return new sick person because he already sick 
		throw new IllegalArgumentException("Already sick!");
	}
    public Person recover() { // returns new similar person with different health condition , now he covalescent 
    	Point locationCopy = new Point(getLocation());
    	Convalescent covalescent = new Convalescent(getAge(),locationCopy,getSettlement(),virus);
    	return covalescent;
    	
    }
    public boolean tryToDie()  { // calculate the chances of the sick person to die (boolean values)
    	return virus.tryToKill(this);
    }
	public IVirus getVirus() {//return virus type
		return virus;
	}
	public Long getContagiousTime() { //return contagious time
		return contagiousTime;
	}
	public Long daysFromContagion() { //returns the diffrence between the given time to the present time
		return Clock.diffrence(getContagiousTime());
	}
	public String getType() {
		return "Sick";
	}
	public String toString() {
		return super.toString() +" his contagiousTime is : " +getContagiousTime() +"\n";
	}
	public boolean isEqual(Sick other) { //isequal
		return super.isEqual((Person)other) &&
		this.getContagiousTime() == other.getContagiousTime() &&
		this.getVirus().getClass().equals(other.getVirus().getClass());
	}
}
