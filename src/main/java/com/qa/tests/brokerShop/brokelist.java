package com.qa.tests.brokerShop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qa.Parameters.postParameters;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.qa.util.TestUtil.dtt;
// 1、经纪人列表
public class brokelist extends TestBase {
    //设置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();

    TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse res;
    //host根url
    String host;
    //Excel路径
    String testCaseExcel;
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
        postHeader.put("Content-Type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("Host");
        //载入配置文件，post接口参数
        testCaseExcel=System.getProperty("user.dir")+prop.getProperty("testCase1data");

    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);
    }
    @Test(dataProvider = "postData")
    public void brokeList(String content ,String loginUrl,String username, String passWord) throws Exception {
        //使用构造函数将传入的用户名密码初始化成请求参数
        postParameters loginParameters = new postParameters(username,passWord);
        //将请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);
        //发送请求
//        res = restClient.postApi(host+loginUrl,userJsonString,postHeader);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://bj.izhiliao.com/broker/list?index=1&size=30");
        post.setConfig(requestConfig);
        //设置头信息
//        post.setHeader("content-type", "application/json");
//        post.setHeader("It-token", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjkzMTUwNTQsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1Njk1NzQyNTR9.wBAuPXGfw7rEvJuMfFsnVDXA0E-18VnAdvvEHDf6vnE; izl_site=3066_bj; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1569319766; ifh_agent_siteid=27504; verifyCode=03A6E1B36D3E6562C1C3E319A224BF46; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxNTAxMDI2OTg5NiIsImV4cCI6MTU2OTQ2MDQzMn0.ui53J4ZSGWGasEjj9HcEo5T3Z1Wcu01Wfvledyuphlg; prov=cn010; city=010; weather_city=bj; region_ip=114.64.253.150; region_ver=1.30; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1568597125,1569390212; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1569390242; IFH_CLUSTER_SID=DFE5C676C0384F5CA6C1A15E3D674816; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1569396168");
        //发送post请求
         res = httpclient.execute(post);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode,true);
        try {
        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            JSONObject jsonObject = JSONObject.parseObject(result)  ;
            Reporter.log("返回json："+jsonObject);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONArray jsonArrayList = jsonData.getJSONArray("locationList");

            List<String> list = new ArrayList<String>();
            for (int i=0;i<jsonArrayList.size();i++){
                String areaName = jsonArrayList.getJSONObject(i).getString("name");
                list.add(areaName);
            }
            String expectAreaName="朝阳";
            if (list.contains(expectAreaName)){
                Reporter.log("返回区域"+expectAreaName);
                System.out.println("返回区域"+expectAreaName);
                Assert.assertTrue(true);
            }else {
                Reporter.log("没有找到区域"+expectAreaName);
                System.out.println("没有找到区域"+expectAreaName);
                Assert.assertTrue(true);
            }
//            for (int i=0;i< jsonArrayList.size();i++){
//                String jsonArrayList.getJSONObject(i).getString("");
//                Reporter.log("断言公司："+companys);
//                if (companys.contains("我爱我家")){
//                    Assert.assertTrue(true);
//                    break;
//                }
//                else{
//                    Reporter.log("经纪人列表：异常");
//                    Assert.assertTrue(false);
//                }
            }

        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {     //关闭流并释放资源
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
