package artificialintelligence;

import java.util.ArrayList;
import java.util.Arrays;

public class Population{
	
	ArrayList<Entity> population = null;

	public Population(int populationsize) {
		ArrayList<Entity> population = new ArrayList<Entity>(populationsize);
	}
	
	public void sortPopulationDesceding(){
		//Arrays.sort(population);
	}

}
