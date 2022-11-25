package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.user.UserRequest;
import com.example.everyteam.repository.UserRepository;
import com.example.everyteam.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.everyteam.config.exception.ErrorResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void joinUser(UserRequest.join req) {
        if(userRepository.countByUserId(req.getId())>0)
            throw new BadRequestException(EXIST_USER);
        String pwd = new SHA256().encrypt(req.getPwd());
        User user = User.builder().id(req.getId()).pwd(pwd).build();
        userRepository.save(user);
    }


    public String login(UserRequest.login req) {
        User user = userRepository.findByUserId(req.getId()).orElseThrow(()->new BadRequestException(NOT_FOUND_USER));
        String encPwd = new SHA256().encrypt(req.getPwd());
        if(!user.getPwd().equals(encPwd)) throw new BadRequestException(INVALID_PWD);
        return user.getId();
    }

    public User getUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new BadRequestException(NOT_FOUND_USER));
        return user;
    }

    ///////TODO : test User List
    public List<User> getAllUserList() {
        return userRepository.findAll();
    }

    public void updateColor(User user, String color) {
        String[] colorList = {"basic", "purple", "earth", "indigo"};
        for(String c : colorList){
            if(c.equals(color)){
                user.setColor(color);
                userRepository.save(user);
                return;
            }
        }
        throw new BadRequestException(INVALID_COLOR);

    }
}
