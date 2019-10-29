package com.qa.tests.secondHouse;

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
import java.util.HashMap;
import java.util.Map;

import static com.qa.util.TestUtil.dtt;

public class GetSimilarHouse extends TestBase {
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
    public void brokeDetail(String content ,String loginUrl,String username, String passWord,int id,int type) throws Exception {
        //使用构造函数将传入的用户名密码初始化成请求参数
        postParameters loginParameters = new postParameters(username,passWord);
        //将请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);

        //发送请求
//        res = restClient.postApidetail(host+loginUrl,userJsonString,postHeader);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sh.izhiliao.com/esf/api/house/getSimilarHouse");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; IFH_CLUSTER_SID=9D7315C973144918AE6885ADA0725FB7; verifyCode=A5A0D965F16E9980110FA2B1DAAF95AF; coupon_menu_status=0; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1570525547; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzA1MjU5MDgsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzA3ODUxMDh9.KYHJ-Uoqtvl1auKS_k1xPAL-OWhqEnm5u4HOX3v_4D4; ifh_agent_siteid=3066; firstLogin=false; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzA5MzE1MjksImluZm8iOiIxNTcxMjg5NTYxNixodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfNzc3MzQ0NCxpdV83NzczNDQ0LGlmZW5nX3VjXzc3NzM0NDQifQ.2gtMA68cRzPzav1ky6Ycb85ahV-jg3BEtdw3n-lqWBQ; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u35\\u36\\u31\\u36; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxNTAxMDI2OTg5NiIsImV4cCI6MTU3MDc2MDQxN30.WL08wRJNTPfdprkdNmaGrjBSLnvKPAce9vwirje4LI4; prov=cn010; city=010; weather_city=bj; region_ip=114.253.10.118; region_ver=1.30; izl_site=27504_sh; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1569586854,1570678272; im_nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u75\\u63\\u5f\\u37\\u37\\u37\\u33\\u34\\u34\\u34; im_headImg=http://s0.ifengimg.com/2017/09/06/male_0d8c528a.png; im_moblie=15712895616; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1570678313; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1570679578");
        //发送post请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "2628791");
        map.put("type", "1");



        //设置发送的数据
        StringEntity s = new StringEntity(JSON.toJSONString(map));
        postParameters loginParameters1 = new postParameters(id,type);

        //将请求对象序列化成json对象
        String userJsonString1 = JSON.toJSONString(loginParameters1);
        post.setEntity(new StringEntity(userJsonString1));

//        s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
//        post.setEntity(s);
         res = httpclient.execute(post);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode,true);
        try {
        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            Reporter.log("result:"+result);
            JSONObject jsonObject = JSONObject.parseObject(result)  ;
            Reporter.log("返回json："+jsonObject);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONObject jsonLocationList = jsonData.getJSONObject("agentPage");
            JSONArray data = jsonLocationList.getJSONArray("data");
            for (int i=0;i< data.size();i++){
                String companys = data.getJSONObject(i).getString("company");
                Reporter.log("断言公司："+companys);
                if (companys.contains("测试公司")){
                    Assert.assertTrue(true);
                    break;
                }else{
                    Reporter.log("经纪人列表：异常");
                    Assert.assertTrue(false);
                }
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
