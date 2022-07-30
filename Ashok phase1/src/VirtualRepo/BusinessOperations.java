package VirtualRepo;

import java.util.Scanner;



public class BusinessOperations {
	public void operations(String path) {
		Scanner sc=new Scanner(System.in);
		System.out.println("----- Enter into Business level Operations -------------");
		int n=5;
		while(true) {
			//Business operations menu
			System.out.println("1. Add a file into a directory \n2. Delete a file from directory  \n3.Search a file in a directory \n4.Return to main menu");
			System.out.println("\nEnter your operation : ");
			int choice=sc.nextInt();
			switch(choice) {
			    case 1:System.out.println("Enter file name to add : ");
			       String str1=sc.next();
			       FileAdding FA=new FileAdding();
			       FA.createfile(path+"\\"+str1,path,str1);
			    	break;
			    case 2:System.out.println("Enter file name to Delete : ");
			       String str2=sc.next();
			        
			       FileDelete FD=new FileDelete();
			       FD.deletefile(path+"\\"+str2,path,str2);
			    	break;
			    case 3:System.out.println("Enter file name to search : ");
			       String str3=sc.next();
			        
			       SearchFile FS=new SearchFile();
			       FS.searchingfile(path+"\\"+str3,path,str3);
			    	break;
			    case 4:
			    	System.out.println("Navigating to main menu.");
			    	return;
			    default:System.out.println("Wrong option Entered! please enter correct option.");
			    break;
			}
		}
	}
}


