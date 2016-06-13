package com.maybe.live.service;

import com.maybe.live.domain.Token;

/**
 * @author: Tate
 * @date: 2016/5/24 9:41
 */
public interface ITokenService {

    Token createToken(String mailAddress);
}
