package com.maybe.live.kit;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Tate
 * @date: 2016/5/20 14:57
 */
@Data
public class Mail implements Serializable {

    private String host;

    private String sender;

    private String receiver;

    private String name;

    private String username;

    private String password;

    private String subject;

    private String message;

    private int smtpPort;

}
