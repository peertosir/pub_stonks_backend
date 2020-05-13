package dev.peertosir.publicstonks.psapi.service.users.impl;

import dev.peertosir.publicstonks.psapi.entity.UserEntity;
import dev.peertosir.publicstonks.psapi.repository.UserRepository;
import dev.peertosir.publicstonks.psapi.service.users.UserService;
import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        UserEntity savedUser = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser, returnValue);
        return returnValue;
    }
}
