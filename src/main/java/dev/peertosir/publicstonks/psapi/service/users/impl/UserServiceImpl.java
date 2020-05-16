package dev.peertosir.publicstonks.psapi.service.users.impl;

import dev.peertosir.publicstonks.psapi.entity.UserEntity;
import dev.peertosir.publicstonks.psapi.exceptions.users.UserNotFoundException;
import dev.peertosir.publicstonks.psapi.model.response.users.UserRest;
import dev.peertosir.publicstonks.psapi.repository.UserRepository;
import dev.peertosir.publicstonks.psapi.service.users.UserService;
import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;
import dev.peertosir.publicstonks.psapi.shared.utils.HashIdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HashIdGenerator hashIdGenerator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, HashIdGenerator hashIdGenerator,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.hashIdGenerator = hashIdGenerator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //Check if user with this email already exists
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        String publicUserId = hashIdGenerator.generateUserId(20);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String id) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        UserDto returnValue = new UserDto();

        if (userDto.getFirstName() != null) {
            userEntity.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            userEntity.setLastName(userDto.getLastName());
        }
        if (userDto.getDescription() != null) {
            userEntity.setDescription(userDto.getDescription());
        }
        UserEntity updatedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageable);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UserNotFoundException();

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
