import java.util.HashMap;

public class ConsoleMode extends Mode {
	
	private String consoleText = null;
	private final int PeopleInLine = 5000;
	public ConsoleMode() {
		loadData();
	}

	@Override
	void loadData() {
		// TODO Auto-generated method stub
		map = new HashMap<>();
		
		
		map.put("WW", new Ride("WW", "WildCat Waterfall", RideType.WATER, 60, 6));
		
		map.put("RR", new Ride("RR", "Roarin' Racer", RideType.COASTER, 90, 48));
		map.put("HL", new Ride("HL","Haunted Lab",RideType.THRILL, 30, 12 ));
		
		map.put("MR", new Ride("MR", "Mine Ride", RideType.GENERAL,40,24));
		map.put("CC", new Ride("CC", "Cats Carousel", RideType.GENERAL,180,50));
		map.put("MC", new Ride("MR", "Mini Cat", RideType.KIDDIE,45,20));
		
	}

	public void ProcessOutPut(String id, int peopleInLine) {
		double wt = TabulatePredictedWaitTime(id, peopleInLine);
		int tolerance = GetTolerance(id);
		String status = GetStatusString(tolerance, wt);
		
		ProcessOutPut(map.get(id).getName(), peopleInLine, wt, status);
	}
	
	public void ProcessOutPut(String name, int peopleInLine, double waitTime, String statusString) {
		consoleText = name + " has " + peopleInLine + " people in line and the wait time is "
                + String.format("%.2f", waitTime) + " minutes - " + statusString;
	}
	
	public String GetConsoleText() {
		return consoleText;
	}
	
	public boolean IsValidInput(int peopleInLine) {
		
		return peopleInLine > 0 && peopleInLine <= PeopleInLine;
			
	}

	
}
