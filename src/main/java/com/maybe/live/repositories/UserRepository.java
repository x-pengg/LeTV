package com.maybe.live.repositories;

import com.maybe.live.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chan on 16/4/28.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User t SET t.enabled = true WHERE t.email = :email")
    int updateEnabledByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User t SET t.name = :name WHERE t.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Integer id);

    @Modifying
    @Query("UPDATE User t SET t.password = :password WHERE t.email = :email")
    int updatePasswordByEmail(@Param("password") String password, @Param("email") String email);
}
