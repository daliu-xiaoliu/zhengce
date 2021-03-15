import com.yang.entities.Policy;
import com.yang.entities.PolicyInfo;
import com.yang.util.RedisUtil;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class test {

    public static void main(String[] args) throws Exception {
        String url="https://www.baidu.com/s?ie=UTF-8&wd=%E5%A4%A7%E5%AD%A6%E7%94%9F%E4%BA%BA%E6%89%8D%E6%94%BF%E7%AD%96";
        Document parse = Jsoup.parse(new URL(url),30000);
        Element element=parse.getElementById("content_left");
        Elements li = element.getElementsByClass("result c-container new-pmd");
        List<PolicyInfo> list=new ArrayList<>();
        for (Element el:li) {
            String name= el.getElementsByClass("t").text();
            String price = el.getElementsByClass("c-abstract").text();
            String shopnum=el.getElementsByTag("a").eq(0).attr("href");
            System.out.println(name);
            System.out.println(price);
            System.out.println(shopnum);
        }

        String city="安徽人才";
        String name=city.substring(0,2);
        System.out.println(name);
    }


}
