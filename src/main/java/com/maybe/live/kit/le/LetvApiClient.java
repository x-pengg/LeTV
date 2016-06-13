package com.maybe.live.kit.le;

import com.maybe.live.support.Config;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tate on 2016/3/21.
 */
@Data
public class LetvApiClient {
    Logger logger = LoggerFactory.getLogger(LetvApiClient.class);

    private String host = Config.LELIV_EHOST;
    private String userid = Config.LELIVE_USERID;
    private String secret = Config.LELIVE_SECRET;
    private String apiUrl;
    private static final LetvApiClient instance = new LetvApiClient();


    public HttpResponse executeGet(String method, String ver, Map<String, String> params) throws IOException {
        return this.executeGet(method, ver, params, null);
    }

    public HttpResponse executePost(String method, String ver, Map<String, String> params) throws IOException {
        return this.executePost(method, ver, params, null);
    }

    public HttpResponse executeGet(String method, String ver, Map<String, String> params, Map<String, String> headers)
            throws IOException {
        Map<String, String> map = new DefaultMap(method, ver, this.getUserid());
        if (params != null) {
            map.putAll(params);
        }
        String sign = LetvApiKit.digest(map, this.getSecret());
        map.put("sign", sign);

        StringBuffer uri = new StringBuffer(this.host);
        uri.append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            uri.append(entry.getKey());
            uri.append("=");
            uri.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
            uri.append("&");
        }

        apiUrl = uri.toString();
        HttpGet get = new HttpGet(apiUrl);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HttpClient httpClient = HttpClients.createDefault();
        long start = System.currentTimeMillis();
        HttpResponse response = httpClient.execute(get);
        long cost = System.currentTimeMillis() - start;
        logger.debug("LetvApi Get cost={}ms, statusCode={}, url={}", cost, response.getStatusLine().getStatusCode(), apiUrl);

        return response;
    }

    public HttpResponse executePost(String method, String ver, Map<String, String> params, Map<String, String> headers)
            throws IOException {

        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        Map<String, String> map = new DefaultMap(method, ver, this.getUserid());
        if (params != null) {
            map.putAll(params);
        }
        String sign = LetvApiKit.digest(map, this.getSecret());
        map.put("sign", sign);

        List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
        StringBuffer uri = new StringBuffer(this.host);
        apiUrl = uri.toString();
        HttpPost post = new HttpPost(apiUrl);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            post.addHeader(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> body : map.entrySet()) {
            data.add(new BasicNameValuePair(body.getKey(), body.getValue()));
            uri.append(body.getKey()).append("=").append(body.getValue()).append(";");
        }

        HttpClient httpClient = HttpClients.createDefault();
        long start = System.currentTimeMillis();
        post.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
        HttpResponse response = httpClient.execute(post);
        long cost = System.currentTimeMillis() - start;
        logger.debug("LetvApi Post cost={}ms, statusCode={}, url={}，params={}", cost, response.getStatusLine()
                .getStatusCode(), apiUrl, uri);

        return response;
    }
}
