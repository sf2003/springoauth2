package org.spring.oauth2.user;

import org.spring.oauth2.domain.User;
import org.spring.oauth2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by stzhang on 2016/9/21.
 */
@RestController
public class UserFetchApi {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/oauth/user/me")
    @ResponseBody
    public UserBaseInfo user(Principal user) {
        UserBaseInfo result = new UserBaseInfo();
        String userName = user.getName();
        result.setName(user.getName());
        User u = userRepo.findByName(userName);
        if(u != null){
            result.setAge(u.getAge());
            result.setDepartId(u.getDepartId());
            result.setId(u.getId());
            result.setRealName(u.getRealName());
            result.setRoles(u.getUserRoles());
        }
        return  result;
    }

    @RequestMapping("/oauth/user/fetchUser")
    @ResponseBody
    public UserBaseInfo user(@RequestParam(required = true) String name) {
        UserBaseInfo result = new UserBaseInfo();
        result.setName(name);
        User u = userRepo.findByName(name);
        if(u != null){
            result.setAge(u.getAge());
            result.setDepartId(u.getDepartId());
            result.setId(u.getId());
            result.setRealName(u.getRealName());
            result.setRoles(u.getUserRoles());
        }
        return  result;
    }

}
