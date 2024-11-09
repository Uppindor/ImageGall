package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CustomUserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    List<User> getAll();


}
