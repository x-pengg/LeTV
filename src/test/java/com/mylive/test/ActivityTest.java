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

    private static String userId = "826763";
    private static String secret = "df116ce79e6509b44f267d75361305d1";
    private static String ver = "3.0";

    @Test
    public void Search() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A2016032600001ek");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.search", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPushUrl() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A2016032600001ek");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.getPushUrl", ver, param);
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
            param.put("startTime", "20160326214655");
            param.put("endTime", "20160326224655");
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
        param.put("activityId", "A2016032600001ek");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.playerpage.getUrl", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPlayInfo() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A2016032600001ek");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.getPlayInfo", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
