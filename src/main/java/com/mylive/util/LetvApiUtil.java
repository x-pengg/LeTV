package com.mylive.util;

import java.security.MessageDigest;
import java.util.*;

/**
 * Created by TateChan on 2016/3/21.
 */
public class LetvApiUtil {


    public static String digest(Map<String, String> params, String secret) {
        StringBuilder result = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());// map中的参数是区分大小写的
        Collections.sort(keys);
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            if (!"sign".equalsIgnoreCase(key)) { // 必须这么写，set里调用remove方法，无法忽略大小写;request里的map无法remove
                result.append(key).append(params.get(key));
            }
        }
        result.append(secret);
        return LetvApiUtil.MD5(result.toString());
    }


    /**
     * generate MD5
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String MD5(String src) {
        try {
            if (src == null) {
                return "";
            }
            byte[] result = null;
            MessageDigest alg = MessageDigest.getInstance("MD5");
            result = alg.digest(src.getBytes("utf-8"));
            return byte2hex(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    /**
     * generate MD5
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String MD5(byte[] src) {
        try {
            if (src == null) {
                return "";
            }
            byte[] result = null;
            MessageDigest alg = MessageDigest.getInstance("MD5");
            result = alg.digest(src);
            return byte2hex(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static String byte2hex(byte[] b) {
        if (b == null) {
            return "";
        }
        StringBuffer hs = new StringBuffer();
        String stmp = null;
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0");
            }
            hs.append(stmp);
        }
        return hs.toString();
    }


}
