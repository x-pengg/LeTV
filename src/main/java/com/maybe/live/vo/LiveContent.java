package com.maybe.live.vo;

import com.maybe.live.domain.Content;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Tate
 * @date: 2016/6/1 14:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LiveContent extends Content {
    private String liveType;
}
