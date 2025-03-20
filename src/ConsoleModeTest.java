import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleModeTest {

	private ConsoleMode cm;
	

	@BeforeEach
	void setUp() throws Exception {
		cm = new ConsoleMode();
	}

	@AfterEach
	void tearDown() throws Exception {
		cm = null;
	}

	@Test
	void testWaitTimeWW() {
		assertEquals( 41.699998, cm.TabulatePredictedWaitTime("WW", 250), 0.0001); //tolerance
		
		assertNotEquals(22, cm.TabulatePredictedWaitTime("WW", 250));
	}
	
	@Test
	void testWaitTimeForMC() {
		assertEquals( 11.3, cm.TabulatePredictedWaitTime("MC", 300));
		
		assertNotEquals(22, cm.TabulatePredictedWaitTime("HL", 250));
	}
	
	@Test
	void testStringIdExists() {
		assertTrue(cm.Contains("WW"));
		
		assertFalse(cm.Contains("WL"));
		
		assertTrue(cm.Contains("MC"));
	}
	
	@Test
	void testPeopleInLineInput() {
		assertTrue(cm.IsValidInput(5000));
		assertFalse(cm.IsValidInput(5001));
		assertFalse(cm.IsValidInput(0));
		assertFalse(cm.IsValidInput(-100));
	}
	
	@Test
	void testProcessOutPut() {
		assertTrue(cm.GetConsoleText() == null);
		cm.ProcessOutPut("WW", 250);
		assertTrue(cm.GetConsoleText().length() >  1);
	}

}
