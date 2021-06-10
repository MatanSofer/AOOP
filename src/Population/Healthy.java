//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Population;
import Location.*;

import java.util.ArrayList;
import java.util.List;

import Country.*;
import Virus.*;
import Simulation.*;


public class Healthy extends Person {
	
	private static double infectionCoefficient=1;  //static as requested
	
	public Healthy(int age , Point location , Settlement settlement) {  //constructor for all fields
		super(age , location , settlement);
	}
	public Person vaccinate() { // returns new similar person with different health condition , now he vaccinated
		Point locationCopy = new Point(getLocation());
		Vaccinated vaccinated = new Vaccinated(getAge(),locationCopy,getSettlement(),Clock.now());
		return vaccinated;
	}
	
	public Sick contagion(IVirus virus) { // returns new similar person with different health condition , now he sick
		IVirus new_virus = VirusManager.randomContagion(virus);
		Point locationCopy = new Point(getLocation());
		Sick sickperson = new Sick(getAge(),locationCopy,getSettlement(),Clock.now(),new_virus);
		return sickperson;
	}

	public double contagionProbability() { // return the contagionProbability static double
		return infectionCoefficient;
	}
	public String getType() {
		return "Healty";
	}
	
	public boolean isEqual(Healthy other) { //is equal
		return super.isEqual((Person)other) ;
	}
}
