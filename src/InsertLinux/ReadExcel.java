package InsertLinux;

import java.awt.List;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static ArrayList<ArrayList<String>> ReadLines() throws Exception{
		InputStream is;
		ArrayList<ArrayList<String>> list = new ArrayList();
		ArrayList<String> a1 = new ArrayList<String>();
        ArrayList<String> a2 = new ArrayList<String>();
        ArrayList<String> a3 = new ArrayList<String>();
		
			is = new FileInputStream("/home/server/openfire/config/1.xlsx");
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            // 获取当前工作薄的每一行
	            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                    XSSFCell one = xssfRow.getCell(0);
	                    //读取第一列数据
	                    XSSFCell two = xssfRow.getCell(1);
	                    //读取第二列数据
	                    XSSFCell three = xssfRow.getCell(2);
	                    //读取第三列数据
	                   
	                  //  BigDecimal bd = new BigDecimal(two.getNumericCellValue());
	                    a1.add(one.getStringCellValue());
	                    a2.add(two.getStringCellValue());
	                    a3.add(three.getStringCellValue());
	                    list.add(a1);
	                    list.add(a2);
	                    list.add(a3);
 	                  //  System.out.println(one.getStringCellValue()+" "+bd.toString()+" "+three.getStringCellValue());
	                }
	            }
	            
//                    for (int j = 0; j < list.get(1).size(); ++j)
//                        System.out.println(list.get(0).get(j)+list.get(1).get(j)+list.get(2).get(j));
                
	        }
		
        
        // 获取每一个工作薄
        return list;
        
	}
	
	//将一条信息插入Excel末尾
			public static void appendExcel(String userName,String userId,String userGroup) throws Exception{
				String fileName = "/home/server/openfire/config/1.xlsx";
				InputStream is = new FileInputStream(fileName);
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
				int numSheet = 0;
		        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
		        int lastLineNum = xssfSheet.getLastRowNum();
		        
		        XSSFRow xssfRow = xssfSheet.createRow(lastLineNum+1);
		        XSSFCell columnOne = xssfRow.createCell(0);
		        XSSFCell columnTwo = xssfRow.createCell(1);
		        XSSFCell columnThree = xssfRow.createCell(2);
		        
		        columnOne.setCellValue(userName);
		        columnTwo.setCellValue(userId);
		        columnThree.setCellValue(userGroup);
		        FileOutputStream fout = new FileOutputStream("/home/server/openfire/config/1.xlsx"); 
		        xssfWorkbook.write(fout);
		        fout.close();
			}
	
			//判断用户是否在excel中
			public static boolean checkUserId(String userId){
				boolean newUserId = true;
				String fileName = "/home/server/openfire/config/1.xlsx";
				ArrayList<String> list = new ArrayList<>();
				InputStream is;
				try {					
					is = new FileInputStream(fileName);
					XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
					int numSheet = 0;
			        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
		                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
		                if (xssfRow != null) {
		                    //XSSFCell one = xssfRow.getCell(0);
		                    //读取第一列数据
		                    XSSFCell two = xssfRow.getCell(1);
		                    //读取第二列数据
		                    //XSSFCell three = xssfRow.getCell(2);
		                    //读取第三列数据
		                   
		                    list.add(two.getStringCellValue());
		                }
		            }
			        
			        for (String str : list) {
						if(str.equals(userId))
							newUserId = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("学号检验"+newUserId);
				return newUserId;
			}
	public static void main(String[] args) {
//		ReadExcel re = new ReadExcel();
//		re.ReadLines();
	}
}
