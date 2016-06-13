package com.maybe.live.service;

import com.maybe.live.domain.Content;
import com.maybe.live.domain.Type;

import java.util.List;

/**
 * @author: Tate
 * @date: 2016/6/1 14:19
 */
public interface ILiveService {

    void createLive(Content content);

    List<Type> getAllType();

    Content getLiveOfUser(Integer uid);

}
