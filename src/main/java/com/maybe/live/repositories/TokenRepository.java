package com.maybe.live.repositories;

import com.maybe.live.domain.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chan on 16/5/18.
 */
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findByToken(String token);

    @Modifying
    @Query("UPDATE Token t SET t.isUsed = true WHERE t.email = :email")
    int updateTokenIsUsedByEmail(@Param("email") String email);


}
