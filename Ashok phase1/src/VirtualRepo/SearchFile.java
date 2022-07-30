package VirtualRepo;

import java.io.File;

public class SearchFile {

	void searchingfile(String str, String path,String str3) {
		try {
			File sf = new File(str+".txt" );
			
					if (sf.exists()) { 
						
				
					System.out.println(str3 + "file present in a directory path" + (path));
				} else {
					System.out.println(str3+ "file is not found in a directory" + (path));
				}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
