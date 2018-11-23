package core;

import exceptions.DanceGroupNotFoundException;
import io.Generator;
import io.Reader;
import model.DSP;

public class DSPG {

	public static void main(String[] args) throws DanceGroupNotFoundException {
		DSP dsp = new DSP();
		Generator i = new Generator(dsp, new Reader("assets/danceShowData_danceGroups.csv"));
	}

}
