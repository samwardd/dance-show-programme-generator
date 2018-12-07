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

public class TUIController implements Controller {
	
	private DSP dsp;
	
	public TUIController(DSP dsp) {
		this.dsp = dsp;
	}
	
	public String listAllDancersIn(String dance) {
		StringBuilder dancers  = new StringBuilder();
		
		try {
			Dance findDance = dsp.getDance(dance);			
			
			Iterator<Performer> iterator = findDance.getAllPerformers().iterator();
			
			dancers.append(findDance.getName());
			dancers.append(": ");
			
			while(iterator.hasNext()) {
				dancers.append(iterator.next().getForename());
				
				if(iterator.hasNext()) {
					dancers.append(", "); // Only add to string if not last in iterator
				}
			}
			dancers.append("."); // Add full stop after all items iterated over
			
		} catch (DanceNotFoundException e) {
			dancers = dancers.append(e.getMessage());
		}
		dancers.append("\n\n");
		
		return dancers.toString();
	}

	public String listAllDancesAndPerformers() {
		StringBuilder dancesAndPerformers = new StringBuilder();		
	
		for(Dance dance : dsp.getDances().values()) {
			dancesAndPerformers.append("Dance: ");
			dancesAndPerformers.append(dance.getName());
			dancesAndPerformers.append("\n");
			
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

	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		StringBuilder feasibility = new StringBuilder();
		
		try {
			Reader reader = new Reader(filename);		
			ArrayList<String> runningOrder = new Generator().readRunningOrder(reader);

			for(int i = 0; i < runningOrder.size(); i++) {
				Dance current = dsp.getDance(runningOrder.get(i));

				int compareUpto = i + gaps;

				for(int j = i + 1; j <= compareUpto; j++) {
					if(j < runningOrder.size()) {
						Dance comparingTo = dsp.getDance(runningOrder.get(j));

						String result = current.comparePerformersToString(comparingTo);
						if(result != null) {
							if(feasibility.toString().isEmpty()) {
								feasibility.append("The given running order is not feasible because the"
										+ " following performer(s) won't have enough time to change"
										+ " costume between:" + "\n\n");
							}
							feasibility.append("\t" + result);
						}
					} 
				}
			}
			feasibility.append("\n");
		}
		catch (FileNotFoundException e) {
			feasibility.append("A file with that name storing the running order could not be found." + "\n\n");
		} 
		catch (DanceNotFoundException e) {
			feasibility.append("A dance in the running order could not be found." + "\n\n");
		}			
			
		if(feasibility.toString().trim().isEmpty()) {
			feasibility.append("The given dance order is feasible." + "\n\n");
		}
		
		return feasibility.toString();
	}

	public String generateRunningOrder(int gaps) {
		StringBuilder runningOrder = new StringBuilder();	
		
		ArrayList<Dance> dances = dsp.generateRunningOrder(gaps);
		
		if(dances.size() > 0) {
			Iterator<Dance> iterator = dances.iterator();
			int danceNumber = 1;
			
			runningOrder.append("A running order has been generated successfully:" + "\n\n");
			
			while(iterator.hasNext()) {
				runningOrder.append("\t" + danceNumber + ". ");
				runningOrder.append(iterator.next().getName());
				
				if(iterator.hasNext()) {
					runningOrder.append(", "  + "\n");
					danceNumber++;
				}
			}
			runningOrder.append("." + "\n\n");
		} else {
			runningOrder.append("No feasible running order could be generated." + "\n\n");
		}
		
		return runningOrder.toString();
	}
}
