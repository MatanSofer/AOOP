//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Virus;
import Population.*;
public interface IVirus {  // this interface is for all the viruses classes 3 method as requested
	
	
	public double contagionProbability(Person other);
    public boolean tryToContagion(Person one, Person two);
    public boolean tryToKill(Sick other);
    public boolean[] getMutations();
    public void setMutation(int index,boolean value);
    public String getType();
    public IVirus[] getMutations_types();
}
