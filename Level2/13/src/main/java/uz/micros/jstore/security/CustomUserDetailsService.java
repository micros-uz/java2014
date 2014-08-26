package uz.micros.jstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.AppUser;
import uz.micros.jstore.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AppUser appUser = userService.getUserByUserName(userName);

        if (appUser != null)
            return new User(appUser.getUsername(), appUser.getPassword(),
                    true, true, true, true, getAuthorities());
        else
            throw new UsernameNotFoundException("No user with username '" + userName + "' found!");
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
