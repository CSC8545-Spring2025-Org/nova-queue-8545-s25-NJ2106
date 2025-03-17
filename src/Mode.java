import java.util.*;

public abstract class Mode {

	protected HashMap<String, Ride> map;
	
	 abstract void loadData();
	 
	 abstract void ProcessOutPut(String id, int peopleInLine);
	 
	 
	 public double TabulatePredictedWaitTime(String id, int peopleInLine) {
		  double dispatchTimeInMinutes = map.get(id).getDispatchTime() / 60.0;

       long ridersPerDispatch = map.get(id).getRidersPerDispatch();

       double pwt = Math.round((dispatchTimeInMinutes / ridersPerDispatch) * peopleInLine * 10.0) / 10.0;

       return pwt;
	 }
	 
	 public String GetStatusString(int tolerance, double pwt) {
		 String statusLabel = "";

	        if (pwt <= tolerance) {
	            statusLabel = "Normal";
	        }
	        if (pwt < 0.5 * tolerance) {
	            statusLabel = "Below Normal";
	        }

	        if (pwt > tolerance) {
	            statusLabel = "Above Normal";
	        }
	        if (pwt > 2 * tolerance) {
	            statusLabel = "Alert";
	        }
	        return statusLabel;
		 
	 }
	 
	 public int GetTolerance(String id) {
		 return (int) (map.get(id).getRideType().waitTimeTolerance.getSeconds() / 60);
	 }
	 
	 public boolean Contains(String id) {
		 return map.containsKey(id);
	 }
}
