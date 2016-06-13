package com.maybe.live.kit.qiniu;

import lombok.Data;

/**
 * @author: Tate
 * @date: 2016/5/26 11:55
 */
@Data
public class QiNiuPicResp {

    private long fsize;
    private String key;
    private String url;
    private String hash;
    private int width;
    private int height;

}
