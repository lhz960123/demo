package com.example.demo.tool;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExcel {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
    //自适应上传文件版本
    public Workbook getWorkbook(InputStream inputStr,String str)throws Exception{
        Workbook wb=null;
        String filetype=str.substring(str.lastIndexOf("."));
        System.out.print("   文件类型"+filetype);
        if(excel2003L.equals(filetype)){
            wb=new HSSFWorkbook(inputStr);
        }
        if(excel2007U.equals(filetype)){
            wb=new XSSFWorkbook(inputStr);
        }else {
            throw new Exception("解析的文件有误");
        }
        System.out.print("wb");
        return wb;

    }
    //解析文件
    public List<List<Object>> getBankListByExcel(InputStream in,String str)throws Exception {
        List<List<Object>>  list= null;
        Workbook workbook=this.getWorkbook(in,str);
        if(null == workbook){
           throw new Exception("创建Excel为空");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell=null;
        list=new ArrayList<List<Object> >();
        //遍历Excel中所有的sheet
        for(int i=0;i<workbook.getNumberOfSheets();i++){
            sheet = workbook.getSheetAt(i);
            if (sheet==null){
                continue;
            }
            //遍历当前的行数
            for (int j=sheet.getFirstRowNum();j<sheet.getLastRowNum();j++){
                    row=sheet.getRow(j);
                    //if (row==null||row.getFirstCellNum()==j){continue;}
                    //遍历所有的属性
                    List<Object> list1=new ArrayList<Object>();
                    for (int y=row.getFirstCellNum();y<row.getLastCellNum();y++){
                            cell=row.getCell(y);
                            System.out.print("cell----------");
                            list1.add(cell);
                    }
                    list.add(list1);
            }
        }
        //
        return  list;
    }
}
