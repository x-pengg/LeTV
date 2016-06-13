package com.maybe.live.repositories;

import com.maybe.live.domain.Content;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: Tate
 * @date: 2016/6/1 14:14
 */
public interface ContentRepository extends CrudRepository<Content, Long> {

    Content findByUserId(Integer uid);
}
