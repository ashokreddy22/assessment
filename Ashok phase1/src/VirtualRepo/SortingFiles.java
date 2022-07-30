package VirtualRepo;

import java.io.File;

public class SortingFiles {
	 public void AscendingOrder(String path) {
	      	
			File fr=new File(path);
			String listoffiles[]=fr.list();
			if(listoffiles.length==0) {  
				System.out.println(" files not present in the directory");
			}
			// Files are auto arranged in a Ascending order
			//So no need to perform any operation or sorting technique.
			//Displays list of files in ascending order
			else {
			System.out.println("List of files in Directory");
			for(String str :listoffiles) {
				System.out.println(str);
			}
			

		}
	}

}
