package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
public class Insert_mysql {
//	static String profilepath="./config.properties";
	static String profilepath="/home/server/openfire/config/config.properties";
	
	public static String Info_insert(String userInfo){         
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
			   System.out.println("Insert user into userinfo!");
		   String[] temp = userInfo.split("[*]");
		 //判断数据是否为空
		   for(int i = 3;i <= 22;i++){
			   if(temp[i].equals("0"))
				   temp[i]=null;
		   }
		 //  System.out.println(userInfo);
		   
		   TimeZone tz =TimeZone.getTimeZone("Asia/Shanghai");
		   TimeZone.setDefault(tz);
		   Date date=new Date();
		   DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String time=format.format(date);
		   
		   String sql = "insert into userinfo (user_number,user_name,lab_num,password,serial_number,active,time0,"
		   		+ "degree,user_group,grade,major,tutor,advisor,telephone,qq,email,contact,relationship,contact_phone,research,degree_type)"
		   		+ "value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   //String sql = "insert into userinfo (user_number,user_name,lab_num,password,serial_number,active,time0,authority,degree,grade,major,tutor,adviser)value (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		   PreparedStatement pStmt = conn.prepareStatement(sql);
		   //根据数据库表列序列逐列插入数据
		   pStmt.setString(1, temp[1]);
	       pStmt.setString(2, temp[2]);
	       pStmt.setString(3, temp[3]); 
	       pStmt.setString(4, temp[4]);
	       pStmt.setString(5, temp[5]);
	       pStmt.setInt(6,1);
	       pStmt.setString(7,time);
	       pStmt.setString(8, temp[9]);
	       pStmt.setString(9, temp[10]);
	       pStmt.setString(10, temp[11]);
	       pStmt.setString(11, temp[12]);
	       pStmt.setString(12, temp[13]);
	       pStmt.setString(13, temp[14]);
	       pStmt.setString(14, temp[15]);
	       pStmt.setString(15, temp[16]);
	       pStmt.setString(16, temp[17]);
	       pStmt.setString(17, temp[18]);
	       pStmt.setString(18, temp[19]);
	       pStmt.setString(19, temp[20]);
	       pStmt.setString(20, temp[21]);
	       pStmt.setString(21, temp[22]);
	       
	     //  System.out.println(pStmt);
	       pStmt.executeUpdate();
	       pStmt.close();       
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
		   return userInfo;
	} 
	public static String Deviceinfo_insert(String deviceInfo){         
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
			   System.out.println("Insert device into deviceinfo_dynamic!");
		   String[] temp = deviceInfo.split("[*]");
		  //  System.out.println(userInfo);
		   String memory_capacity = temp[6];
		   String disk_capacity = temp[7];
		   TimeZone tz =TimeZone.getTimeZone("Asia/Shanghai");
		   TimeZone.setDefault(tz);
		   Date date=new Date();
		   DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String time=format.format(date);
		   
		   String sql = "insert into deviceinfo_dynamic (serial_number,CPU_type,memory_capacity,disk_capacity,change_time,authority)value (?,?,?,?,?,?)"; 
		   PreparedStatement pStmt = conn.prepareStatement(sql);
		   //根据数据库表列序列逐列插入数据
	       pStmt.setString(1, temp[5]); 
	       pStmt.setString(2, temp[8]);
	       pStmt.setString(3, memory_capacity);
	       pStmt.setString(4, disk_capacity);
	       pStmt.setString(5, time);
	       pStmt.setInt(6, 3);	       
	     //  System.out.println(pStmt);
	       pStmt.executeUpdate();
	       pStmt.close();       
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
	    return deviceInfo;
	}
	
	public static void deviceinfo_static_insert(String serial_number){
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
			   System.out.println("Insert device_ID into deviceinfo_static!");
		   String sql1 = "select id from deviceinfo_dynamic where serial_number=" + "\'" +serial_number+ "\' ";
		   int device_ID = 0;
		   Statement statement = conn.createStatement();
		   ResultSet rs = statement.executeQuery(sql1);
	       if(rs.next()){
	    	   device_ID = rs.getInt("id");
	       }
		   String sql2 = "insert into deviceinfo_static(device_ID)value("+device_ID+")"; 
		   PreparedStatement pStmt = conn.prepareStatement(sql2);
		   
	       pStmt.executeUpdate();
	       pStmt.close();       
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
	
	public static void device_use_insert(String userID,String deviceID,String device_rank){         
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
		   int user_ID = Integer.parseInt(userID);
		   int device_ID = Integer.parseInt(deviceID);
		 //  System.out.println(userInfo);
		   
		   TimeZone tz =TimeZone.getTimeZone("Asia/Shanghai");
		   TimeZone.setDefault(tz);
		   Date date=new Date();
		   DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String time=format.format(date);
		   
		   String sql = "insert into device_use (device_ID,user_ID,device_rank,status,time)value (?,?,?,?,?)"; 
		   PreparedStatement pStmt = conn.prepareStatement(sql);
		   //根据数据库表列序列逐列插入数据
		   pStmt.setInt(1, device_ID);
	       pStmt.setInt(2, user_ID);
	       pStmt.setString(3, device_rank);
	       pStmt.setString(4, "领取"); 
	       pStmt.setString(5, time);	       
	       pStmt.executeUpdate();
	       pStmt.close();       
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


