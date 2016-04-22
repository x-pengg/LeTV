package com.maybe.live.rmi;

import java.util.HashMap;

/**
 * Created by TateChan on 2016/3/21.
 */
public class DefaultMap extends HashMap<String, String> {

    public DefaultMap(String method, String ver, String userid) {
        this.put("method", method);
        this.put("ver", ver);
        this.put("userid", userid);
        this.put("timestamp", String.valueOf(System.currentTimeMillis()));
    }

}
