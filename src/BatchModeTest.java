import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BatchModeTest {

	private ConsoleMode cm;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	 //bm = new BatchMode();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		cm = new ConsoleMode();
	}

	@AfterEach
	void tearDown() throws Exception {
		cm = null;
	}

	@Test
	void test() {
		assertEquals( 41.7, cm.TabulatePredictedWaitTime("WW", 250));
	}

}
