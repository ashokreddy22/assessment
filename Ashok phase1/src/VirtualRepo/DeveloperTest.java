package VirtualRepo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DeveloperTest {
	public static void information() {
		System.out.println("*************Application Details************");
		System.out.println("Project Name :LockedMe.com");
		System.out.println("This application is developed by :");
		System.out.println(   "Name : DEVELOPER2");
		System.out.println("*********************************************");
	}

	public static void main(String[] args) throws IOException{
		DeveloperTest.information();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the path  the location where to create a directory ");
		String path = sc.next();
		System.out.println("Enter the Directory name for to store files ");
		String dir = sc.next();
		path = path + "\\" + dir;
		File file = new File(path);
		if (file.mkdir()) {
			System.out.println("Directory created");
		} else {
			System.out.println("Directory already exists");
		}
		while (true) {
			// Main menu to perform the operations
			System.out.println("--------------Main menu --------------------");
			System.out
					.println("1. Display in ascending order \n2. Business level operations  \n3.Close the Application");

			System.out.println("Enter Your below options to take a operation : ");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				SortingFiles sf = new SortingFiles();
				sf.AscendingOrder(path);
				break;
			case 2:
				BusinessOperations bo = new BusinessOperations();
				bo.operations(path);

				break;
			case 3:
				System.out.println("Thank you");
				System.exit(0);
			default:
				System.out.println("Wrong option Entered! please enter correct option.");
				break;
			}
		}
	}

}
