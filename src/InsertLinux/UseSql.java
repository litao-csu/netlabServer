package InsertLinux;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UseSql {
	
	
	public static boolean insertNewUser(String userName,String userId,String groupName){
		boolean successInsert = true;
		ArrayList<ArrayList<String>> list;
		
		try {
			
			list = ReadExcel.ReadLines();
			//将新用户注册到openfire
			Register_openfire ro = new Register_openfire();
			ro.registeOneUser(userName, userId);
			//将新用户分组关系插入数据库
			insertGroupForOne(userName, userId, groupName, list);
			//将新用户写入excel文件
			ReadExcel.appendExcel(userName, userId, groupName);
			
			
		} catch (Exception e) {
			successInsert = false;
			e.printStackTrace();
		}
		
		System.out.println("调用接口成功");
		return successInsert;
	}
	
	
	
	//插入全部分组关系
	public static void insertGroup(ArrayList<ArrayList<String>> list){
		String insertRosterGroup = null;
		String insertRoster = null;
//		ArrayList<String> l1 = new ArrayList<String>();
//		ArrayList<String> l2 = new ArrayList<String>();
		int n;
		try {
			for(int i = 0;i<list.get(0).size();i++){
				for(int j = 0;j<list.get(0).size();j++){
					if(i < j){
						Connection connection = DBUtil.getConnection();
						Statement statement = connection.createStatement();
						
						insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(i)+"','"+
										list.get(1).get(j)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(j)+"')";
						System.out.println(insertRoster);
						statement.execute(insertRoster);
						
						insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(j)+"','"+
										list.get(1).get(i)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(i)+"')";
						System.out.println(insertRoster);
						statement.execute(insertRoster);
						
						
						
						insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(j)+"')";
						statement.execute(insertRosterGroup);
						System.out.println(insertRosterGroup);
						insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(i)+"')";
						statement.execute(insertRosterGroup);
						System.out.println(insertRosterGroup);
						
						
						statement.close();
						DBUtil.closeConnection(connection);
						
						
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//为一名新用户添加分组关系
		public static void insertGroupForOne(String UserName,String UserId,String GroupName,ArrayList<ArrayList<String>> list){
			String insertRosterGroup = null;
			String insertRoster = null;
			int oldListNum = list.get(0).size();
			list.get(0).add(UserName);
			list.get(1).add(UserId);
			list.get(2).add(GroupName);
//			ArrayList<String> l1 = new ArrayList<String>();
//			ArrayList<String> l2 = new ArrayList<String>();
			int n;
			try {
				for(int i = oldListNum;i<list.get(0).size();i++){
					for(int j = 0;j<list.get(0).size();j++){
						if(i > j){
							Connection connection = DBUtil.getConnection();
							Statement statement = connection.createStatement();
							
							insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(i)+"','"+
											list.get(1).get(j)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(j)+"')";
							System.out.println(insertRoster);
							statement.execute(insertRoster);
							
							insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(j)+"','"+
											list.get(1).get(i)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(i)+"')";
							System.out.println(insertRoster);
							statement.execute(insertRoster);
							
							
							
							insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(j)+"')";
							statement.execute(insertRosterGroup);
							System.out.println(insertRosterGroup);
							insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(i)+"')";
							statement.execute(insertRosterGroup);
							System.out.println(insertRosterGroup);
							
							
							statement.close();
							DBUtil.closeConnection(connection);
							
							
						}
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	//新增部分信息
	public static void insertGroup(ArrayList<ArrayList<String>> list,int begin){
		String insertRosterGroup = null;
		String insertRoster = null;
//		ArrayList<String> l1 = new ArrayList<String>();
//		ArrayList<String> l2 = new ArrayList<String>();
		int n;
		try {
			for(int i = begin;i<list.get(0).size();i++){
				for(int j = 0;j<list.get(0).size();j++){
					if(i > j){
						Connection connection = DBUtil.getConnection();
						Statement statement = connection.createStatement();
						
						insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(i)+"','"+
										list.get(1).get(j)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(j)+"')";
						System.out.println(insertRoster);
						statement.execute(insertRoster);
						
						insertRoster = "INSERT into ofRoster (username,jid,sub,ask,recv,nick) VALUES ('"+list.get(1).get(j)+"','"+
										list.get(1).get(i)+"@netlab'"+",'3','-1','-1','"+list.get(0).get(i)+"')";
						System.out.println(insertRoster);
						statement.execute(insertRoster);
						
						
						
						insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(j)+"')";
						statement.execute(insertRosterGroup);
						System.out.println(insertRosterGroup);
						insertRosterGroup = "INSERT into ofRosterGroups (rank,groupName) VALUES ('0','"+list.get(2).get(i)+"')";
						statement.execute(insertRosterGroup);
						System.out.println(insertRosterGroup);
						
						
						statement.close();
						DBUtil.closeConnection(connection);
						
						
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	private void setInsertGroupToPreparedStatement(PreparedStatement pStatement,
//			ArrayList<String> list) throws SQLException {
//		pStatement.setString(1, list.get(0));
//		pStatement.setString(2, list.get(1));
//		pStatement.setString(3, list.get(2));
//		
//	}
	
	//为一个用户更换组
	public static void changeGroup(String stdId,String newGroup){
		
		String changeSql = "update ofRosterGroups set groupName='"+newGroup+"' where rosterId="
						+ "any(select rosterId from ofRoster where jid='"+stdId+"@netlab')";
		System.out.println(changeSql);
		
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(changeSql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//删除一位用户
		public static void deleteUser(String stdId){
			
			String deleteOfRosterGroupsSql = "delete from ofRosterGroups where rosterId="+
												"any(select rosterId from ofRoster where username='"+stdId+
												"' UNION select rosterId from ofRoster where jid='"+stdId+"@netlab')";
			String deleteOfRosterSql = "delete from ofRoster where rosterId=any"+
										"(select rosterId from ((select * from ofRoster where username='"+stdId+
										"' )UNION ALL(select * from ofRoster where jid='"+stdId+"@netlab'))AS A)";
			System.out.println(deleteOfRosterGroupsSql);
			System.out.println(deleteOfRosterSql);
			try {
				Connection connection = DBUtil.getConnection();
				Statement statement = connection.createStatement();
				statement.execute(deleteOfRosterGroupsSql);
				statement.execute(deleteOfRosterSql);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		//将用户姓名插入姓名版
		public static void insertNameBoard(ArrayList<ArrayList<String>> list){
			
			String insertNameBoardSql;
			for(int i = 0;i<list.get(0).size();i++){
				insertNameBoardSql = "INSERT INTO ofVCard (`username`, `vcard`) VALUES ('"+list.get(1).get(i)+"', '<vCard xmlns=\"vcard-temp\"><N><FAMILY/><GIVEN>"
									+list.get(0).get(i)+"</GIVEN><MIDDLE/></N><ORG><ORGNAME/><ORGUNIT/></ORG><NICKNAME/><FN>"
									+list.get(0).get(i)+"</FN><TITLE/><URL/><EMAIL><HOME/><INTERNET/><PREF/><USERID/></EMAIL><TEL><WORK/><VOICE/><NUMBER/></TEL><TEL><WORK/><PAGER/><NUMBER/></TEL><TEL><WORK/><FAX/><NUMBER/></TEL><TEL><WORK/><CELL/><NUMBER/></TEL><TEL><HOME/><VOICE/><NUMBER/></TEL><TEL><HOME/><PAGER/><NUMBER/></TEL><TEL><HOME/><FAX/><NUMBER/></TEL><TEL><HOME/><CELL/><NUMBER/></TEL><ADR><WORK/><LOCALITY/><CTRY/><STREET/><PCODE/><REGION/></ADR><ADR><HOME/><LOCALITY/><CTRY/><STREET/><PCODE/><REGION/></ADR></vCard>')";
				System.out.println("insert "+list.get(0).get(i));
				
				try {
					Connection connection = DBUtil.getConnection();
					Statement statement = connection.createStatement();
					statement.execute(insertNameBoardSql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	
	/*//判断一个组名是否合法
		public static boolean isLegalGroupName(String groupName){
			boolean b = false;
			String[] Groups = {"参数组","大数据组","图形组","网络与信息安全组","网络组",
								"数据中心组","互联网产品组","网络软件组","透明计算","生物组","深度学习组","老师"};
			for(String str:Groups)
				if(str.equals(groupName))
					b = true;
			return b;
		}*/
		
		public static boolean isLegalGroupName(String groupName){
			boolean b = false;
//			String[] Groups = {"计算机楼109","计算机楼113-1","计算机楼115","计算机楼116"
//			 		,"计算机楼201","计算机楼213","计算机楼214","计算机楼216"
//			 		,"计算机楼302","计算机楼405","计算机楼406-A","计算机楼412"
//			 		,"升华后楼410-2","升华后楼201","升华后楼212","老师"};
//			for(String str:Groups)
//				if(str.equals(groupName))
//					b = true;
			if(groupName.startsWith("升") || groupName.startsWith("计") || 
					groupName.startsWith("老师") || groupName.startsWith("逸"))
				b = true;
			return b;
		}

		
		public static void main(String[] args) {
//			System.out.println("Start!");
//			if(args[0].equals("insert")){
//				System.out.println("insert infomation");
//				/*
//				 * insert (all or beginNumber)  插入分组关系
//				 * insert name 插入姓名版
//				 */
//				//读取excel文件
//				ReadExcel re = new ReadExcel();
//				ArrayList<ArrayList<String>> info;
//				info = re.ReadLines();
//				UseSql us = new UseSql();
//				//将分组关系插入数据库
//				
//				if(args[1].equals("all")){
//					//插入全部数据
//					us.insertGroup(info);
//				}else if(args[1].equals("name")){
//					us.insertNameBoard(info);
//				}else{
//					//新增数据
//					us.insertGroup(info, Integer.parseInt(args[1]));
//				}
//				
//			}else if(args[0].equals("change")){
//				/*
//				 *  change 学号 组名
//				 */
//				System.out.println("change group "+args[1]+ " "+ args[2]);
//				UseSql us = new UseSql();
//				boolean GroupNameLegal = us.isLegalGroupName(args[2]);
//				if(GroupNameLegal){
//					us.changeGroup(args[1], args[2]);
//				}else{
//					System.out.println("组名不合法");
//				}
//			}else if(args[0].equals("delete")){
//				/*
//				 * delete 学号 
//				 */
//				System.out.println("删除 "+args[1]);
//				UseSql us = new UseSql();
//				us.deleteUser(args[1]);
//			}else{
//				System.out.println("Insert or Change groups "+args[0]);
//			}
			
			
			//测试
	//		ReadExcel re = new ReadExcel();
	//		ArrayList<ArrayList<String>> info;
	//		info = re.ReadLines();
	//		UseSql us = new UseSql();
	//		us.insertGroup(info, 162);
	//		us.changeGroup("164611107", "深度学习组");
		}
	}
