package com.mpinfo.seucarro.api.security;

import com.mpinfo.seucarro.domain.User;
import com.mpinfo.seucarro.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRep.findByLogin(username);
        if(user == null) {
            throw  new UsernameNotFoundException("Usuário não encontrado");
        }

        return user;

    }
}
