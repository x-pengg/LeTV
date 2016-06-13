package com.maybe.live.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tate on 2016/5/2 0002.
 */

@MappedSuperclass
@Data
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;


    public BaseModel() {
        this.createTime = this.createTime == null ? new Date() : this.createTime;
        this.updateTime = this.updateTime == null ? new Date() : this.updateTime;
    }

}
