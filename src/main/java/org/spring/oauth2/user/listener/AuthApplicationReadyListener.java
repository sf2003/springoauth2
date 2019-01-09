package org.spring.oauth2.user.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.oauth2.domain.User;
import org.spring.oauth2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by stzhang on 2016/9/21.
 */
@Component
public class AuthApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = LoggerFactory.getLogger(AuthApplicationReadyListener.class);
    @Autowired
    private UserRepository userRepo;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if(userRepo == null){
            return;
        }
        logger.info("Start to Init default users[user, admin]");

        //init user named user to DB.
        String userName = "user";
        User u = userRepo.findByName(userName);
        if( u == null){
            u = new User();
            u.setName(userName);
            u.setDepartId("001");
            u.setPassword("user");
            u.setRealName("Normal-User");
            u.setUserRoles(new String[]{"ROLE_USER"});
            userRepo.save(u);
        }
        //init user named admin to DB.
        userName = "admin";
        u = userRepo.findByName(userName);
        if( u == null){
            u = new User();
            u.setName(userName);
            u.setDepartId("001");
            u.setPassword("admin");
            u.setRealName("Admin-User");
            u.setUserRoles(new String[]{"ROLE_ADMIN"});
            userRepo.save(u);
        }


    }
}
