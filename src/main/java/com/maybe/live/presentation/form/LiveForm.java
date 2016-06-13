package com.maybe.live.presentation.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tate on 2016/6/1 0001.
 */
@Data
public class LiveForm {

    @NotEmpty(message = "直播名称不能为空")
    @Size(max = 150, message = "直播名称最大为300个字节")
    private String title;

    @Size(max = 250, message = "直播简介最大为500个字节")
    private String summary;

    @NotNull(message = "直播类型不能为空")
    private Integer typeId;

    @NotNull(message = "播放模式不能为空")
    private Integer playMode;

    @NotEmpty(message = "清晰度不能为空")
    private String codeRate;

}
