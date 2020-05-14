package dev.peertosir.publicstonks.psapi.service.users;

import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);
}
