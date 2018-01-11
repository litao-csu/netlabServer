package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class use_mysql {  
//	static String profilepath="./config.properties";
	static String profilepath="/home/server/openfire/config/config.properties";
    public use_mysql() {
		super();
	}
    
    //从数据库中读取所在建筑、实验室、所在项目组等信息
	public static String getInitInfo(){
    	
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
		
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		StringBuffer sb4 = new StringBuffer();
		StringBuffer sb5 = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		          
		try {               
    	  	Class.forName(driver);  
    	  	Connection conn = DriverManager.getConnection(url, user, password);  
    	  	if(!conn.isClosed())          
    	  		System.out.println("Succeeded connecting to the Database!");               
    	  	Statement statement = conn.createStatement();
    	  	String sql1 = "select id,building from lab_building";
    	  	String sql2 = "select lab_name from lab_name";
    	  	String sql3 = "select group_study from group_study";
    	  	String sql4 = "select grade from user_grade";
    	  	
          
    	  	ResultSet rs1 = statement.executeQuery(sql1);
    	  	//查询数据库后返回数据
	         while(rs1.next())  
	         {         
	        	 sb1.append(rs1.getInt("id"));
	        	 sb1.append(",");
	        	 sb2.append(rs1.getString("building"));
	        	 sb2.append(",");       	
      	 
	         }
		     rs1.close();  
		     
		     ResultSet rs2 = statement.executeQuery(sql2);
	         while(rs2.next())  
	         {         
	        	 sb3.append(rs2.getString("lab_name"));
	        	 sb3.append(",");	        	
      	 
	         }
		     rs2.close(); 
		     
		     ResultSet rs3 = statement.executeQuery(sql3);
	         while(rs3.next())  
	         {         
	        	 sb4.append(rs3.getString("group_study"));
	        	 sb4.append(",");	        	
      	 
	         }
		     rs3.close(); 
		     
		     ResultSet rs4 = statement.executeQuery(sql4);
	         while(rs4.next())  
	         {         
	        	 sb5.append(rs4.getString("grade"));
	        	 sb5.append(",");	        	
      	 
	         }
		     rs4.close(); 
		     conn.close(); 
		  }   
		  catch(ClassNotFoundException e) {  
		      System.out.println("Sorry,can`t find the Driver!");              
		      e.printStackTrace();  
		  } catch(SQLException e) {  
			  e.printStackTrace();  
		  } catch(Exception e) {  
			  e.printStackTrace();  
		  }
		
		sb.append(sb1.toString());
		sb.append(";");
		sb.append(sb2.toString());
		sb.append(";");
		sb.append(sb3.toString());
		sb.append(";");
		sb.append(sb4.toString());
		sb.append(";");
		sb.append(sb5.toString());
//		System.out.println(sb.toString());
		String total = sb.toString();
		
		return total;
    }
    
    public static String judgeUserInfo(String user_number){
    	
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
		String userID = new String();
          try {               
        	  	Class.forName(driver);  
        	  	Connection conn = DriverManager.getConnection(url, user, password);  
        	  	if(!conn.isClosed())          
        	  		System.out.println("Succeeded connecting to the Database!");               
        	  	Statement statement = conn.createStatement();
//		        	  	String[] Get_info = clientInputStr.split("[*]");
//		        	  	String user_number = Get_info[1];
        	  	String sql = "select * from userinfo where user_number=\'"+user_number+"\' and active=1";
        	  	ResultSet rs = statement.executeQuery(sql);
        	  	//查询数据库后返回数据，每列用*隔开
		         if(rs.next())  
		         {         
		        	 userID = rs.getString("id");	        	 		   		        	 
		         }  
		         else 
		         {
		        	 userID = "userinfo_unregister";
		         }
         	    rs.close();       
         	    conn.close();  
         }   
		  catch(ClassNotFoundException e) {  
		   System.out.println("Sorry,can`t find the Driver!");              
		   e.printStackTrace();  
		  } catch(SQLException e) {  
		   e.printStackTrace();  
		  } catch(Exception e) {  
		   e.printStackTrace();  
		  }
		  return userID;
    }
    public static String[] getUserInfo(String serial_number){
    	
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
		
		String[] useri = new String[37];
		for(int i = 0;i < 37;i++)
			useri[i] = "";
		          
		try {               
    	  	Class.forName(driver);  
    	  	Connection conn = DriverManager.getConnection(url, user, password);  
    	  	if(!conn.isClosed())          
    	  		System.out.println("Succeeded connecting to the Database!");               
    	  	Statement statement = conn.createStatement();
    	  	String sql = "select * from userinfo where id =(select user_ID from device_use where device_ID="
    	  			+ "(select id from deviceinfo_dynamic where serial_number=\'"+serial_number+"\') and status=\'"+"领取"+"\') and active=1";
          
    	  	ResultSet rs = statement.executeQuery(sql);
    	  	//查询数据库后返回数据，每列用*隔开
	         if(rs.next())  
	         {         
	        	 useri[0] = rs.getString("user_number");
	        	 useri[1] = "*";
	        	 useri[2] = rs.getString("password");	
	        	 useri[3] = "*";
	        	 useri[4] = rs.getString("serial_number");
	        	 useri[5] = "*";
	        	 useri[6] = rs.getString("user_name");
	        	 useri[7] = "*";
	        	 useri[8] = rs.getString("lab_num");
	        	 useri[9] = "*";
	        	 useri[10] = rs.getString("degree");
	        	 useri[11] = "*";
	        	 useri[12] = rs.getString("user_group");
	        	 useri[13] = "*";
	        	 useri[14] = rs.getString("grade");
	        	 useri[15] = "*";
	        	 useri[16] = rs.getString("major");
	        	 useri[17] = "*";
	        	 useri[18] = rs.getString("tutor");
	        	 useri[19] = "*";
	        	 useri[20] = rs.getString("advisor");
	        	 useri[21] = "*";
	        	 useri[22] = rs.getString("telephone");
	        	 useri[23] = "*";
	        	 useri[24] = rs.getString("qq");
	        	 useri[25] = "*";
	        	 useri[26] = rs.getString("email");
	        	 useri[27] = "*";
	        	 useri[28] = rs.getString("contact");
	        	 useri[29] = "*";
	        	 useri[30] = rs.getString("relationship");
	        	 useri[31] = "*";
	        	 useri[32] = rs.getString("contact_phone");
	        	 useri[33] = "*";
	        	 useri[34] = rs.getString("research");
	        	 useri[35] = "*";
	        	 useri[36] = rs.getString("degree_type");
	        		 
//	        	 System.out.println("use_mysql:"+useri[0] + useri[1] + useri[2]+ useri[3] + useri[4]+ useri[5] + useri[6] + useri[28] + useri[30]);
	        	 
	         }  
	         else 
	         {
				useri[0] = "####";
	         }
		   rs.close();       
		   conn.close(); 
		   }   
		  catch(ClassNotFoundException e) {  
		   System.out.println("Sorry,can`t find the Driver!");              
		   e.printStackTrace();  
		  } catch(SQLException e) {  
		   e.printStackTrace();  
		  } catch(Exception e) {  
		   e.printStackTrace();  
		  }
		  return useri;
    }
    public static String[] getDeviceinfo_dynamic(String serial_number){
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
		
		String[] deviceinfo ={"", "", ""};
        try {               
      	  	Class.forName(driver);  
      	  	Connection conn = DriverManager.getConnection(url, user, password);  
      	  	if(!conn.isClosed())          
      	  		System.out.println("Succeeded connecting to the deviceinfo_dynamic Database!");               
      	  	Statement statement = conn.createStatement();            
      	  	String sql = "select * from deviceinfo_dynamic where serial_number=\'"+serial_number+"\' ";
      	  	ResultSet rs = statement.executeQuery(sql);
      	  	//查询数据库后返回数据，每列用*隔开
	        if(rs.next())  
	        {         
	        	deviceinfo[0] = rs.getString("id");
	        	deviceinfo[1] = "*";
	        	deviceinfo[2] = rs.getString("authority");
	//        	deviceinfo[2] = rs.getString("asset_number");
	//        	deviceinfo[3] = "*";
	//        	deviceinfo[4] = rs.getString("user_status");
	//        	deviceinfo[5] = "*";
	//        	deviceinfo[6] = rs.getString("serial_number");
	//        	deviceinfo[7] = "*";
	//        	deviceinfo[8] = rs.getString("CPU_type");
	//        	deviceinfo[9] = "*";
	//        	deviceinfo[10] = rs.getString("memory_capacity");
	//        	deviceinfo[11] = "*";
	//        	deviceinfo[12] = rs.getString("disk_capacity");
	//        	deviceinfo[13] = "*";
	//        	deviceinfo[14] = rs.getString("change_time");
	//        	deviceinfo[15] = "*";
	//        	deviceinfo[16] = rs.getString("notes");
	//        	
//		      	System.out.println(deviceinfo[0] + deviceinfo[1] + deviceinfo[2]);
	       }  
	       else 
	       {
	    	   deviceinfo[0] = "device_unregister";
	       }
		   rs.close();       
		   conn.close();  
		}   
		catch(ClassNotFoundException e) {  
		 System.out.println("Sorry,can`t find the Driver!");              
		 e.printStackTrace();  
		} catch(SQLException e) {  
		 e.printStackTrace();  
		} catch(Exception e) {  
		 e.printStackTrace();  
		}
		return deviceinfo;
    }
    
    public static String[] getDevice_useinfo(String serial_number){
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
 		
 		String[] deviceinfo ={"", "", ""};
         try {               
       	  	Class.forName(driver);  
       	  	Connection conn = DriverManager.getConnection(url, user, password);  
       	  	if(!conn.isClosed())          
       	  		System.out.println("Succeeded connecting to the deviceinfo_dynamic Database!");               
       	  	Statement statement = conn.createStatement();            
       	  	String sql = "select device_rank from device_use where device_ID=(select id from deviceinfo_dynamic where serial_number=\'"+serial_number+"\')"
       	  		+ "and status=\'" + "领取" + "\'" ;
//       	  	System.out.println(sql);
       	  	ResultSet rs = statement.executeQuery(sql);
       	  	//查询数据库后返回数据，每列用*隔开
         if(rs.next())  
         {         
         	deviceinfo[0] = rs.getString("device_rank");
         	
// 	      	System.out.println("查询设备等级为"+deviceinfo[0]);
         }  
 		 rs.close();       conn.close();  }   
 		catch(ClassNotFoundException e) {  
 		 System.out.println("Sorry,can`t find the Driver!");              
 		 e.printStackTrace();  
 		} catch(SQLException e) {  
 		 e.printStackTrace();  
 		} catch(Exception e) {  
 		 e.printStackTrace();  
 		}
 		return deviceinfo;
     }
    public static String judgeDevice_useinfo(String user_number){
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
  		
  		String deviceinfo = new String();
          try {               
        	  	Class.forName(driver);  
        	  	Connection conn = DriverManager.getConnection(url, user, password);  
        	  	if(!conn.isClosed())          
        	  		System.out.println("Succeeded connecting to the deviceinfo_dynamic Database!");               
        	  	Statement statement = conn.createStatement();            
        	  	String sql = "select device_ID from device_use where user_ID=(select id from userinfo where user_number=\'"+user_number+"\' and active=1) "
        	  			+ "and device_rank=\'" + "master" + "\'" + "and status=\'" + "领取" + "\'";
        	  	ResultSet rs = statement.executeQuery(sql);
//        	  	String sql1 = "select device_ID from device_use where user_ID=(select id from userinfo where user_number=\'"+user_number+"\') "
//        	  			+ "and status=\'" + "归还" + "\'";
//        	  	ResultSet rs1 = statement.executeQuery(sql1);
        	  	//查询数据库后返回数据，每列用*隔开
	          if(rs.next())  
	          {         
	          	  deviceinfo = "slave_device";     	      //该用户已有主设备，设此机器设为从设备

	          }
	          else
	          {
	        	  deviceinfo = "master_device";            //该机器为用户主设备
	          }
	  		 rs.close();      
	  		 conn.close();  
		  	}   
	  		catch(ClassNotFoundException e) {  
	  		 System.out.println("Sorry,can`t find the Driver!");              
	  		 e.printStackTrace();  
	  		} catch(SQLException e) {  
	  		 e.printStackTrace();  
	  		} catch(Exception e) {  
	  		 e.printStackTrace();  
	  		}
            System.out.println(deviceinfo);
	  		return deviceinfo;
	      	  
    }
    public static String[] getInfo(String user_info){
       	
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
    		
    		String[] useri = new String[37];
    		for(int i = 0;i < 37;i++)
    			useri[i] = "";
    		          
    		try {               
       	  	Class.forName(driver);  
       	  	Connection conn = DriverManager.getConnection(url, user, password);  
       	  	if(!conn.isClosed())          
       	  		System.out.println("Succeeded connecting to the Database!");               
       	  	Statement statement = conn.createStatement();
       	  	String temp[] = user_info.split("[*]");
       	  	String user_number = temp[1];
       	  	String sql = "select * from userinfo where user_number=\'"+user_number+"\' and active=1";
             
       	  	ResultSet rs = statement.executeQuery(sql);
       	  	//查询数据库后返回数据，每列用*隔开
    	         if(rs.next())  
    	         {         
    	        	 useri[0] = rs.getString("user_number");
    	        	 useri[1] = "*";
    	        	 useri[2] = rs.getString("password");	
    	        	 useri[3] = "*";
    	        	 useri[4] = rs.getString("user_number")+"@csu.edu.cn";
    	        	 useri[5] = "*";
    	        	 useri[6] = rs.getString("user_name");
    	        	 useri[7] = "*";
    	        	 useri[8] = rs.getString("lab_num");
    	        	 useri[9] = "*";
    	        	 useri[10] = rs.getString("degree");
    	        	 useri[11] = "*";
    	        	 useri[12] = rs.getString("user_group");
    	        	 useri[13] = "*";
    	        	 useri[14] = rs.getString("grade");
    	        	 useri[15] = "*";
    	        	 useri[16] = rs.getString("major");
    	        	 useri[17] = "*";
    	        	 useri[18] = rs.getString("tutor");
    	        	 useri[19] = "*";
    	        	 useri[20] = rs.getString("advisor");
    	        	 useri[21] = "*";
    	        	 useri[22] = rs.getString("telephone");
    	        	 useri[23] = "*";
    	        	 useri[24] = rs.getString("qq");
    	        	 useri[25] = "*";
    	        	 useri[26] = rs.getString("email");
    	        	 useri[27] = "*";
    	        	 useri[28] = rs.getString("contact");
    	        	 useri[29] = "*";
    	        	 useri[30] = rs.getString("relationship");
    	        	 useri[31] = "*";
    	        	 useri[32] = rs.getString("contact_phone");
    	        	 useri[33] = "*";
    	        	 useri[34] = rs.getString("research");
    	        	 useri[35] = "*";
    	        	 useri[36] = rs.getString("degree_type");
    	        		 
//    	        	 System.out.println("use_mysql:"+useri[0] + useri[1] + useri[2]+ useri[3] + useri[4]+ useri[5] + useri[6] + useri[28] + useri[30]);
    	        	 
    	         }  
    	         else 
    	         {
    				useri[0] = "####";
    	         }
    		   rs.close();       
    		   conn.close(); 
    		   }   
    		  catch(ClassNotFoundException e) {  
    		   System.out.println("Sorry,can`t find the Driver!");              
    		   e.printStackTrace();  
    		  } catch(SQLException e) {  
    		   e.printStackTrace();  
    		  } catch(Exception e) {  
    		   e.printStackTrace();  
    		  }
    		  return useri;
       }
}
