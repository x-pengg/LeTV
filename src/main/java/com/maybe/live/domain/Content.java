package com.maybe.live.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: Tate
 * @date: 2016/6/1 14:03
 */
@Entity
@Table(name = "live_content")
@Data
@EqualsAndHashCode(callSuper = false)
public class Content extends BaseModel {

    @Column(nullable = false)
    private String title;

    @Column
    private String summary;

    @Column(nullable = false, name = "tid")
    private Integer typeId;

    @Column(nullable = false, name = "uid")
    private Integer userId;

    @Column(nullable = false, name = "play_mode")
    private Integer playMode;

    @Column(nullable = false, name = "code_rate")
    private String codeRate;

    @Column(nullable = false)
    private Boolean status;
}
