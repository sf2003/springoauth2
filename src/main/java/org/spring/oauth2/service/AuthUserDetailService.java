package org.spring.oauth2.service;

import org.spring.oauth2.domain.User;
import org.spring.oauth2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sun.swing.StringUIClientPropertyKey;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by stzhang on 2016/9/21.
 */
@Component
public class AuthUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u = userRepo.findByName(username);
        if(u == null){
            throw new UsernameNotFoundException("not found user: username="+username);
        }
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                ArrayList authorities =  new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ANYONE"));
                String[] userRoles = u.getUserRoles();
                if(userRoles != null && userRoles.length > 0){
                     for (String roleName : userRoles) {
                        authorities.add(new SimpleGrantedAuthority(roleName.trim()));
                     }
                }

//                authorities.add(new SimpleGrantedAuthority(""));
                return authorities;
            }

            @Override
            public String getPassword() {
                return u.getPassword();
            }

            @Override
            public String getUsername() {
                return u.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        return userDetails;
    }
}
