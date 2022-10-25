//whu.cs
//统计生成excel表格
package com.whuse.controller.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
//jsoup爬虫

/**
 * @author 王子安
 * @date 2022/10/17 - 17:19
 */
public class HtmlParseUtilExcelTest {



    public static void main(String[] args) throws IOException {

        String path = "D:\\JAVA\\code\\Crawler";

        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("测试统计表");

        //获取请求
        String url = "http://cs.whu.edu.cn/teacher.aspx?showtype=jobtitle&typename=%e6%95%99%e6%8e%88";
        //解析网页(jsoup返回的document就是浏览器document对象)
        Document document = Jsoup.parse(new URL(url),30000);
//        Elements elements = document.getElementsByClass("teacher_zc_list");(第一次测试失败)

        //获取对象（tag是<tr>的）
        Elements elementss = document.getElementsByTag("tr");
        for (Element e1 : elementss) {
            String name = e1.getElementsByClass("w1").eq(0).text();
            String gender = e1.getElementsByClass("w2").eq(0).text();
            String professional_titles = e1.getElementsByClass("w4").eq(0).text();
            String research_interests = e1.getElementsByClass("w5").eq(0).text();
            System.out.println(name);
                //创建行(根据e1的索引)
                Row row = sheet.createRow(e1.elementSiblingIndex());
                for (int cellNum = 0; cellNum < 4; cellNum++) {
                    Cell cell = row.createCell(cellNum);
                    switch (cellNum){
                        case 0:
                            cell.setCellValue(name);
                            break;
                        case 1:
                            cell.setCellValue(gender);
                            break;
                        case 2:
                            cell.setCellValue(professional_titles);
                            break;
                        case 3:
                            cell.setCellValue(research_interests);
                            break;
                    }
                }

        }
        //生成一张表
        FileOutputStream fileOutputStream = new FileOutputStream(path + "测试用表.xlsx");

        workbook.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();

        System.out.println("生成完毕");

//        System.out.println(elementss.html());
    }

}
