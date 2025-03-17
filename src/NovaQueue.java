import java.util.*;

public class NovaQueue {

    
    
    private static int peopleInLine = 5000;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Welcome to NovaQueue");

        if (args.length == 0 || (args.length >= 1 && "console".equalsIgnoreCase(args[0]))) {

        	Prompt();
            
        }else if(args.length >= 1 && "batch".equalsIgnoreCase(args[0])) {
        	
        	BatchMode bm = new BatchMode();
        	bm.ProcessOutPut();
        	
        }else {
        	System.out.println("Invalid Input. The input should be either console or batch");
        }
    }

    public static void Prompt() {
    	
        ConsoleMode cm = new ConsoleMode();
        System.out.println("Console Mode started");
        String rideid = "";
        int peopleinline = 0;
        boolean validInput = false;
        boolean runLoop = true;
        boolean continueExecution = true;
        while (continueExecution) {

            Scanner scann = new Scanner(System.in);
            while (runLoop) {

                System.out.println("Enter the ride id:");
                rideid = scann.nextLine();

                if ("STOP".equals(rideid)) {
                    System.out.println("Thanks for using NovaQueue");
                    continueExecution = false;
                    System.exit(0);
                }
                if (!cm.Contains(rideid)) {
                    System.out.println("No ride exists for ride id - " + rideid);
                    
                }
                else {
                    runLoop = false;
                }
            }
            while (!validInput) {
                System.out.println("Enter number of people currently in line: ");

                if (scann.hasNextInt()) {
                    peopleinline = scann.nextInt();
                    if (peopleinline < 0 || peopleinline > peopleInLine) {
                        System.out.println("Enter Valid Input between 0 and 5000");

                    }
                    else {

                        validInput = true;
                        runLoop = true;
                    }
                }
                else {
                    System.out.println("Invalid input");
                    scann.next();
                }
            }
            cm.ProcessOutPut(rideid, peopleinline);
            validInput = false;

        }
    }
}
