//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;

import java.util.*;
import Population.*;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import Location.*;

public class Settlement {

	private String name; // all fields the included in settlement
	private Location location;
	private RamzorColor ramzorcolor;
	private double ramzorRating; // this is additional field for saving ramzor VALUE
	private int maxPopulation;
	private int vaccineDose = 0;
	private int deadpopulation = 0;
	private List<Settlement> connectedSettlements = new ArrayList<Settlement>();
	private List<Person> nonSickPeople;
	private List<Person> sickPeople = new ArrayList<Person>();

	public Settlement() // default constructor (there isn't use now , maybe in future )
	{
		this.name = "NULL";
		this.location = null;
		this.nonSickPeople = null;
		this.ramzorcolor = null;
		this.ramzorRating = 0.4;
		this.vaccineDose = 0;
	}

	public Settlement(String name, Location location, List<Person> nonsick, RamzorColor ramzorcolor, int maxPopulation) // constructor
																														// for
																														// all
																														// fields
	{
		this.name = new String(name);
		this.location = new Location(location);
		this.nonSickPeople = new ArrayList<Person>(nonsick);
		this.ramzorcolor = ramzorcolor;
		this.maxPopulation = maxPopulation;
		this.vaccineDose = 0;
	}

	public Settlement(Settlement other) // copy constructor
	{
		this.name = new String(other.getName());
		this.location = new Location(other.getLocation());
		this.nonSickPeople = other.nonSickPeople;
		this.ramzorcolor = other.getColor();
		this.sickPeople = other.getSickPeople();
		this.maxPopulation = other.maxPopulation;

	}

	public RamzorColor calculateRamzorGrade() // this method implemented in "sons"
	{
		return ramzorcolor;
	}

	public double contagiousPercent() // calculate the percent value between 0 to 1 of sick people
	{
		double precent = (double) sickPeople.size() / ((double) getPeopleSize());

		return precent; // return the percent
	}

	public String contagiousPrecentString() {

		double precent = contagiousPercent();
		int precent_i = (int) ((precent) * 100);
		String precent_s = precent_i + "%";
		return precent_s;
	}

	public static Point randomLocation(Point point, Size size) // return random location in the settle
	{
		int x = ((int) (Math.random() * size.getWidth())) + point.getX();
		int y = ((int) (Math.random() * size.getHeight())) + point.getY();
		return new Point(x, y);
	}

	public boolean addPerson(Person other) // add another person to settle people array
	{
		if (other instanceof Sick) {
			this.sickPeople.add(other);
		} else {
			this.nonSickPeople.add(other);
		}
		return true;
	}

	public void jtableMakeSick() //for update add sick button in random varient
	{
		
		Person temp;
		IVirus[] random = { new ChineseVariant(), new BritishVariant(), new SouthAfricanVariant() };
		int randomVarient;
		for (int i = 0; i < 0.01 * nonSickPeople.size(); i++) {
			randomVarient = ((int) (Math.random() * 3));
			temp = nonSickPeople.get(i).contagion(random[randomVarient]);
			sickPeople.add(temp);
			nonSickPeople.remove(i);

		}
	}


	public boolean transferPerson(Person other, Settlement one) // for transfer person between settles
	{
		if (this.getPeopleSize() >= this.maxPopulation) {
			return false;
		}
		double randomProbability = Math.random();
		if (one.getColor().getInSettleProbability()
				* other.getSettlement().getColor().getInSettleProbability() <= randomProbability) {
			return false;
		}
		return true;
	}

	public List<Settlement> getconnectedSettlements() {  //returns connected settle array
		return this.connectedSettlements;
	}

	public void appendconnectedSettlements(Settlement other) { //add connected settle
		this.connectedSettlements.add(other);
	}

	public void printconnectedSettlements() {  //print all connected settles
		System.out.println("For settlement " + getName() + " connected settlements are :");
		for (int i = 0; i < this.connectedSettlements.size(); i++) {
			System.out.println(this.connectedSettlements.get(i).getName());
		}
	}

	public void setColor(RamzorColor newColor) // set the ramzor color
	{
		this.ramzorcolor = newColor;

	}

	public double getRamzorRating() // get ramzor rating
	{
		return this.ramzorRating;
	}

	public void setRamzorRating(double newRating) // set ramzor rating
	{
		this.ramzorRating = newRating;
	}

	public RamzorColor getColor() // get ramzor color
	{
		return this.ramzorcolor;
	}

	public List<Person> getSickPeople() // get sick population list
	{
		return this.sickPeople;
	}

	public List<Person> getNonSickPeople() // get non sick population list
	{
		return this.nonSickPeople;
	}

	public int getPeopleSize() {   //get all population size
		return getNonSickPeople().size() + getSickPeople().size();
	}

	public int getSickPeopleSize() { //get sickpeople size
		return sickPeople.size();
	}

	public int getNonSickPeopleSize() { //get non sick people size
		return nonSickPeople.size();
	}

	public int getVaccineDose() {  //returns number of vaccineDoses
		return this.vaccineDose;
	}

	public void addVaccineDose(int newDoses) { //for add vaccine button
		this.vaccineDose += newDoses;
	}

	public int getdeadPopulation() {  //return dead population number
		return this.deadpopulation;
	}

	public void reduceOneVaccineDose() { //reduce number of vaccine doses
		this.vaccineDose -= 1;
	}
	public String getName() // return name
	{
		return this.name;
	}

	public Location getLocation() // return location
	{
		return this.location;
	}

	public String getType() {
		return "Settlement";
	}

	public String toString() // to string
	{
		return "The name is: " + name + "\nThe location on the map is:" + location + "\nThe ramzor color is: "
				+ ramzorcolor + "\n";
	}

	public boolean isEqual(Settlement other) // is equal
	{
		return (this.getName() == other.getName() && this.getLocation() == other.getLocation()
				&& this.getSickPeople() == other.getSickPeople() && this.getColor() == other.getColor()
				&& this.getRamzorRating() == other.getRamzorRating()
				&& this.getNonSickPeople() == other.getNonSickPeople());
	}
	
	
	
}
	

