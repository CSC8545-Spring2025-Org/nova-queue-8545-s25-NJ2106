
public class WaitTimeOutput {
    private String rideId;
    private long peopleInLineCurrently;
    private double predictedWaitTimeInMinutes;
    private String StatusString;
    
    public WaitTimeOutput(String rideId, long peopleInLineCurrently, double predictedWaitTimeInMinutes, String StatusString ) {
        this.rideId = rideId;
        this.peopleInLineCurrently = peopleInLineCurrently;
        this.predictedWaitTimeInMinutes = predictedWaitTimeInMinutes;
        this.StatusString = StatusString;
    }
    
    public String getrideId() {
        return this.rideId;
        
    }
    
    public long getPeopleInLineCurrently() {
        return this.peopleInLineCurrently;
        
    }
    
    public double getpredictedWaitTime() {
        return this.predictedWaitTimeInMinutes;
        
    }
    
    public String getStatusString() {
        return this.StatusString;
        
    }
}
