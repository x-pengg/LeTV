package com.maybe.live.service.impl;

import com.google.common.collect.Lists;
import com.maybe.live.domain.Content;
import com.maybe.live.domain.Type;
import com.maybe.live.kit.CalendarKit;
import com.maybe.live.kit.ResourceKit;
import com.maybe.live.kit.le.LetvApiClient;
import com.maybe.live.repositories.ContentRepository;
import com.maybe.live.repositories.TypeRepository;
import com.maybe.live.service.ILiveService;
import com.maybe.live.support.Config;
import com.maybe.live.support.GenericException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tate
 * @date: 2016/6/1 14:30
 */
@Service
public class LiveServiceImpl implements ILiveService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private TypeRepository typeRepository;

    private static final Logger _log = LoggerFactory.getLogger(LiveServiceImpl.class);

    @Override
    public void createLive(Content content) {
        content.setStatus(true);
        contentRepository.save(content);

       /* LetvApiClient letvApiClient = newLetvApiClient();

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("activityName", content.getTitle());
            param.put("startTime", CalendarKit.format(new Date(), "yyyMMddHHmmss"));
            param.put("endTime", CalendarKit.format(CalendarKit.addHour(new Date(), 6),"yyyMMddHHmmss"));
            param.put("coverImgUrl", "http://cdn.volios.me/d8eda892-0e55-448f-8fae-7d7b99e814c7");
            param.put("codeRateTypes", content.getCodeRate());
            param.put("description", content.getSummary());
            param.put("playMode", content.getPlayMode().toString());
            param.put("liveNum", "1");
            param.put("needRecord", "0");
            param.put("needTimeShift", "0");
            param.put("needFullView", "0");
            param.put("activityCategory", "013");
            HttpResponse httpResponse = letvApiClient.executePost("lecloud.cloudlive.activity.create", "3.1", param);
            HttpEntity entity = httpResponse.getEntity();
            _log.info("创建直播：{}", EntityUtils.toString(entity, "GBK"));

        } catch (IOException e) {
            e.printStackTrace();
            throw GenericException.withCode(501);
        }*/
    }

    @Override
    public List<Type> getAllType() {
        List<Type> types = Lists.newArrayList();
        /*Iterator<Type> iterator = typeRepository.findAll().iterator();
        while (iterator.hasNext()) {
            types.add(iterator.next());
        }
        */
        typeRepository.findAll().forEach(type -> types.add(type));
        return types;
    }

    @Override
    public Content getLiveOfUser(Integer uid) {
        return contentRepository.findByUserId(uid);
    }

   /* public LetvApiClient newLetvApiClient(){
        LetvApiClient letvApiClient = new LetvApiClient();
        letvApiClient.setUserid(Config.LELIVE_USERID);
        letvApiClient.setSecret(Config.LELIVE_SECRET);
        return letvApiClient;
    }*/
}
