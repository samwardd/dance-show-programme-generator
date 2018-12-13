package test;

import static org.junit.Assert.*;
import org.junit.*;

import controller.Controller;
import controller.TUIController;
import io.Generator;
import io.Reader;
import model.DSP;

public class TestingRequirement4 {
	private Controller controller;

	/**
	 * Set up to allow for the tests to take place.
	 * Creates a new dance show, and provides it with suitable data.
	 * Can be operated on using the controller also created.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		DSP dsp = new DSP();
		Generator i = new Generator();
		i.generateDanceGroups(dsp, new Reader("assets/danceShowData_danceGroups.csv"));
		i.generateDances(dsp, new Reader("assets/test/dances_subset.csv"));		
		
		controller = new TUIController(dsp);
	}
	
	/**
	 * Test a running order with an acceptable time to change costume.
	 */
	@Test
	public void testGenerateRunningOrder_AcceptableGaps() {
		String result  = controller.generateRunningOrder(2);
		
		// Check whether the result contains a string to show that is has 
		// been successfully generated.
		boolean checkSuccess = result.contains("A running order has been generated successfully");
		
		// Ensure that this is the case by asserting that the result is true.
		assertTrue(checkSuccess);
	}
	
	/**
	 * Test a running order with an no time to change costume.
	 */
	@Test
	public void testGenerateRunningOrder_NoGaps() {
		String result  = controller.generateRunningOrder(0);
		
		// Check whether the result contains a string to show that is has 
		// been successfully generated.
		boolean checkSuccess = result.contains("A running order has been generated successfully");
		
		// Ensure that this is the case by asserting that the result is true.
		assertTrue(checkSuccess);
	}
	
	/**
	 * Test a running order with an unacceptable time to change costume.
	 */
	@Test
	public void testGenerateRunningOrder_UnacceptableGaps() {
		String result  = controller.generateRunningOrder(20);
		
		// Check whether the result contains a string to show that no
		// feasible running order could be generated.
		boolean checkFailure = result.contains("No feasible running order could be generated");
		
		// Ensure that this is the case by asserting the result.
		assertTrue(checkFailure);
	}
}
