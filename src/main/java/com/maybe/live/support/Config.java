package com.maybe.live.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Tate on 2016/5/9 0009.
 */
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    public static final String USER_SESSION = "USER_SESSION";
    public static final String UTF_8 = "utf-8";
    public static final int NEEDLOGIN_STATUS = 599;
    public static final String RETURN_URL = "RETURN_URL";
    public static final String LIVE_TYPES = "liveTypes";

    private static Properties envprop;

    static {
        Resource res = new ClassPathResource("env.properties");
        try {
            envprop = PropertiesLoaderUtils.loadProperties(res);
        } catch (IOException e) {
            log.error("Reading env.properties fail!", e);
        }
    }

    public static final Integer TOKENEXPIRESDATE = Integer.parseInt(envprop.getProperty("tokenExpiresDate"));

    /**************
     * LeLive
     **************/
    public static final String LELIV_EHOST = envprop.getProperty("leLive.host");
    public static final String LELIVE_USERID = envprop.getProperty("leLive.userId");
    public static final String LELIVE_SECRET = envprop.getProperty("leLive.secret");

    /**************
     * SendEmail
     **************/
    public static final String MAIL_FROM_USER = envprop.getProperty("mail.from.user");
    public static final String MAIL_FROM_PASSWORD = envprop.getProperty("mail.from.password");

    /*************
     * QiNiu
     *************/
    public static final String QINIU_ACCESSKEY = envprop.getProperty("qiniu.accessKey");
    public static final String QINIU_SECRETKEY = envprop.getProperty("qiniu.secretKey");
    public static final String QINIU_BUCKETNAME = envprop.getProperty("qiniu.bucketName");
    public static final String QINIU_DOMAIN = envprop.getProperty("qiniu.domain");
}
