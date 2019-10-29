package com.qa.tests.appHouse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.qa.util.TestUtil.dtt;

//二手房APP接口 5、二手房和出租房房源详情
public class HouseSecondView extends TestBase {
    //設置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();
    TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse res;
    //host 根url
    String host;
    //excel 路劲
    String testCaseExecel;
    // header
    HashMap<String,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
        postHeader.put("Content-type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("Host");
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("testCase1data");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData")
    public void filterCon(String contenx,String loginUrl,String username,String password)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.izhiliao.com/house/view");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzEyODE3NzIsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzE1NDA5NzJ9.X3FjovpVeJH4LpZB9j--GAAY_SkNYG-oSnyFqjXQUjY");
        //发送post请求?houseId=2747683&type=1

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("houseId", "2747683");
//        map.put("type", "1");
//        //设置发送的数据
//        StringEntity s = new StringEntity(JSON.toJSONString(map));
//        s.setContentEncoding("UTF-8");
////            s.setContentType("application/json");//发送json数据需要设置contentType
//        post.setEntity(s);


        res = httpClient.execute(post);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode);
        try{
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result =  EntityUtils.toString(res.getEntity());
                Reporter.log("result:"+result);
                JSONObject jsonObject =JSONObject.parseObject(result);
                Reporter.log("返回json:"+jsonObject);
                System.out.println("返回json:"+jsonObject);
                String msgValue = jsonObject.getString("msg");
                Reporter.log("返回json:"+msgValue);
                String subwayStation=null;
                if (msgValue.equals("操作成功")) {
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    JSONObject jsonObjectHouse = jsonObjectData.getJSONObject("house");
                     subwayStation = jsonObjectHouse.getString("subwayStation");
                     String expectionStation="9号线丰台南路站";
                     if (expectionStation.contains(subwayStation)){
                         Reporter.log("正确获取二手房app房详情："+subwayStation);
                         System.out.println("正确获取二手房app房详情："+subwayStation);
                         Assert.assertTrue(true);
                     }else {
                         Reporter.log("获取二手房app房详情失败");
                         System.out.println("获取二手房app房详情失败");
                         Assert.assertTrue(false);
                     }
                    Assert.assertTrue(true);
                } else {
                        System.out.println("失败");
                        Reporter.log("失败");
                        Assert.assertTrue(false);
                    }
                }
                else {
                    Reporter.log("返回失败！");
                    Assert.assertTrue(false);
                }



        }catch (Exception e){
            System.out.println("发生异常："+e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





























