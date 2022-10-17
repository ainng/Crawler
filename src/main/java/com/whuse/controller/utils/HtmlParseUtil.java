package com.whuse.controller.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
//jsoup爬虫
/**
 * @author 王子安
 * @date 2022/10/17 - 17:19
 */
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
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

            System.out.println("==============================================================");
            System.out.println("姓名："+name);
            System.out.println("性别："+gender);
            System.out.println("职称："+professional_titles);
            System.out.println("研究方向："+research_interests);
        }

//        System.out.println(elements.html());（同第一次）
    }

}
