package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class FactorySettlements { //Factory called from simulation file 
	public static Settlement getSettlement(String settlementType,String name , Location location, List<Person> people , RamzorColor ramzorcolor,int maxPopulation) {
		if (settlementType == null) { return null; }
		if (settlementType.equalsIgnoreCase("KIBBUTZ")) { return new Kibbutz(name ,location,people , ramzorcolor,maxPopulation); }
		else if (settlementType.equalsIgnoreCase("MOSHAV")) { return new Moshav(name ,location,people , ramzorcolor,maxPopulation); }
		else if (settlementType.equalsIgnoreCase("CITY")) { return new City(name ,location,people , ramzorcolor,maxPopulation); }
		return null;
}

}
