package com.maybe.live.support;

import com.google.common.base.Strings;
import com.maybe.live.kit.ResourceKit;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Tate
 * @date: 2016/5/10 16:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GenericException extends RuntimeException {

    private Integer code;

    public GenericException(Integer code) {
        super();
        this.code = code;
    }

    public GenericException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public GenericException(Integer code, String msg, Exception e) {
        super(msg, e);
        this.code = code;
    }

    public static GenericException withCode(Integer code, String msg, Exception e) {
        String message = Strings.isNullOrEmpty(msg) ? ResourceKit.getErrorMsg(code.toString()) : msg;
        message = Strings.nullToEmpty(message);
        if (e == null) {
            return new GenericException(code, message);
        }
        return new GenericException(code, message, e);
    }


    public static GenericException withCode(Integer code, String msg) {
        return withCode(code, msg, null);
    }


    public static GenericException withCode(Integer code, Exception e) {
        return withCode(code, "", e);
    }

    public static GenericException withCode(Integer code) {
        return withCode(code, "");
    }


}
