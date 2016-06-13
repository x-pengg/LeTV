package com.maybe.live.support;

import com.maybe.live.kit.ResourceKit;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Tate
 * @date: 2016/5/19 13:08
 */
@Getter
@Setter
public class CustomResp {
    private Long timestamp = System.currentTimeMillis();
    private int code;
    private String message;
    private Object body;

    public CustomResp(int code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public CustomResp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomResp withEx(GenericException e) {
        return new CustomResp(e.getCode(), e.getMessage());
    }

    public static CustomResp withMsgKey(Integer key) {
        return new CustomResp(key, ResourceKit.getErrorMsg(key.toString()));
    }

    public static CustomResp withMsgKey(Integer key, Object body) {
        return new CustomResp(key, ResourceKit.getErrorMsg(key.toString()), body);
    }

}
