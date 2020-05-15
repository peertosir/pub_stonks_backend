package dev.peertosir.publicstonks.psapi.controller;

import dev.peertosir.publicstonks.psapi.exceptions.users.UserAlreadyExistsException;
import dev.peertosir.publicstonks.psapi.exceptions.users.UserUnprocessableEntityException;
import dev.peertosir.publicstonks.psapi.model.request.users.UserDetailsRequestModel;
import dev.peertosir.publicstonks.psapi.model.response.general.OperationStatusModel;
import dev.peertosir.publicstonks.psapi.model.response.users.UserRest;
import dev.peertosir.publicstonks.psapi.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import dev.peertosir.publicstonks.psapi.service.users.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<UserRest> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
            ) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto user : users) {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(user, userRest);
            returnValue.add(userRest);
        }

        return returnValue;
    }

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        if (userDetails.getFirstName().isEmpty()) {
            throw new UserAlreadyExistsException();
        }

        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping(
            path = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }

    @DeleteMapping(
            path = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName("User deleted");
        userService.deleteUser(id);
        operationStatusModel.setOperationResult("SUCCESS");
        return operationStatusModel;
    }

}
