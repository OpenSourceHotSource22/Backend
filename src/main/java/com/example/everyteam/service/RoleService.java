package com.example.everyteam.service;

import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Role;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.role.RoleRequest;
import com.example.everyteam.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final TeamService teamService;

    @Transactional
    public void createRole(Post post, RoleRequest.createRole req, String code) {
        for(int i=0;i<req.getRole().size();i++){
            String getRole = String.valueOf(req.getRole().get(i).get("role"));
            String getUser = String.valueOf(req.getRole().get(i).get("user"));
            teamService.UserOnTeam(req.getTeamCode(),getUser);
            User reqUser = userService.getUser(getUser);
            Role role = Role.builder().user(reqUser).post(post).role(getRole).code(code).build();
            roleRepository.save(role);
        }
    }
}
