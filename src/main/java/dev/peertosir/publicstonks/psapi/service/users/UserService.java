package dev.peertosir.publicstonks.psapi.service.users;

import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
