import java.util.HashMap;

public class Line {

    private String rideId;
    private int lineSize;
    public HashMap<String, Integer> map = new HashMap<>();
    public Line(String rideId, int size) {
        // TODO Auto-generated constructor stub
        this.rideId = rideId;
        this.lineSize = size;
        if(!map.containsKey(rideId)) {
            map.put(rideId, size);
        }
    }
    
    public Line(String ride) {
        this.rideId = ride;
    }
    
    public int GetLineData(String rideId) {
        return map.get(rideId);
    }

}
