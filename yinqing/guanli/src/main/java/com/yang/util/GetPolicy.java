package com.yang.util;

import com.yang.entities.Policy;
import com.yang.entities.PolicyInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GetPolicy {


    public static List<PolicyInfo> getPolicy(String find) throws Exception {
        String url="https://www.baidu.com/s?ie=UTF-8&wd="+find;
        Document parse = Jsoup.parse(new URL(url),30000);
        Element element=parse.getElementById("content_left");
        Elements li = element.getElementsByClass("result c-container new-pmd");
        List<PolicyInfo> list=new ArrayList<>();
        for (Element el:li) {
            String name= el.getElementsByClass("t").text();
            String info = el.getElementsByClass("c-abstract").text();
            String route=el.getElementsByTag("a").eq(0).attr("href");
            String id=UUID.randomUUID().toString();
            System.out.println(id);
            String city=find.substring(0,2);
            String policyId="01001";
            switch (city){
                case "北京":policyId="01002";break;
                case "上海":policyId="01003";break;
                case "合肥":policyId="01004";break;
                case "西安":policyId="01005";break;
                case "吕梁":policyId="01006";break;
                default:policyId="01001";
            }
            Policy policy=new Policy(null,id,name,policyId,find,50);
            System.out.println(policy);
            PolicyInfo policyInfo=new PolicyInfo(policy,route,info);
            System.out.println(policyInfo);
            list.add(policyInfo);
        }
        return list;
    }

}
