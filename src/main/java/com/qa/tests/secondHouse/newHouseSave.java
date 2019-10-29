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

public class newHouseSave extends TestBase {
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
        //发送请求https://api.izhiliao.com/house/view
//        res = restClient.postApidetail(host+loginUrl,userJsonString,postHeader);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.izhiliao.com/house/save");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjkzMTUwNTQsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1Njk1NzQyNTR9.wBAuPXGfw7rEvJuMfFsnVDXA0E-18VnAdvvEHDf6vnE; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1569477603; izl_site=3066_bj; IFH_CLUSTER_SID=C0ABA12138D641F0A5F67432CC4A941E; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1569482510; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1569554178; verifyCode=FC9B752D0D2755CEF7EFC0EA638A49BE; JSESSIONID=B76DA3E811DB8680F3EFA6464D28CD24; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1569567535; ifh_agent_siteid=27504; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzcxODI0MjQ1MyIsImV4cCI6MTU2OTY1NjA2M30.EjlXwK5j0MCD-HqJmYMH6wptuziLbosXNBG81UDozMc; Hm_lvt_4e508e074db803e7cfe17dc4c535f739=1569569162,1569569328,1569569549,1569569664; Hm_lvt_72f9fd87c51a333ceca5010d5c825a1d=1569569162,1569569328,1569569549,1569569664; Hm_lpvt_4e508e074db803e7cfe17dc4c535f739=1569569675; Hm_lpvt_72f9fd87c51a333ceca5010d5c825a1d=1569569675");
        //发送post请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("geoLocation", "31.132026,121.397859");
        map.put("lpId", "614453");
        map.put("siteId", "27504");
        map.put("locationId", "30");
        map.put("type", "合租");
        map.put("areaId", "68");
        map.put("lpName", "东苑佳佳花园");
        map.put("lpAddr", "闵行古美报春路218弄");
        map.put("bedroom", "5");
        map.put("livingroom", "4");
        map.put("kitchenroom", "3");
        map.put("bathroom", "2");
        map.put("floor", "1");
        map.put("totalFloor", "12");
        map.put("houseType", "住宅");
        map.put("fitment", "毛坯");
        map.put("supportLift", "有");
        map.put("supportHeating", "集中供暖");
        map.put("rentPersonLimit", "南");
        map.put("buildArea", "13");
        map.put("rentFamilyNum", "6");
        map.put("visitType", "随时看房");
        map.put("rentPrice", "1010");
        map.put("rentPayType", "押一付一");

        map.put("sourceTitle", "出租房屋每天每天一套出租房屋每天两套");
        map.put("sourceDesc", "出租房屋每天一套出租房屋每天一套描述描述");
        map.put("lpName", "sourceDesc");
        map.put("pics","https://s0.ifengimg.com/2019/09/27/dfeb2cf92edd417fb73fcd8e5a7c9980.jpg|https://s0.ifengimg.com/2019/09/27/dfeb2cf92edd417fb73fcd8e5a7c9980.jpg|室内图");


        //设置发送的数据
        StringEntity s = new StringEntity(JSON.toJSONString(map));
//        s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
        post.setEntity(s);
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
