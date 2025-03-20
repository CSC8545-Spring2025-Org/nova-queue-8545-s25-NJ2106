import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BatchModeTest {

	private BatchMode bMode;
	
	@BeforeAll
	 void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		bMode = new BatchMode();
	}

	@AfterEach
	void tearDown() throws Exception {
		bMode = null;
	}
	
	@Test
	void testResult() {
		assertTrue( bMode.GetResult().size() == 0);
	}

	@Test
	void testProcessOutputState() {
		assertTrue(bMode.GetResult().size() == 0);
		
		bMode.ProcessOutPut("WW", 250);
		
		
		assertTrue(bMode.GetResult().size() == 1);
		
		bMode.ProcessOutPut("MC", 300);
		
		assertTrue(bMode.GetResult().size() == 2);
	}
	
	@Test
	void testCompareFileSizes() throws IOException {
		
		bMode.ProcessOutPut();
		
		Path csv1 = Paths.get("src/resources/wait-time-output-expected.csv");
        Path csv2 = Paths.get("src/resources/wait-time-output-actual.csv");
        
        List<String> lines1 = Files.readAllLines(csv1);
        List<String> lines2 = Files.readAllLines(csv2);
        
        assertEquals(lines1.size(), lines2.size());

	}
	

}
