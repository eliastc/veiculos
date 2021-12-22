package com.mpinfo.seucarro.domain;

import com.mpinfo.seucarro.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository rep;

    @GetMapping()
    public List<UserDTO> getUsers() {
       return rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());

    }
}
