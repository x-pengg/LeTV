package com.maybe.live.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: Tate
 * @date: 2016/6/1 14:01
 */
@Entity
@Table(name = "live_type")
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String value;

}
