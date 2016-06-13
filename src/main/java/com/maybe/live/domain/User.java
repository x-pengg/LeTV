package com.maybe.live.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by chan on 16/4/28.
 */
@Entity
@Table(name = "live_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "head_img")
    private String headImg;

    @Column
    private boolean enabled;

}
