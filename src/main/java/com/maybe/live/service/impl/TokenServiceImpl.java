package com.maybe.live.service.impl;

import com.maybe.live.domain.Token;
import com.maybe.live.repositories.TokenRepository;
import com.maybe.live.service.ITokenService;
import com.maybe.live.support.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Tate
 * @date: 2016/5/24 9:42
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public Token createToken(String mailAddress) {
        Token token = new Token();
        token.setEmail(mailAddress);
        token.setToken(TokenUtils.generateNewToken());
        token.setExpiryDate(TokenUtils.calculateExpiryDate());
        token.setIsUsed(false);
       return tokenRepository.save(token);
    }
}
