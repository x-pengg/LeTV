package com.maybe.live.service;

/**
 * @author: Tate
 * @date: 2016/5/23 15:35
 */
public interface ISendMailService {

    void sendEmailOfRegistered(String mailAddress, String token);

    void sendEmailOfForGot(String mailAddress, String token, String ip, String ua, String datetime);

}
