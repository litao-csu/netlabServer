package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class update_mysql {
//	static String profilepath="./config.properties";
	static String profilepath="/home/server/openfire/config/config.properties";
	
	//注册时，更新个人信息
	public static void userinfo_update(String userinfo){         
		Properties prop =  new  Properties(); 
		
		try{
			prop.load(new FileInputStream(new File(profilepath)));
		}catch (FileNotFoundException e) {	    
			e.printStackTrace();
		}catch (IOException e) {       
	        System.exit(-1);
	    }
	
		String driver = prop.getProperty("driver");        
		String url = prop.getProperty("url");          
		String user = prop.getProperty("user");                  
		String password = prop.getProperty("password");  
	   try {               
		   Class.forName(driver);   
		   Connection conn = DriverManager.getConnection(url, user, password);  
		   if(conn!=null)          
			   System.out.println("Succeeded connecting to device_use!");
		 //  System.out.println(userInfo);
		   String[] temp = userinfo.split("[*]");
		   String user_number = temp[1];
		 //判断数据是否为空
		   for(int i = 3;i <= 22;i++){
			   if(temp[i].equals("0"))
				   temp[i]=null;
		   }
		   String user_lab = temp[3];
		   String serial_number = temp[5];
		   String user_degree = temp[9];
		   String user_group = temp[10];
		   String user_grade = temp[11];
		   String user_major = temp[12];
		   String user_tutor = temp[13];
		   String user_advisor = temp[14];
		   String user_telephone = temp[15];
		   String user_qq = temp[16];
		   String user_email = temp[17];
		   String user_contact = temp[18];
		   String user_relationship = temp[19];
		   String user_contactPhone = temp[20];
		   String user_research = temp[21];
		   String user_degreeType = temp[22];
		   
		   String sql = "update userinfo set serial_number = ?,lab_num = ?,degree = ?,user_group = ?,grade = ?,"
		   		+ "major = ?,tutor = ?,advisor = ?,telephone = ?,qq = ?,email = ?,contact = ?,relationship = ?,"
		   		+ "contact_phone = ?,research = ?,degree_type = ? where user_number=\'"+user_number+"\'";

		    PreparedStatement ptmt = conn.prepareStatement(sql);

		    ptmt.setString(1, serial_number);
		    ptmt.setString(2, user_lab);
		    ptmt.setString(3, user_degree);
		    ptmt.setString(4, user_group);
		    ptmt.setString(5, user_grade);
		    ptmt.setString(6, user_major);
		    ptmt.setString(7, user_tutor);
		    ptmt.setString(8, user_advisor);
		    ptmt.setString(9, user_telephone);
		    ptmt.setString(10, user_qq);
		    ptmt.setString(11, user_email);
		    ptmt.setString(12, user_contact);
		    ptmt.setString(13, user_relationship);
		    ptmt.setString(14, user_contactPhone);
		    ptmt.setString(15, user_research);
		    ptmt.setString(16, user_degreeType);

		    ptmt.executeUpdate();	       
	        ptmt.close();       
	        conn.close();  
	   	}   
		 catch(ClassNotFoundException e) {  
		      System.out.println("Sorry,can`t find the Driver!");              
		      e.printStackTrace();  
		 } catch(SQLException e) {  
		 	System.out.println("SQLException");
		 	e.printStackTrace();  
		 } catch(Exception e) {  
		 	System.out.println("other Exception");
		 	e.printStackTrace();  
		 }
		} 
	
	//小程序更新个人信息时调用
	public static void userinfo_update_mysql(String userinfo){         
		Properties prop =  new  Properties(); 
		
		try{
			prop.load(new FileInputStream(new File(profilepath)));
		}catch (FileNotFoundException e) {	    
			e.printStackTrace();
		}catch (IOException e) {       
	        System.exit(-1);
	    }
	
		String driver = prop.getProperty("driver");        
		String url = prop.getProperty("url");          
		String user = prop.getProperty("user");                  
		String password = prop.getProperty("password");  
	   try {               
		   Class.forName(driver);   
		   Connection conn = DriverManager.getConnection(url, user, password);  
		   if(conn!=null)          
			   System.out.println("Succeeded connecting to device_use!");
		 //  System.out.println(userInfo);
		   String[] temp = userinfo.split("[*]");
		   String user_number = temp[1];
		 //判断数据是否为空
		   for(int i = 2;i <= 16;i++){
			   if(temp[i].equals("0"))
				   temp[i]=null;
		   }
		   String user_lab = temp[2];
		   String user_degree = temp[3];
		   String user_group = temp[4];
		   String user_grade = temp[5];
		   String user_major = temp[6];
		   String user_tutor = temp[7];
		   String user_advisor = temp[8];
		   String user_telephone = temp[9];
		   String user_qq = temp[10];
		   String user_email = temp[11];
		   String user_contact = temp[12];
		   String user_relationship = temp[13];
		   String user_contactPhone = temp[14];
		   String user_research = temp[15];
		   String user_degreeType = temp[16];
		   
		   String sql = "update userinfo set lab_num = ?,degree = ?,user_group = ?,grade = ?,major = ?,tutor = ?,"
		   		+ "advisor = ?,telephone = ?,qq = ?,email = ?,contact = ?,relationship = ?,contact_phone = ?,research = ?,"
		   		+ "degree_type = ? where user_number=\'"+user_number+"\'";

		    PreparedStatement ptmt = conn.prepareStatement(sql);

		    ptmt.setString(1, user_lab);
		    ptmt.setString(2, user_degree);
		    ptmt.setString(3, user_group);
		    ptmt.setString(4, user_grade);
		    ptmt.setString(5, user_major);
		    ptmt.setString(6, user_tutor);
		    ptmt.setString(7, user_advisor);
		    ptmt.setString(8, user_telephone);
		    ptmt.setString(9, user_qq);
		    ptmt.setString(10, user_email);
		    ptmt.setString(11, user_contact);
		    ptmt.setString(12, user_relationship);
		    ptmt.setString(13, user_contactPhone);
		    ptmt.setString(14, user_research);
		    ptmt.setString(15, user_degreeType);

		    ptmt.executeUpdate();	       
	        ptmt.close();       
	        conn.close();  
	   	}   
		 catch(ClassNotFoundException e) {  
		      System.out.println("Sorry,can`t find the Driver!");              
		      e.printStackTrace();  
		 } catch(SQLException e) {  
		 	System.out.println("SQLException");
		 	e.printStackTrace();  
		 } catch(Exception e) {  
		 	System.out.println("other Exception");
		 	e.printStackTrace();  
		 }
		} 
	
	public static void deviceuse_update_mysql(String deviceuse_info){         
		Properties prop =  new  Properties(); 
		//String profilepath="/home/server/openfire/config/config.properties";
		
		try{
			prop.load(new FileInputStream(new File(profilepath)));
		}catch (FileNotFoundException e) {	    
			e.printStackTrace();
		}catch (IOException e) {       
	        System.exit(-1);
	    }
	
		String driver = prop.getProperty("driver");        
		String url = prop.getProperty("url");          
		String user = prop.getProperty("user");                  
		String password = prop.getProperty("password"); 
		try {               
		   Class.forName(driver);   
		   Connection conn = DriverManager.getConnection(url, user, password);  
		   if(conn!=null)          
			   System.out.println("Succeeded connecting to device_use!");
		 //  System.out.println(userInfo);
		   String[] temp = deviceuse_info.split("[*]");
		   String serial_number = temp[1];
		   
		   String sql = "update device_use set device_rank = ? where device_ID=(select id from deviceinfo_dynamic where serial_number=\'"+serial_number+"\')"
				   + "and status=\'" + "领取" + "\'";

		    PreparedStatement ptmt = conn.prepareStatement(sql);

		    ptmt.setString(1, "master");
		    ptmt.executeUpdate();	       
	         ptmt.close();       
	         conn.close();  
		}   
			 catch(ClassNotFoundException e) {  
			      System.out.println("Sorry,can`t find the Driver!");              
			      e.printStackTrace();  
			 } catch(SQLException e) {  
			 	System.out.println("SQLException");
			 	e.printStackTrace();  
			 } catch(Exception e) {  
			 	System.out.println("other Exception");
			 	e.printStackTrace();  
			 }
		}
	public static void device_rankchange(String deviceuse_info){         
		Properties prop =  new  Properties(); 
		
		try{
			prop.load(new FileInputStream(new File(profilepath)));
		}catch (FileNotFoundException e) {	    
			e.printStackTrace();
		}catch (IOException e) {       
	        System.exit(-1);
	    }
	
		String driver = prop.getProperty("driver");        
		String url = prop.getProperty("url");          
		String user = prop.getProperty("user");                  
		String password = prop.getProperty("password");  
		   try {               
			   Class.forName(driver);   
			   Connection conn = DriverManager.getConnection(url, user, password);  
			   if(conn!=null)          
				   System.out.println("Succeeded connecting to device_use!");
			 //  System.out.println(userInfo);
			   String[] temp = deviceuse_info.split("[*]");
			   String user_number = temp[2];
			   
			   String sql = "update device_use set device_rank = ? where user_ID=(select id from userinfo where user_number=\'"+user_number+"\') and device_rank='master'"
					   + "and status=\'" + "领取" + "\'";

			    PreparedStatement ptmt = conn.prepareStatement(sql);

			    ptmt.setString(1, "slave");
			    ptmt.executeUpdate();	       
		         ptmt.close();       
		         conn.close();  
		}   
			 catch(ClassNotFoundException e) {  
			      System.out.println("Sorry,can`t find the Driver!");              
			      e.printStackTrace();  
			 } catch(SQLException e) {  
			 	System.out.println("SQLException");
			 	e.printStackTrace();  
			 } catch(Exception e) {  
			 	System.out.println("other Exception");
			 	e.printStackTrace();  
			 }
		}
}
