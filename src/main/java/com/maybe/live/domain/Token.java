package com.maybe.live.domain;

import com.maybe.live.support.LocalDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: Tate
 * @date: 2016/5/18 16:40
 */
@Entity
@Table(name = "live_token")
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String token;

    @Column(name = "expiry_date", nullable = false, columnDefinition = "datetime")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime expiryDate;

    @Column(name = "status", nullable = false)
    private Boolean isUsed;


}
