package dev.peertosir.publicstonks.psapi.controller;

import dev.peertosir.publicstonks.psapi.model.request.UserDetailsRequestModel;
import dev.peertosir.publicstonks.psapi.model.response.UserRest;
import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dev.peertosir.publicstonks.psapi.service.users.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser() {
        return "Get user";
    }

    @PostMapping()
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping()
    public String updateUser() {
        return "Update user";
    }

    @DeleteMapping()
    public String deleteUser() {
        return "Delete user";
    }

}
