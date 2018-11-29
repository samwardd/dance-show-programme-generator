package controller;

import java.util.Iterator;

import exceptions.DanceNotFoundException;
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
		// TODO Auto-generated method stub
		return null;
	}

	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

}
