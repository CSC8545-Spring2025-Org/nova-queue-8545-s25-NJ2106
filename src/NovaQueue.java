import java.util.*;

public class NovaQueue {

    public static HashMap<String, RIDETYPE> rideType = new HashMap<String, RIDETYPE>();

    public static HashMap<String, int[]> rideInfo = new HashMap<String, int[]>();

    public static HashMap<String, String> rideName = new HashMap<String, String>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LoadInMemoryDB();
        System.out.println("Welcome to NovaQueue");
        Scanner scann = new Scanner(System.in);

        while (true) {

            System.out.println("Enter the ride id:");
            String rideid = scann.nextLine();

            if ("STOP".equals(rideid)) {
                break;
            }
            if (!rideType.containsKey(rideid)) {
                System.out.println("No ride exists for ride id - " + rideid);
                continue;
            }
            System.out.println("Enter number of people currently in line: ");
            int peopleinline = 0;
            if (scann.hasNextInt()) {
                peopleinline = scann.nextInt();
            }
            else {
                System.out.println("Invalid count for people in line");
                scann.nextLine();
                continue;
            }

            scann.nextLine();

            double pwt = ((rideInfo.get(rideid)[0] / rideInfo.get(rideid)[1]) * peopleinline) / 60.0;

            String statusLabel = "";

            int tolerance = rideType.get(rideid).getValue();
            if (pwt < 0.5 * tolerance) {
                statusLabel = "Below Normal";
            }
            if (pwt <= tolerance) {
                statusLabel = "Normal";
            }
            if (pwt > tolerance) {
                statusLabel = "Above Normal";
            }
            if (pwt > 2 * tolerance) {
                statusLabel = "Alert";
            }

            System.out.println(rideName.get(rideid) + " has " + peopleinline + " people in line and the wait time is "
                    + String.format("%.2f", pwt) + " minutes - " + statusLabel);
        }

        System.out.println("Thanks for using NovaQueue");

    }

    public static void LoadInMemoryDB() {

        rideType.put("WW", RIDETYPE.WATER);
        rideType.put("RR", RIDETYPE.COASTER);
        rideType.put("HL", RIDETYPE.THRILL);
        rideType.put("MR", RIDETYPE.GENERAL);
        rideType.put("CC", RIDETYPE.GENERAL);
        rideType.put("MC", RIDETYPE.KIDDIE);

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
