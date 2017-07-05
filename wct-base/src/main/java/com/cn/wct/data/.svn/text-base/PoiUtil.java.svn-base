package com.cn.wct.data;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * --2016.3.23
 * 解析EXCEL文档1.2
 * 支持xlsx和xls文档解析，全面兼容OFFICE所有EXCEL版本文件
 * @author eguid
 *
 ** --2016.3.21
 * 解析EXCEL文档1.1
 * 支持xls文档解析
 * @author eguid
 */
@SuppressWarnings("all")
public class PoiUtil {
	/**
	 * 按照给定的字段进行解析
	 * 如给定数组：{id,name,sal,date}
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public static Map<String, List<String>> parseByfield(File file,String[] fields) throws InvalidFormatException, IOException
	{
		Workbook wb=createWorkbook(file);
		Sheet sheet=wb.getSheetAt(0);
		//列
		Cell cell=null;
		//暂时存放
		String data=null;
		//最大行数
		int maxRowNum=sheet.getLastRowNum();
		//最大列数
		int MaxCellNum=sheet.getRow(0).getLastCellNum();
		List<String>list=null;
		
		Map<String,List<String>>map=null;
		map=new HashMap<String,List<String>>();
		
		for(int i=0;i<maxRowNum;i++){
			
			list=new ArrayList<String>();
			String title=null;
			for(int j=0;j<MaxCellNum;j++){
			//获取第j行第i列的值
			cell=sheet.getRow(j).getCell(i);
			
			data=getValue4Cell(cell);

			//如果标题与给定字段对应,则记录值;否则进入下个整列
			if(ishave(fields,data))
			{
				if(j==0)
				{
					title=data;
				}else{
				list.add(data);
				}
			}
			else{
				break;
			}
			
			}
			map.put(title, list);
			}
		return map;
	}
	/**
	 * 是否有此字段
	 * @param fields
	 * @param field
	 * @return
	 */
	private static boolean ishave(String[] fields,String field)
	{
		if(field==null||fields==null||fields.length<1){
		return false;
		}
		
		for(int index=0;index<fields.length;index++)
		{
			if(field.equals(fields[index]))
				return true;
			else
				return false;
		}
		return false;
	}
	/**
	 * 
	 * 解析
	 * 第一行是标题行
	 * 第二行以后都是内容
	 * 例如：
	 * id sex name  
	 *  1   男     王
	 *  2   女     李
	 * 
	 * 
	 * 解析后的map格式：
	 * key  value
	 * 0     List()一行
	 * 1     List()一行
	 * 2     List()一行
	 *
	 *例如：
	 *0    [id ,  name, sex,   sal   ,  date]
	 *1    [1.0, wang, 1.0, 1000.0, 42287.0]
	 *2    [2.0, liang, 1.0, 1001.0, 42288.0]
	 *@param file
	 *@throws IOException 
	 * @throws InvalidFormatException 
	 */
public static Map parse1(File file) throws IOException, InvalidFormatException
{
	//提取并创建工作表
	Workbook wb=createWorkbook(file);
	//获取sheet页第0页
    Sheet sheet = wb.getSheetAt(0);  
    //获取行迭代器
    Iterator rows = sheet.rowIterator();  
   //解析出来的数据存放到这个map里面，value套了一层List，key用于存放标题，List用于存放标题下的所有数据
    Map<String,List<String>> map=new LinkedHashMap<String,List<String>>();
    
    String title=null;//标题
    int rowindex=0;//行数
    int cellindex=0;//列数
    String data=null;//用于暂存数据
    while(rows.hasNext())
    {
    	 List<String> list=new ArrayList<String>(); 
    	cellindex=0;
    	//获取行中数据
    	 Row row = (Row) rows.next();  
    	//获取列迭代器
    	 Iterator cells = row.cellIterator();  
 
    	    while(cells.hasNext())
    	    {
    	    	//获取列中数据
    	    	 Cell cell = (Cell) cells.next();  
    	    	 //获取每个单元格的值
    	    	//将标题下的内容放到list中
    	    	 String value=getValue4Cell(cell);
    	    	 //if(StringUtils.isNotBlank(value)){
    	    		 list.add(value);
    	    	 //}
    	    	
    	    }
    	  //将解析完的一列数据压入map
    	   // if(CollectionUtils.isNotEmpty(list)){
    	    	 map.put(""+rowindex++, list);
    	  //  }
    	   
    }

    return map;
}
/**
 * 把默认的格式转换成这种格式
 * id [1,2,3,4,5]
 *name [wang,liang,eguid,qq,yy]
 * 
 * @param map    map格式：Map<String,List<String>>
 * @return  Map<String,List<String>>
 */
public static  Map<String,List<String>>  format(Map<String,List<String>> map)
{
	Map<String,List<String>> newmap=new HashMap<String,List<String>>();
	//获取标题行有多少列
	String[] titles=new String[map.get("0").size()];
	int index=0;
	//获取所有标题
	for(String s:map.get("0"))
	{
	titles[index++]=s;
	}
	//控制List
	for(int i=0;i<titles.length;i++)
	{
		List<String>newlist=new ArrayList<String>();
		//控制map
		for(int j=1;j<map.size();j++)
		{
			newlist.add(map.get(j+"").get(i));
		}
	newmap.put(titles[i],newlist);
	}
	return newmap;
	}
/**
 * 解析文件名后缀
 * @return
 */
private static String parseFileSuffix(File file)
{
	String fileName=file.getName();
	return fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
	}
/**
 * 提取文件并创建工作表
 * @throws IOException 
 * @throws InvalidFormatException 
 */
private static Workbook createWorkbook(File file) throws InvalidFormatException, IOException
{
	//如果文件不存在，抛出文件没找到异常
	InputStream input = new FileInputStream(file);  
	
	Workbook wb=null;
	//如果创建工作表失败会抛出IO异常
	wb=WorkbookFactory.create(input);
	return wb;
}
/**
 * 提取单元格中的值
 */
private static String getValue4Cell(Cell cell)
{
	String data=null;
	 switch (cell.getCellType()) {  
     case Cell.CELL_TYPE_NUMERIC: // 数字  
         data=String.valueOf(cell.getNumericCellValue()) ;
         break;  
     case Cell.CELL_TYPE_STRING: // 字符串  
     	 data=String.valueOf(cell.getStringCellValue()) ;
         break;  
     case Cell.CELL_TYPE_BOOLEAN: // Boolean  
     	 data=String.valueOf( cell.getBooleanCellValue());
         break;  
     case Cell.CELL_TYPE_FORMULA: // 公式  
     	 data=String.valueOf(cell.getCellFormula()) ;
         break;  
     case Cell.CELL_TYPE_BLANK: // 空值  
     	 data=String.valueOf("");
         break;  
     case Cell.CELL_TYPE_ERROR: // 故障  
         System.out.println(" 故障");  
         break;  
     default:  
         System.out.print("未知类型  ");  
         break;  
     }  
	 return data;
	}
/**
 * 用于关闭流（暂不用）
 * @throws IOException 
 */
private void closeAll(Closeable...closes) throws IOException
{
	if(closes==null)
	{
		return;
	}
	if(closes.length<1)
	{
		return;
	}
	for(Closeable c:closes)
	{
	if(c!=null)
	{
	try {
		c.close();
	} catch (IOException e) {
		e.printStackTrace();
		throw e;
	}	
	}
	}
	}
/**
 * 格式：
 key      value
 
 id        [1.0, 2.0, 3.0, 4.0, 5.0]
 sex      [1.0, 1.0, 1.0, 0.0, 0.0]
 name  [wang, liang, eguid, qq, yy]
 date    [42287.0, 42288.0, 42289.0, 42290.0, 42291.0]
 sal       [1000.0, 1001.0, 1002.0, 1003.0, 1004.0]
 * @throws InvalidFormatException 
 */

public static void test1() throws IOException, InvalidFormatException
{
	Map<String, List<String>>map=parse1(new File("测试.xlsx"));
	Map <String,List<String>>newmap=format(map);
	for(Entry<String,List<String>>e:newmap.entrySet())
	{
		System.out.println(e.getKey());
		System.out.println(e.getValue());
	}
	}
/**
 格式：
 key  value
 0      [id, name, sex, sal, date]
 1      [1.0, wang, 1.0, 1000.0, 42287.0]
 2      [2.0, liang, 1.0, 1001.0, 42288.0]
 3      [3.0, eguid, 1.0, 1002.0, 42289.0]
 4      [4.0, qq, 0.0, 1003.0, 42290.0]
 5      [5.0, yy, 0.0, 1004.0, 42291.0]
 * @throws InvalidFormatException 
 */
public static void test2() throws IOException, InvalidFormatException
{
	Map<String, List<String>>map=parse1(new File("F:/util/apache-tomcat-7.0.57/webapps/tissue-web/upload/2 (2).xlsx"));
	for(Entry<String,List<String>>e:map.entrySet())
	{
		System.out.println(e.getKey());
		System.out.println(e.getValue());
	}
	}
public static void main(String[]args) throws IOException, InvalidFormatException
{
	//System.out.println(parseFileSuffix(new File("测试.xlsx")));
	// test1();
	 test2();
	//Map<String, List<String>> map=parseByfield(new File("F:/util/apache-tomcat-7.0.57/webapps/tissue-web/upload/xlsx_20160604_1465033758330.xlsx"),{"id","name"});
	//System.out.println(map);
}
}
