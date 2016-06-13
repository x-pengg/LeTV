package com.maybe.live.repositories;

import com.maybe.live.domain.Content;
import com.maybe.live.domain.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author: Tate
 * @date: 2016/6/1 14:14
 */
public interface TypeRepository extends CrudRepository<Type, Long> {


}
