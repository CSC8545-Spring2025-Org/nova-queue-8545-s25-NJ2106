import java.time.Duration;

//This isn't the only way to approach wait time tolerance per ride.
//Use as you see fit.
public enum RideType {

    GENERAL(Duration.ofMinutes(20)), COASTER(Duration.ofMinutes(60)), THRILL(Duration.ofMinutes(45)), KIDDIE(
            Duration.ofMinutes(10)), WATER(Duration.ofMinutes(25));

    public final Duration waitTimeTolerance;

    private RideType(Duration waitTimeTolerance) {
        this.waitTimeTolerance = waitTimeTolerance;
    }

}
