package ionixx;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int choice;
		System.out.println("enter your choice:\n1:Admin Login\n2:Agent Login\n3:Exit");
		Scanner s=new Scanner(System.in);
		choice=s.nextInt();
		Admin ad=new Admin();
		Agent ag=new Agent();
		switch(choice)
		
		{
		case 1:
		{
			ad.admin();
			break;
		}
		case 2:
		{
			ag.selling();
			break;
		}
		case 3:
			System.exit(0);
	}

}
}
