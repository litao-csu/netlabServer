package InsertLinux;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

public class Register_openfire {
	public static void writeFile(String new_userinfo)  throws IOException {
		
		String userName = new String();
		String name = new String();
		String useremail = new String();
		String password = new String();
		System.out.println(new_userinfo);
		String[] temp = new_userinfo.split("[*]");
		
		String newUser;
		useremail = temp[1];
		userName = temp[0];
		password = temp[2];
		name = temp[3];		
		String userinfo = "{\r\n" + "\t\"new_user\"" + ": \r\n" + "\t\t{\r\n" + "\t\t\t\"username\" :"+ "\"" + userName + "\",\r\n" 
               + "\t\t\t\"name\" :"+ "\"" + name + "\",\r\n" + "\t\t\t\"email\" :"+ "\"" + useremail + "\",\r\n" + "\t\t\t\"password\" :"+ "\"" + password + "\",\r\n"
               + "\t\t\t\"create\" : \"创建用户\"\r\n" + "\t\t}\r\n" + "}\r\n";
		
		try {				
				newUser = "/home/server/openfire/config/new_user.json"; 
				
	        	FileWriter fw = new FileWriter(newUser);  
	        	PrintWriter out = new PrintWriter(fw);  
	        	out.write(userinfo);  
	        	out.println();  
	        	fw.close();  
	        	out.close();
	        } 
				catch (Exception e) {
	        	e.printStackTrace();
				}  
	}
	
	public void registeOneUser(String userName,String userId){
		String regis = null;
		String stuId = null;
		String stuName = null;
		String stuEmail = null;
		stuId = userId;
		stuName = userName;
		stuEmail = stuId + "@csu.edu.cn";
		regis = stuId+"*"+stuEmail+"*"+"zndxjsjl"+"*"+stuName;
		
		try {
			writeFile(regis);
			String cmd = "node /home/server/openfire/config/1.js";
		    System.out.println(cmd);
		    Runtime r=Runtime.getRuntime();
		    Process proc = r.exec(cmd);
		   
		 //   System.out.println(proc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) throws Exception {
		String regis = null;
		String stuId = null;
		String stuName = null;
		String stuEmail = null;
		//regis = "164611107"+"*"+"164611107@csu.edu.cn"+"*"+"zndxjsjl"+"*"+"张先凯";
		
		ReadExcel re = new ReadExcel();
		ArrayList<ArrayList<String>> info;
		info = ReadExcel.ReadLines();
		Register_openfire reg = new Register_openfire();
		
		for (int j = 0; j < info.get(0).size(); ++j){
			stuId = info.get(1).get(j);
			stuName = info.get(0).get(j);
			stuEmail = stuId + "@csu.edu.cn";
			regis = stuId+"*"+stuEmail+"*"+"zndxjsjl"+"*"+stuName;
			try {
				reg.writeFile(regis);
				String cmd = "node /home/server/openfire/config/1.js";
			  //  System.out.println(cmd);
			    Runtime r=Runtime.getRuntime();
			    Process proc = r.exec(cmd);
			    Thread.sleep(1000);
			 //   System.out.println(proc.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			regis = null;
		}
		
	}
}
