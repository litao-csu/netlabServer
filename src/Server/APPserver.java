package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import InsertLinux.ReadExcel;
import InsertLinux.UseSql;
import Server.use_mysql;
import Server.update_mysql;

public class APPserver {
//	public static final int PORT = 12345;//监听的端口号
	public static final int PORT = 12306;//监听的端口号
	Socket client;
	
	public static void main(String[] args) {  
	    System.out.println("server run...\n" );   
	    APPserver server = new APPserver();  
	    server.init();  
	}    
	
	public void init() {  
	    try {  
	        @SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(PORT);  
		    while (true) {  
		        // 一旦有堵塞, 则表示服务器与客户端获得了连接  
		         client = serverSocket.accept();  
		        // 处理这次连接  
		        new HandlerThread(client);  
		    }  
		} catch (Exception e) {  
		    System.out.println("服务器异常: " + e.getMessage());  
		}  
	}   
	
	private class HandlerThread implements Runnable {  
	    private Socket socket;  
	    public HandlerThread(Socket client) {  
	        socket = client;  
	        new Thread(this).start();  
	    }   
	    
		public void run() {
		    try {// 读取客户端数据  
		        DataInputStream input = new DataInputStream(socket.getInputStream());
		        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		        String clientInputStr = input.readUTF();
		        String[] temp = clientInputStr.split("[*]");                
		        if(temp[0].equals("register"))
		        {
		        	String device_rank = new String();
		        	String[] device_sign = use_mysql.getDeviceinfo_dynamic(temp[5]);
			        if(device_sign[0].equals("device_unregister"))
			        {
			        	//插入设备信息表
						Insert_mysql.Deviceinfo_insert(clientInputStr);
						Insert_mysql.deviceinfo_static_insert(temp[5]);
						String[] device_info = use_mysql.getDeviceinfo_dynamic(temp[5]);
						String userinfo_sign = use_mysql.judgeUserInfo(temp[1]);
						if(userinfo_sign.equals("userinfo_unregister"))
						{
							//判断用户是否在excel中,如果不存在，调用openfire注册接口
							if(ReadExcel.checkUserId(temp[1])){
//								System.out.println("判断用户是否在excel中:"+temp[2]+temp[1]+temp[10]);
								String lab = temp[3];
								if(temp[1].length() == 6)
									lab = "老师";
								UseSql.insertNewUser(temp[2],temp[1],lab);
							}
							
							device_rank = "master";
							String ret_info = Insert_mysql.Info_insert(clientInputStr);//调用数据库插入注册信息	
							String user_id = use_mysql.judgeUserInfo(temp[1]);
							Insert_mysql.device_use_insert(user_id,device_info[0],device_rank);//新用户新设备注册
							out.writeUTF(ret_info+"*"+device_rank);
							out.flush();
//							System.out.println(ret_info+"Insert success!");
						 }
						 else 
						 {
							 update_mysql.userinfo_update(clientInputStr);
							/*//更改用户在openfire里的分组								
							String lab = temp[3];
							if(temp[1].length() == 6)
								lab = "老师";
							UseSql.changeGroup(temp[1], lab);*/
								
							 
				    		 String deviceuse_sign = use_mysql.judgeDevice_useinfo(temp[1]);
				    		 if(deviceuse_sign.equals("slave_device"))
				    		 {
				    			 device_rank = "slave";
				    			 Insert_mysql.device_use_insert(userinfo_sign,device_info[0],device_rank);//老用户新设备注册
				    		 }
				    		 else {
				    			 device_rank = "master";
				    			 Insert_mysql.device_use_insert(userinfo_sign,device_info[0],device_rank);
							 }
				    		 out.writeUTF(clientInputStr+"*"+device_rank);
				    		 out.flush();
//				    		 System.out.println(clientInputStr);
						 }
		        	}
					else 
					{
						String userinfo_sign = use_mysql.judgeUserInfo(temp[1]);
						if(userinfo_sign.equals("userinfo_unregister"))
						{
							//判断用户是否在excel中,如果不存在，调用openfire注册接口
							/*if(ReadExcel.checkUserId(temp[1])){
//								System.out.println("判断用户是否在excel中:"+temp[2]+temp[1]+temp[10]);
								String lab = temp[3];
								if(temp[1].length() == 6)
									lab = "老师";
								UseSql.insertNewUser(temp[2],temp[1],lab);
							}*/
							String ret_info = Insert_mysql.Info_insert(clientInputStr);//调用数据库插入注册信息	
							String user_id = use_mysql.judgeUserInfo(temp[1]);
							device_rank = "master";
							Insert_mysql.device_use_insert(user_id,device_sign[0],device_rank);//新用户老设备注册
							out.writeUTF(ret_info+"*"+device_rank);
							out.flush();
//							System.out.println(ret_info+"Insert success!");
						}
						else
						{		
							update_mysql.userinfo_update(clientInputStr);	
							//更改用户在openfire里的分组								
							/*String lab = temp[3];
							if(temp[1].length() == 6)
								lab = "老师";
							UseSql.changeGroup(temp[1], lab);*/
							
				    		String deviceuse_sign = use_mysql.judgeDevice_useinfo(temp[1]);
				    		if(deviceuse_sign.equals("master_device"))
				    		{
				    			device_rank = "master";
				    			Insert_mysql.device_use_insert(userinfo_sign,device_sign[0],device_rank);//老用户老设备注册
				    		}
				    		else
				    		{
				    			device_rank = "slave";
				    			Insert_mysql.device_use_insert(userinfo_sign,device_sign[0],device_rank);
							}
				    		out.writeUTF(clientInputStr+"*"+device_rank);
				    		out.flush();
//				    		System.out.println(clientInputStr);
						}	
					 }
		         }
		        else if (temp[0].equals("initinfo")) 
		        {
		        	String info = use_mysql.getInitInfo();
		        	out.writeUTF(info);
		    		out.flush();
				}
		        else if (temp[0].equals("updateinfo")) 
		        {
		        	update_mysql.userinfo_update_mysql(clientInputStr);
		        	
		        	//更改用户在openfire里的分组								
					/*String lab = temp[2];
					if(temp[1].length() == 6)
						lab = "老师";
					UseSql.changeGroup(temp[1], lab);*/
					
		        	out.writeUTF(clientInputStr);
		    		out.flush();
//		        	System.out.println(clientInputStr);
				}
		        else if (temp[0].equals("set_mainequipment")) {
		        	update_mysql.deviceuse_update_mysql(clientInputStr);
//		        	System.out.println(clientInputStr);
				}
		        else if (temp[0].equals("check_userinfo")) {
		        	String[] userinfo = use_mysql.getInfo(clientInputStr);
		        	StringBuffer sb = new StringBuffer(); 
		        	for(int i = 0; i < userinfo.length; i++)
		        	{
		            	sb. append(userinfo[i]);
		        	}
		            	String s = sb.toString();
//		            	System.out.println(s);	            
	            		out.writeUTF(s);
	            		out.flush();
				}
		        else if (temp[0].equals("get_clientaddr")) {
		        	String addr = client.getInetAddress().getHostAddress();
		        	out.writeUTF(addr);
		        	out.flush();
//		        	System.out.println(addr);
				}else
		         {       	
		    		String[] userinfo = use_mysql.getUserInfo(clientInputStr);//服务器通过硬盘序列号查询数据库返回的登录数据或者失败返回的“####”
		        	// 向客户端回复信息            
		        	StringBuffer sb = new StringBuffer(); 
		        	for(int i = 0; i < userinfo.length; i++)
		        	{
		            	sb. append(userinfo[i]);
		        	}
	            	String s = sb.toString();
//		            	System.out.println(s);
	            	if(s.equals("####"))
	            	{
	            		out.writeUTF(s);
	            		out.flush();
	            	}
	            	else 
	            	{
	            		String[] device_info = use_mysql.getDeviceinfo_dynamic(clientInputStr);
	            		String[] device_useinfo = use_mysql.getDevice_useinfo(clientInputStr);
						out.writeUTF(s+"*"+device_info[2]+"*"+device_useinfo[0]);
						out.flush();
//						System.out.println(s+"*"+device_info[2]+"*"+device_useinfo[0]);
					}
		            	
		         }	                   	                    
        	     out.close();  
        	     input.close(); 
	    	}catch (Exception e) 
		    {  
		        System.out.println("服务器 run 异常: " + e.getMessage());  
		    } 
		    finally 
		    {  
		        if (socket != null) 
		        {  
		            try 
		            {  
		                socket.close();  
		            } 
		            catch (Exception e) 
		            {  
		                socket = null;  
		                System.out.println("服务端 finally 异常:" + e.getMessage());  
		            }  
		        }  
		    }
		} //run 
	}  
} 