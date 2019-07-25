package com.zrc.springboottutorial.client;

import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/24 18:56
 * Description: No Description
 */

public class ClientTest {
    public static void main(String[] args) {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpget = new HttpGet(url);
//        CloseableHttpResponse response = httpclient.execute(httpget);
//
//        String result = null;
//        HttpEntity entity = response.getEntity();
//        if (entity != null) {
//            result = EntityUtils.toString(entity);
//        }
//        System.out.println(result);


        //建议以注入方式使用
        RestTemplate restTemplate=new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8090/helloworld4", String.class);
        System.out.println(result);
    }
}
