package com.maybe.live.support;

import com.maybe.live.domain.Type;
import com.maybe.live.kit.WebKit;
import com.maybe.live.service.ILiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Tate
 * @date: 2016/6/1 15:50
 */
@Component
public class AppListener implements ApplicationListener<ApplicationContextEvent> {

    @Autowired
    private ILiveService iLiveService;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        List<Type> liveTypes = iLiveService.getAllType();
        ServletContext.liveTypes = liveTypes;
    }
}
