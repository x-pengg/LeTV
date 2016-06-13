package com.maybe.live.kit.le;

import com.maybe.live.kit.MD5;

import java.util.*;

/**
 * Created by Tate on 2016/3/21.
 */
public class LetvApiKit {

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
        return MD5.encrypt(result.toString());
    }

}
