package com.maybe.live.kit;

import java.security.MessageDigest;

/**
 * Created by Tate on 2016/5/9 0009.
 */
public class MD5 {

    /**
     * generate MD5
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String encrypt(String src) {
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
