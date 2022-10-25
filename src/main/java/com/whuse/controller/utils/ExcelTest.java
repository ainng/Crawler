package com.whuse.controller.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 王子安
 * @date 2022/10/24 - 16:20
 */
public class ExcelTest {

    @Test
    public void testWrite() throws Exception {

        String path = "D:\\JAVA\\code\\Crawler";

        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("测试统计表");
        //创建行
        Row row1 = sheet.createRow(0);
        //创建一个单元格
        Cell cell11 = row1.createCell(0);

        cell11.setCellValue("姓名");

        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("性别");

        Cell cell13 = row1.createCell(2);
        cell13.setCellValue("职称");

        Cell cell14 = row1.createCell(3);
        cell14.setCellValue("研究方向");


//        //第二行
//        Row row2 = sheet.createRow(1);
//        Cell cell21 = row2.createCell(0);
//        cell21.setCellValue("");
//        Cell cell12 = row1.createCell(1);
//        cell12.setCellValue("性别");

        //生成一张表
        FileOutputStream fileOutputStream = new FileOutputStream(path + "测试用表.xls");

        workbook.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();

        System.out.println("生成完毕");
    }
}
