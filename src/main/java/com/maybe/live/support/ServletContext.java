package com.maybe.live.support;

import com.maybe.live.domain.Type;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author: Tate
 * @date: 2016/6/2 10:54
 */
public class ServletContext {

    public static List<Type> liveTypes;

    public static List<Type> getLiveTypes() {
        if (CollectionUtils.isNotEmpty(liveTypes)) {
            return liveTypes;
        } else {
            return Collections.emptyList();
        }
    }
}
