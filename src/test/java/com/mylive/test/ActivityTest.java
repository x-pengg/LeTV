package com.mylive.test;

import com.mylive.rmi.LetvApiClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TateChan on 2016/3/21.
 */
public class ActivityTest {

    private static String userId = "";
    private static String secret = "";
    private static String ver = "3.0";

    @Test
    public void Search() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A2016032100001aj");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.search", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Create() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        try {

            Map<String, String> param = new HashMap<String, String>();
            param.put("activityName", "测试活动");
            param.put("startTime", "20160321204655");
            param.put("endTime", "20160321224655");
            param.put("liveNum", "1");
            param.put("codeRateTypes", "99");
            param.put("needRecord", "0");
            param.put("needTimeShift", "0");
            param.put("needFullView", "0");
            param.put("activityCategory", "013");
            param.put("playMode", "0");

            HttpResponse httpResponse = letvApiClient.executePost("letv.cloudlive.activity.create", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUrl() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A2016032100001aj");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.playerpage.getUrl", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
