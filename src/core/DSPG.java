package core;

import java.io.FileNotFoundException;

import controller.TUIController;
import io.Generator;
import io.Reader;
import model.DSP;
import view.TUI;

/**
 * This class is used to run the dance show program generator system with the 
 * necessary arguments.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class DSPG {

	public static void main(String[] args) throws FileNotFoundException {		
		DSP dsp = new DSP(); // New dance show programme.
		Generator i = new Generator();
		i.generateDanceGroups(dsp, new Reader("assets/danceShowData_danceGroups.csv")); // Read dance groups.
		i.generateDances(dsp, new Reader("assets/danceShowData_dances.csv")); // Read dances.
		
		new TUI(new TUIController(dsp)); // Show text-based user interface.
	}

}
