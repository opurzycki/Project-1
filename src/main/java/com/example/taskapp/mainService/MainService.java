package com.example.taskapp.mainService;

import com.example.taskapp.model.User;
import com.example.taskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MainService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }
    // mikroserwis do zapisu użytkownika do DB
    public boolean registerUser(User user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch(NullPointerException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
