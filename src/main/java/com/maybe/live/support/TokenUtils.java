package com.maybe.live.support;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: Tate
 * @date: 2016/5/18 17:40
 */
public class TokenUtils {

    public static LocalDateTime calculateExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusDays(Config.TOKENEXPIRESDATE);
    }

    public static String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public static boolean tokenExpired(LocalDateTime expiryDate) {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}
