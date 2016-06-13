package com.maybe.live.kit.qiniu;

import com.maybe.live.support.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author: Tate
 * @date: 2016/5/26 10:55
 */
@Component
public class QiNiuKit {

    private String accessKey;
    private String secretKey;
    private String domain;
    private String bucketName;
    private Auth auth;
    private UploadManager uploadManager;
    private String key;

    public QiNiuKit() {
        this.accessKey = Config.QINIU_ACCESSKEY;
        this.secretKey = Config.QINIU_SECRETKEY;
        this.domain = Config.QINIU_DOMAIN;
        this.bucketName = Config.QINIU_BUCKETNAME;
        this.auth = Auth.create(this.accessKey, this.secretKey);
        //this.key = UUID.randomUUID().toString();
        this.uploadManager = new UploadManager();
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    public void setKey(String key) {
        this.key = key;
    }


    private String getUpToken() {
        return auth.uploadToken(this.bucketName, (String) null, 3600, new StringMap().putNotEmpty("returnBody",
                "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}"));
    }

    public QiNiuPicResp upload(File file, String key) throws QiniuException {
        String token = getUpToken();
        Response res = uploadManager.put(file, key, token);
        QiNiuPicResp qiNiuPicResp = res.jsonToObject(QiNiuPicResp.class);
        return qiNiuPicResp;
    }

    public QiNiuPicResp upload(byte[] bytes, String key) throws QiniuException {
        String token = getUpToken();
        Response res = uploadManager.put(bytes, key, token);
        QiNiuPicResp qiNiuPicResp = res.jsonToObject(QiNiuPicResp.class);
        qiNiuPicResp.setUrl(this.domain + "/" + qiNiuPicResp.getKey());
        return qiNiuPicResp;
    }
}
