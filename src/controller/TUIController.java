package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import exceptions.DanceNotFoundException;
import io.Generator;
import io.Reader;
import model.DSP;
import model.Dance;
import model.Performer;

/**
 * A TUIController object implements the 4 features that the intended dance show programme
 * generator is expected to have.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */
public class TUIController implements Controller {
	
	private DSP dsp;
	
	/**
	 * Creation of a new TUIController
	 * 
	 * @param dsp The dance show programme in use to operate on
	 */
	public TUIController(DSP dsp) {
		this.dsp = dsp;
	}
	
	/**
	 * Provides a string representation of the the dancers in a given dance.
	 * 
	 * @param dance to find
	 * @return string of dancers
	 */
	public String listAllDancersIn(String dance) {
		// Create a new StringBuilder for easier control of building a String to return.
		StringBuilder dancers  = new StringBuilder();
		
		// Try to perform the following.
		try {
			Dance findDance = dsp.getDance(dance);			
			
			// Create an iterator to allow more control over iteration of the performers.
			Iterator<Performer> iterator = findDance.getAllPerformers().iterator();
			
			dancers.append(findDance.getName());
			dancers.append(": ");
			
			// While there are more performers to iterate over.
			while(iterator.hasNext()) {
				dancers.append(iterator.next().getForename());
				
				if(iterator.hasNext()) {
					dancers.append(", "); // Only add to string if not last in iterator
				}
			}
			dancers.append("."); // Add full stop after all items iterated over
			
		} catch (DanceNotFoundException e) {
			// Catches the exception if a dance cannot be found.
			dancers = dancers.append(e.getMessage());
		}
		dancers.append("\n\n");
		
		return dancers.toString();
	}

	/**
	 * Provides a string representation of the dances and the performers in that dance.
	 * 
	 * @return All dances and their dancers in alphabetical order.
	 */
	public String listAllDancesAndPerformers() {
		// Create a new StringBuilder for easier control of building a String to return.
		StringBuilder dancesAndPerformers = new StringBuilder();		
	
		// Iterate through all dances in the dance show programme.
		for(Dance dance : dsp.getDances().values()) {
			dancesAndPerformers.append("Dance: ");
			dancesAndPerformers.append(dance.getName());
			dancesAndPerformers.append("\n");
			
			// Create an iterator to allow more control over iteration of the performers.
			Iterator<Performer> performerIterator = dance.getAllPerformersInOrder().iterator();	
			
			dancesAndPerformers.append("Performers: ");
			
			while(performerIterator.hasNext()) {
				dancesAndPerformers.append(performerIterator.next().getForename());
				
				if(performerIterator.hasNext()) {
					dancesAndPerformers.append(", "); // Only add to string if not last in iterator
				}
			}
			dancesAndPerformers.append("." + "\n\n"); // Add full stop after all items iterated over
		}	
		
		return dancesAndPerformers.toString();
	}

	/**
	 * Provides a string representation whether the order is feasible given a subset of dances and the time needed to change costume.
	 * 
	 * @param filename The subset of dances
	 * @param gaps The time needed to change costume
	 * @return if it is feasible, and if not why not
	 */
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		// Create a new StringBuilder for easier control of building a String to return.
		StringBuilder feasibility = new StringBuilder();
		
		// Try to perform the following.
		try {
			Reader reader = new Reader(filename);		
			// Generate running order to check feasibility of.
			ArrayList<String> runningOrder = new Generator().readRunningOrder(reader);

			// For each dance in the running order.
			for(int i = 0; i < runningOrder.size(); i++) {
				Dance current = dsp.getDance(runningOrder.get(i));

				// How many dances ahead to check to ensure feasibility.
				int compareUpto = i + gaps;

				// Check every dance necessary to ensure feasibility. 
				for(int j = i + 1; j <= compareUpto; j++) {
					// If corresponding dance is within the dances in the dance show programme.
					if(j < runningOrder.size()) {
						Dance comparingTo = dsp.getDance(runningOrder.get(j));

						// Compare the two dances.
						String result = current.comparePerformersToString(comparingTo);
						// If there are performers who appear in both dances.
						if(result != null) {
							if(feasibility.toString().isEmpty()) {
								feasibility.append("The given running order is not feasible because the"
										+ " following performer(s) won't have enough time to change"
										+ " costume between:" + "\n\n"); // Only added if string is currently empty.
							}
							feasibility.append("\t" + result);
						}
					} 
				}
			}
			feasibility.append("\n");
		}
		catch (FileNotFoundException e) {
			// Catches if the provided file cannot be found.
			feasibility.append("A file with that name storing the running order could not be found." + "\n\n");
		} 
		catch (DanceNotFoundException e) {
			// Catches if a dance cannot be found.
			feasibility.append("A dance in the running order could not be found." + "\n\n");
		}			
			
		// If the string describing why a running order is infeasible is empty.
		if(feasibility.toString().trim().isEmpty()) {
			feasibility.append("The given dance order is feasible." + "\n\n");
		}
		
		return feasibility.toString();
	}

	
	/**
	 * Generates a running order of a subset of dances given the time needed to change costume.
	 * 
	 * @return either a feasible running order or a message to inform of no feasible running order
	 */
	public String generateRunningOrder(int gaps) {
		// Create a new StringBuilder for easier control of building a String to return.
		StringBuilder runningOrder = new StringBuilder();	
		
		// Attempts to generate a running order.
		ArrayList<Dance> dances = dsp.generateRunningOrder(gaps);
		
		// If there are dances in the running order.
		if(dances.size() != 0) {
			// Create a new iterator to iterate over the dances with more control.
			Iterator<Dance> iterator = dances.iterator();
			int danceNumber = 1; // Current dance number.
			
			runningOrder.append("A running order has been generated successfully:" + "\n\n");
			
			// While there are more dances to iterate over.
			while(iterator.hasNext()) {
				runningOrder.append("\t" + danceNumber + ". ");
				runningOrder.append(iterator.next().getName());
				
				if(iterator.hasNext()) {
					runningOrder.append(", "  + "\n");
					danceNumber++; // If the current dance is not the last one in the list.
				}
			}
			runningOrder.append("." + "\n\n");
		} else {
			runningOrder.append("No feasible running order could be generated." + "\n\n");
		}
		
		return runningOrder.toString();
	}
}
