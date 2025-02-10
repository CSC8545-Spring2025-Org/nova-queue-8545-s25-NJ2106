import java.util.*;

public class NovaQueue {

    public static HashMap<String, RideType> rideType = new HashMap<>();

    public static HashMap<String, int[]> rideInfo = new HashMap<String, int[]>();

    public static HashMap<String, String> rideName = new HashMap<String, String>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Welcome to NovaQueue");
        
       
        if(args.length == 0) {
            
            ConsoleMode();
        }
        String input = args[0].toLowerCase();
        switch(input) {
            case "console":
                
                ConsoleMode();
                break;
            case "batch":
                BatchMode();
                break;
            default:
                System.out.println("Invalid Input. The input should be either console or batch");
        }
        ClearInMemoryDB();
        
       }
    
    private static void BatchMode() {
        // TODO Auto-generated method stub
        System.out.println("Batch Mode started");
        CsvDataReader csvread = new CsvDataReader();
        List<Ride> rideData = csvread.readRideData();
        HashMap<String, Integer> lineData = csvread.readLineData();
        LoadInMemoryDBForBatchMode(rideData);
        List<WaitTimeOutput> result = new ArrayList<>();
        for(int i = 0 ; i  < rideData.size(); i ++) {
            
            String rideId = rideData.get(i).getId();
            int peopleInLine =  lineData.get(rideId);
            double waitTime = CalculateWaitTime(rideId, peopleInLine);
            int tolerance = GetTolerance(rideId);
            String statusLabel = GetStatusString(tolerance, waitTime); 
            WaitTimeOutput output = new WaitTimeOutput(rideId, peopleInLine, waitTime,statusLabel );
            result.add(output);
        }
        CsvDataWriter writer = new CsvDataWriter();
        writer.writePredictionData(result);
    }
    
    public static void WriteToCsvFile(){
        
    }

    public static void ConsoleMode() {
        LoadInMemoryDB();
        System.out.println("Console Mode started");
        String rideid = "";
        int peopleinline = 0;
        boolean validInput = false;
        while(true) {
            
            Scanner scann = new Scanner(System.in);
            while (true) {
                
                System.out.println("Enter the ride id:");
                rideid = scann.nextLine();
    
                if ("STOP".equals(rideid)) {
                    System.out.println("Thanks for using NovaQueue");
                    System.exit(0);
                }
                if (!rideType.containsKey(rideid)) {
                    System.out.println("No ride exists for ride id - " + rideid);
                    continue;
                }else {
                    break;
                }
            }
            while(!validInput) {
                System.out.println("Enter number of people currently in line: ");
                
                if(scann.hasNextInt()) {
                    peopleinline = scann.nextInt();
                    if(peopleinline < 0 || peopleinline > 5000) {
                        System.out.println("Enter Valid Input between 0 and 5000");
                        
                    }else {
                       
                       validInput = true;
                    }
                }else {
                    System.out.println("Invalid input");
                    scann.next();
                }
                
    
                
            }
                
                double waitingTime = CalculateWaitTime(rideid, peopleinline);
                int tolerance =  GetTolerance(rideid);
                String statusLabel = GetStatusString(tolerance, waitingTime);
                
    
                System.out.println(rideName.get(rideid) + " has " + peopleinline + " people in line and the wait time is "
                        + String.format("%.2f", waitingTime) + " minutes - " + statusLabel);
                
                validInput = false;
                
        }
    }
    
    public static int GetTolerance(String rideId) {
        return (int) (rideType.get(rideId).waitTimeTolerance.getSeconds() / 60);
    }
    
    public static double CalculateWaitTime(String rideId, int peopleInLine) {
        double dispatchTimeInMinutes = rideInfo.get(rideId)[0] / 60.0;
        //System.out.print("dispatchTimeInMinutes - " + dispatchTimeInMinutes);
        int ridersPerDispatch = rideInfo.get(rideId)[1];
        //System.out.print("ridersPerDispatch - " + ridersPerDispatch);
        double pwt =  Math.round((dispatchTimeInMinutes / ridersPerDispatch) * peopleInLine * 10.0) / 10.0;
        //System.out.print("pwt - " + pwt);
       return pwt;
    }
    
    public static String GetStatusString(int tolerance, double pwt) {
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

        

    public static void ClearInMemoryDB() {
        rideType.clear();
        rideInfo.clear();
        rideName.clear();
    }
    
    public static void LoadInMemoryDBForBatchMode(List<Ride> rideData) {
        for(int i = 0 ; i < rideData.size(); i++) {
            Ride r = rideData.get(i);
            if(!rideType.containsKey(r.getId())) {
                rideType.put(r.getId(), r.getRideType());
                rideInfo.put(r.getId(), new int[] {(int) r.getDispatchTime(), (int) r.getRidersPerDispatch()});
                rideName.put(r.getId(), r.getName());
            }
        }
    }

    public static void LoadInMemoryDB() {

        rideType.put("WW", RideType.WATER);
        rideType.put("RR", RideType.COASTER);
        rideType.put("HL", RideType.THRILL);
        rideType.put("MR", RideType.GENERAL);
        rideType.put("CC", RideType.GENERAL);
        rideType.put("MC", RideType.KIDDIE);

        rideInfo.put("WW", new int[] { 60, 6 });
        rideInfo.put("RR", new int[] { 90, 48 });
        rideInfo.put("HL", new int[] { 30, 12 });
        rideInfo.put("MR", new int[] { 40, 24 });
        rideInfo.put("CC", new int[] { 180, 50 });
        rideInfo.put("MC", new int[] { 45, 20 });

        rideName.put("WW", "Wildcat Waterfall");
        rideName.put("RR", "Roarin' Racer");
        rideName.put("HL", "Haunted Lab");
        rideName.put("MR", "Mine Ride");
        rideName.put("CC", "Cats Carousel");
        rideName.put("MC", "Mini Cat");

    }

    public enum RIDETYPE {
        GENERAL(20), COASTER(60), KIDDIE(10), WATER(25), THRILL(45);

        private final int value;

        private RIDETYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
