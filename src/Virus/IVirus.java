//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Virus;
import Population.*;

public interface IVirus {  // this interface is for all the viruses classes 3 method as requested
	public double contagionProbability(Person other); //returns the probability of a virus to contagion another person
    public boolean tryToContagion(Person one, Person two); //tries to contagion another person and returns if succeeded
    public boolean tryToKill(Sick other); // trying to kill a person and returns if succeeded
    public String getType(); //returns type of virus in string format
    public Boolean isEqual(IVirus other); //checks if the virus is rqual to another virus
}
