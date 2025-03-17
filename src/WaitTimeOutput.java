
public class WaitTimeOutput {
    private String rideId;
    private long peopleInLineCurrently;
    private double predictedWaitTimeInMinutes;
    private String statusString;

    public WaitTimeOutput(String rideId, long peopleInLineCurrently, double predictedWaitTimeInMinutes,
            String statusString) {
        this.rideId = rideId;
        this.peopleInLineCurrently = peopleInLineCurrently;
        this.predictedWaitTimeInMinutes = predictedWaitTimeInMinutes;
        this.statusString = statusString;
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
        return this.statusString;

    }
}
