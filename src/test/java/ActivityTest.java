import com.maybe.live.kit.le.LetvApiClient;
import com.maybe.live.support.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tate on 2016/3/21.
 */
public class ActivityTest {

    private static String userId = Config.LELIVE_USERID;
    private static String secret = Config.LELIVE_SECRET;
    private static String ver = "3.1";

    @Test
    public void Search() {
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(userId);
        letvApiClient.setSecret(secret);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("activityId", "A201604210000219");
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
        param.put("activityId", "A2016042300001dx");
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
            param.put("activityName", "感觉自己萌萌哒OwO");
            param.put("startTime", "20160423104655");
            param.put("endTime", "20160423234655");
            param.put("liveNum", "1");
            param.put("codeRateTypes", "99");
            param.put("needRecord", "0");
            param.put("needTimeShift", "0");
            param.put("needFullView", "0");
            param.put("activityCategory", "013");
            param.put("playMode", "0");

            HttpResponse httpResponse = letvApiClient.executePost("lecloud.cloudlive.activity.create", ver, param);
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
        param.put("activityId", "A201604210000219");
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
        param.put("activityId", "A201604210000219");
        try {
            HttpResponse httpResponse = letvApiClient.executeGet("letv.cloudlive.activity.getPlayInfo", ver, param);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity, "GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
