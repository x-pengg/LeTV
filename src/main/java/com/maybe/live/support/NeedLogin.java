package com.maybe.live.support;

import java.lang.annotation.*;

/**
 * @author: Tate
 * @date: 2016/5/11 19:14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NeedLogin {
}
