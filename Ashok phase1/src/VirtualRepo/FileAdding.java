package VirtualRepo;

import java.io.File;

public class FileAdding {
	public void createfile(String str,String path,String str1) {
		File af=new File(str+".txt");
		try {
			if(af.createNewFile()) {       //creating  a new files into a directory  that returns the boolean value
				System.out.println(str1+" Successfully  added into a directory");
			}
			else{
				System.out.println(str1+"Already Existing in a directory");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
