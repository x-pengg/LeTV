package com.maybe.live.service;


import com.maybe.live.domain.User;

/**
 * Created by Tate on 2016/5/2 0002.
 */
public interface IUserService {

    User login(String email, String password);

    User login(String email);

    void register(User user);

    void registerTokenValidate(String token);

    void forgotTokenValidate(String token);

    void clearTokenOfRegistered(String token);

    void clearTokenOfReset(String email);

    void resetPassword(String newPassword, String email);

    void modifyName(String newName, Integer uid);

    void modifyCover(String coverPath, Integer uid);

    boolean userIsValid(String email);

    User findUserByToken(String token);





}
