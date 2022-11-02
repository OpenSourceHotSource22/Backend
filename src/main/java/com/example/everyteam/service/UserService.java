package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.UserRequest;
import com.example.everyteam.repository.UserRepository;
import com.example.everyteam.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void joinUser(UserRequest.join req) {
        if(userRepository.countByUserId(req.getId())>0)
            throw new BadRequestException("이미 등록된 id가 있습니다. ");
        String pwd = new SHA256().encrypt(req.getPwd());
        User user = User.builder().id(req.getId()).pwd(pwd).build();
        userRepository.save(user);
    }


    public String login(UserRequest.login req) {
        User user = userRepository.findByUserId(req.getId()).orElseThrow(()->new BadRequestException("등록된 유저가 없습니다."));
        String encPwd = new SHA256().encrypt(req.getPwd());
        if(!user.getPwd().equals(encPwd)) throw new BadRequestException("비밀번호가 올바르지 않습니다.");
        return user.getId();
    }

    public User getUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new BadRequestException("token을 확인하세요"));
        return user;
    }
}
