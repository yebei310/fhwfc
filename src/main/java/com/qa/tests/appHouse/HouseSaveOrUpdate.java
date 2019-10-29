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
public class HouseSaveOrUpdate extends TestBase {
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
        HttpPost post = new HttpPost("https://api.izhiliao.com/house/saveOrUpdate?lpId=4776952&source=2&recommand=0&propertyYear=70&agentId=0&bathroomStr=1卫&propetyYear=70&score=0&totalVisit=0&userArea=50&versionNumber=2.3.1&visitType=随时看房&picNum=0&nonceStr=1571301567827.404&houseStructure=平层&houseType=住宅&lpName=湖州佳园&heatingStr=集中供暖&fitmentStr=简装&lpAddr=杭州周边-田盛街,近大东路&siteId=22632&dayCount=0&dealYear=满二&dealOnly=是&elevatorStr=有&locationId=637613&areaId=837098&geoLocation=30.881801,120.117271&bedroom=2&bedroomStr=2室&livingroom=1&livingroom=1厅&bathroom=1&kitchenroom=1&kitchenroomStr=1厨&floor=1&totalFloor=12&buildType=板楼&orientation=东&fitment=简装&buildYear=2000&buildArea=65&useArea=60&propertyYear=2017&propertyType=商品房&totalPrice=2511&fee=0.010000&sourceTitle=高价房源速度来看56平262万&sourceDesc=还睡觉忘记忘记忘记忘记忘记忘记忘记就忘记我&pics=https://s0.ifengimg.com/2019/10/17/0788200bbc7048609f268b9c2439c02d.jpg|https://s0.ifengimg.com/2019/10/17/0788200bbc7048609f268b9c2439c02d.jpg|室内图,https://s2.ifengimg.com/2019/10/17/4cec846196b04e2786a343a00cc590c5.jpg|https://s2.ifengimg.com/2019/10/17/4cec846196b04e2786a343a00cc590c5.jpg|户型图&supportLift=有");
//        HttpPost post = new HttpPost("https://api.izhiliao.com/house/saveOrUpdate");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzEyODE3NzIsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzE1NDA5NzJ9.X3FjovpVeJH4LpZB9j--GAAY_SkNYG-oSnyFqjXQUjY");
        //发送post请求?houseId=2747683&type=1

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("lpId", "4776952");
//        map.put("lpName", "湖州佳园");
//        map.put("recommand", "0");
//        map.put("propertyYear", "70");
//        map.put("picNum", "0");
//        map.put("nonceStr", "1571301567827.404");
//        map.put("heatingStr", "集中供暖");
//        map.put("houseType", "住宅");
//        map.put("houseStructure", "平层");
//        map.put("floor", "1");
//        map.put("lpAddr", "杭州周边-田盛街,近大东路");
//        map.put("siteId", "22632");
//        map.put("locationId", "湖州佳园");
//        map.put("lpName", "637613");
//        map.put("areaId", "837098");
//        map.put("geoLocation", "30.881801,120.117271");
//        map.put("bedroom", "2");
//        map.put("bedroomStr", "2室");
//        map.put("livingroom", "1");
//        map.put("livingroomStr", "1厅");
//        map.put("bathroom", "1");
//        map.put("kitchenroom", "1");
//        map.put("kitchenroomStr", "1厨");
//        map.put("totalFloor", "12");
//        map.put("buildType", "板楼");
//        map.put("orientation", "东");
//        map.put("fitment", "简装");
//        map.put("fitmentStr", "简装");
//        map.put("fee", "0.010000");
//        map.put("buildYear", "2000");
//        map.put("buildArea", "65");
//        map.put("useArea", "65");
//        map.put("propertyYear", "2000");
//        map.put("totalPrice", "2512");
//        map.put("dayCount", "0");
//        map.put("dealYear", "满二");
//        map.put("dealOnly", "是");
//        map.put("elevatorStr", "有");
//        map.put("propertyType", "255");
//        map.put("sourceTitle", "高价啊房源速度来看56平262万");
//        map.put("source", "2");
//        map.put("sourceDesc", "还睡觉忘记忘记忘记忘记忘记忘记忘记就忘记我");
//        map.put("supportLift", "有");
//        map.put("pics", "https://s0.ifengimg.com/2019/10/17/0788200bbc7048609f268b9c2439c02d.jpg|https://s0.ifengimg.com/2019/10/17/0788200bbc7048609f268b9c2439c02d.jpg|室内图,https://s2.ifengimg.com/2019/10/17/4cec846196b04e2786a343a00cc590c5.jpg|https://s2.ifengimg.com/2019/10/17/4cec846196b04e2786a343a00cc590c5.jpg|户型图");
//        map.put("agentId", "0");
//        map.put("bathroomStr", "1卫");
//        map.put("propetyYear", "70");
//        map.put("score", "0");
//        map.put("totalVisit", "0");
//        map.put("userArea", "50");
//        map.put("versionNumber", "2.3.1");
//        map.put("visitType", "随时看房");
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





























