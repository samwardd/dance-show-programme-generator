package io;

import model.DSP;
import model.DanceGroup;
import model.Pupil;

import java.util.ArrayList;

public class Generator {
	
	public Generator(DSP dsp, Reader reader) {
		generateDanceGroups(dsp, reader);
	}
	
	public void generateDanceGroups(DSP dsp, Reader reader) {
		ArrayList<ArrayList<String>> groups = reader.readFile();

		for(ArrayList<String> readGroup : groups) {
			DanceGroup group = new DanceGroup(readGroup.get(0));
			
			for (int i = 1; i < readGroup.size(); i++) {
				Pupil pupil = new Pupil(readGroup.get(i));
				group.addPupil(pupil);
			}
			dsp.addGroup(group);
		}
	}
	
}
