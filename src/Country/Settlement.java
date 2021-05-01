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

	public void jtableMakeSick() {
		System.out.println("ss" + getSickPeopleSize());
		Person temp;
		IVirus[] random = { new ChineseVariant(), new BritishVariant(), new SouthAfricanVariant() };
		int randomVarient;
		for (int i = 0; i < 0.01 * nonSickPeople.size(); i++) {
			randomVarient = (int) Math.random() * 2;
			temp = nonSickPeople.get(i).contagion(random[randomVarient]);
			sickPeople.add(temp);
			nonSickPeople.remove(i);

		}
	}

//	 public void updateSickHealthy()
//	 {
//	
//		 for(int i = 0 ; i < people.size() ; i++)
//		 {
//			   if(people.get(i) instanceof Sick )
//				{
//					this.sickPeople.add(people.get(i));
//				}
//				else if(people.get(i) instanceof Healthy )
//				{
//					this.nonSickPeople.add(people.get(i));
//				}
//		 }
//	 }

	public boolean transferPerson(Person other, Settlement one) // will be used in future
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

	public List<Settlement> getconnectedSettlements() {
		return this.connectedSettlements;
	}

	public void appendconnectedSettlements(Settlement other) {
		this.connectedSettlements.add(other);
	}

	public void printconnectedSettlements() {
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

	public List<Person> getSickPeople() // get population list
	{
		return this.sickPeople;
	}

	public List<Person> getNonSickPeople() // get population list
	{
		return this.nonSickPeople;
	}

	public int getPeopleSize() {
		return getNonSickPeople().size() + getSickPeople().size();
	}

	public int getSickPeopleSize() {
		return sickPeople.size();
	}

	public int getNonSickPeopleSize() {
		return nonSickPeople.size();
	}

	public int getVaccineDose() {
		return this.vaccineDose;
	}

	public void setVaccineDose(int newDoses) {
		this.vaccineDose += newDoses;
	}

	public int getdeadPopulation() {
		return this.deadpopulation;
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
	

