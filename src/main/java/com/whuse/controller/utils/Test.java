//whu.cs
//生成各个研究方向老师
package com.whuse.controller.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.IIOException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
//jsoup爬虫
/**
 * @author 王子安
 * @date 2022/10/17 - 17:19
 */
public class Test {



    public static void main(String[] args) throws IOException {

        String path = "D:\\JAVA\\code\\Crawler";

        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("测试统计表");

        //获取请求
        String url = "http://cs.whu.edu.cn/teacher.aspx?showtype=jobtitle&typename=%e6%95%99%e6%8e%88";
        //解析网页(jsoup返回的document就是浏览器document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
//        Elements elements = document.getElementsByClass("teacher_zc_list");(第一次测试失败)

        //获取对象（tag是<tr>的）
        Elements elementss = document.getElementsByTag("tr");

        //记录研究方向（不重复）
        ArrayList<String> ris = new ArrayList<>();


        for (Element e1 : elementss) {
            //获取研究方向
            String research_interests = e1.getElementsByClass("w5").eq(0).text();
            String name = e1.getElementsByClass("w1").eq(0).text();
            //split方法。用，分隔开e1对象研究方向
            String[] e1_ris = research_interests.split(",");
            //如果ris中已经有n1（e1被分开的元素）了，则空操作
            //若没有，则将n1加入到ris中
            for (String n1 : e1_ris) {
                if (ris.contains(n1)){
                }else{
                    ris.add(n1);
                }
            }

        }
            //创建每一行
            for (int i = 0; i < ris.size(); i++) {
                Row row = sheet.createRow(i);
                //每一行第一列
                Cell cell = row.createCell(0);
                //每一行第一列为一个研究方向
                cell.setCellValue(ris.get(i));
                //判断每个e1中的研究方向是否包含本行的研究方向（开头）
                    //s为成功计数器
                int s=1;
                for (Element e1 : elementss) {
                    String research_interests = e1.getElementsByClass("w5").eq(0).text();
                    String name = e1.getElementsByClass("w1").eq(0).text();
                    if (research_interests.contains(ris.get(i))) {
                        //
                        Cell cell1 = row.createCell(s);
                        cell1.setCellValue(name);
                        s++;
                    }
                }

            }
            //生成一张表
            FileOutputStream fileOutputStream = new FileOutputStream(path + "研究方向测试用表.xlsx");

            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();

            System.out.println("生成完毕");


    }
}
