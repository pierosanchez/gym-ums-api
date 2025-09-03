package com.pial.gym.gymapi.config.providers;

import com.pial.gym.gymapi.entity.UserEntity;
import com.pial.gym.gymapi.entity.RolePermissionEntity;
import com.pial.gym.gymapi.repository.IRoleRepository;
import com.pial.gym.gymapi.repository.IUserRepository;
import com.pial.gym.gymapi.repository.IRolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailServiceProvider implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private IRolePermissionRepository iRolePermissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<RolePermissionEntity> listRolePermissionEntity = iRolePermissionRepository.findAllByRoleId(userEntity.getRole().getId());
        listRolePermissionEntity.forEach(rolePermissionEntity -> {
            authorities.add(new SimpleGrantedAuthority(rolePermissionEntity.getPermission().getCode()));
        });
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .disabled(userEntity.getStatus())
                .roles(userEntity.getRole().getCode())
                .authorities(authorities)
                .build();
    }
}
