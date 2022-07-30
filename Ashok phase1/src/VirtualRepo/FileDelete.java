package VirtualRepo;

import java.io.File;

public class FileDelete {

		
	
void deletefile(String str,String path,String str2)
{ 
	try {
	File df=new File(str+".txt");
	if(df.delete()) {
		
		System.out.println(str2+"Successfully  deleted in a  directory path :"+path);
	}
	else {
		System.out.println("Oops..."+str2+" not found in a directory path:"+path);
	}
	}catch (Exception e) {
			
			e.printStackTrace();
		}
}
}
