package core;

import controller.TUIController;
import io.Generator;
import io.Reader;
import model.DSP;
import view.TUI;

public class DSPG {

	public static void main(String[] args) {
		DSP dsp = new DSP();
		Generator i = new Generator();
		i.generateDanceGroups(dsp, new Reader("assets/danceShowData_danceGroups.csv"));
		i.generateDances(dsp, new Reader("assets/danceShowData_dances.csv"));
		
		new TUI(new TUIController(dsp));
	}

}
