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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qa.util.TestUtil.dtt;

public class brokeStoreDetail extends TestBase {
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
    public void brokeDetail(String content ,String loginUrl,String username, String passWord) throws Exception {
        //使用构造函数将传入的用户名密码初始化成请求参数
        postParameters loginParameters = new postParameters(username,passWord);
        //将请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);
        //发送请求
//        res = restClient.postApidetail(host+loginUrl,userJsonString,postHeader);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sz.izhiliao.com/broker/detail?agentId=3581883");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("content-type", "application/json;charset=UTF-8");
        post.setHeader("It-token", "ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzEwMjM4MTAsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzEyODMwMTB9.6-h5xgJL8yK4ckcqENRCFoP5E-LYYrmGTWK3_QnPbcI; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzcxODI0MjQ1MyIsImV4cCI6MTU3MTExNzk0Nn0.YBotWKL7rERPZbg6We9QbKjs3mu6gGXBbWR2IwgWCTU; coupon_menu_status=0; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1571121794; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571122198; IFH_CLUSTER_SID=6A3505F540984C218534A650522D2C07; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22%24device_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_siteid=3066; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1571197184; firstLogin=false; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzE0NTY0MzUsImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.15Nxyfa92rm4a__H0mquCl4Lc0IoKG30A3EYkFrJTUw; secondCityArr=[{\"tableName\":null,\"id\":3066,\"name\":\"å\u008C\u0097äº¬\",\"logo\":null,\"domain\":\"bj\",\"isProxy\":1,\"letter\":\"B\",\"isHot\":0,\"url\":\"https://bj.izhiliao.com/\",\"status\":1,\"abroad\":0,\"sort\":1,\"createTime\":1532921163},{\"tableName\":null,\"id\":36688,\"name\":\"æ·±å\u009C³\",\"logo\":null,\"domain\":\"sz\",\"isProxy\":0,\"letter\":\"S\",\"isHot\":0,\"url\":\"https://m.izhiliao.com/sz\",\"status\":1,\"abroad\":0,\"sort\":4,\"createTime\":1532921163}]; izl_site=36688_sz; im_nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; im_headImg=http://s0.ifengimg.com/2017/09/06/male_0d8c528a.png; im_moblie=13718242453; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1571213601");
        //发送post请求
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("agentId", "3581883");
//        //设置发送的数据
//        StringEntity s = new StringEntity(JSON.toJSONString(map));
//        s.setContentEncoding("UTF-8");
////            s.setContentType("application/json");//发送json数据需要设置contentType
//        post.setEntity(s);
        CloseableHttpResponse res = httpclient.execute(post);
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
            JSONObject jsonCom = jsonData.getJSONObject("agent");
            String companys=  jsonCom.getString("company");
            String expectCompany="深圳市远盛房地产经纪有限公司";
            if (companys.contains(expectCompany)){
                Reporter.log("经纪人店铺所属公司"+expectCompany);
                System.out.println("经纪人店铺所属公司"+expectCompany);
                Assert.assertTrue(true);
            }else {
                Reporter.log("么有找到经纪人店铺所属公司"+expectCompany);
                System.out.println("么有找到经纪人店铺所属公司"+expectCompany);
                Assert.assertTrue(false);
            }
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
