import java.util.*;
public class NovaQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scann=new Scanner(System.in);
		System.out.println("Welcome to NovaQueue");
		
		System.out.println("Enter the ride id:");
		String rideid=scann.nextLine();
		
		System.out.println("Enter number of people currently in line: ");
		int peopleinline=scann.nextInt();
		
		System.out.println(rideid+" has "+peopleinline+" people in line and the wait time is 10 minutes ");

	}

}
