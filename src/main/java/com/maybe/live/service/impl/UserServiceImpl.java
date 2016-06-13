package com.maybe.live.service.impl;

import com.maybe.live.domain.Token;
import com.maybe.live.domain.User;
import com.maybe.live.kit.MD5;
import com.maybe.live.repositories.TokenRepository;
import com.maybe.live.repositories.UserRepository;
import com.maybe.live.service.IUserService;
import com.maybe.live.support.GenericException;
import com.maybe.live.support.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * Created by Tate on 2016/5/2 0002.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw GenericException.withCode(204);
        }
        String encrypt = MD5.encrypt(password);
        if (!user.getPassword().toLowerCase().equals(encrypt)) {
            throw GenericException.withCode(202);
        }
        return user;
    }

    @Override
    public User login(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw GenericException.withCode(204);
        }
        return user;
    }

    @Override
    public void register(User user) {
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw GenericException.withCode(203);
        }
        user.setEnabled(false);
        user.setPassword(MD5.encrypt(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void clearTokenOfRegistered(String token) {
        Token tokenObj = tokenRepository.findByToken(token);
        userRepository.updateEnabledByEmail(tokenObj.getEmail());
        tokenRepository.updateTokenIsUsedByEmail(tokenObj.getEmail());
    }

    @Override
    public void clearTokenOfReset(String email) {
        tokenRepository.updateTokenIsUsedByEmail(email);
    }

    @Override
    @Transactional
    public void resetPassword(String newPassword, String email) {
        userRepository.updatePasswordByEmail(MD5.encrypt(newPassword), email);
        this.clearTokenOfReset(email);
    }

    @Override
    public void modifyName(String newName, Integer uid) {
        userRepository.updateNameById(newName, uid);
    }

    @Override
    public void modifyCover(String coverPath, Integer uid) {
    }

    @Override
    public boolean userIsValid(String email) {
        User user = userRepository.findByEmail(email);
        return !ObjectUtils.isEmpty(user);
    }

    @Override
    public User findUserByToken(String token) {
        Token tokenObj = tokenRepository.findByToken(token);
        if (ObjectUtils.isEmpty(tokenObj)) {
            throw GenericException.withCode(205);
        }
        User user = userRepository.findByEmail(tokenObj.getEmail());
        return user;
    }

    @Override
    public void registerTokenValidate(String token) {
        Token regToken = tokenRepository.findByToken(token);
        User user = userRepository.findByEmail(regToken.getEmail());
        if (regToken == null && !user.getEmail().equals(regToken.getEmail())) {
            throw GenericException.withCode(205);
        }
        tokenValidate(regToken);
    }

    @Override
    public void forgotTokenValidate(String token) {
        Token forgotToken = tokenRepository.findByToken(token);
        tokenValidate(forgotToken);
    }


    public void tokenValidate(Token token) {
        if (TokenUtils.tokenExpired(token.getExpiryDate())) {
            throw GenericException.withCode(206);
        }
        if (token.getIsUsed()) {
            throw GenericException.withCode(207);
        }
    }


}
