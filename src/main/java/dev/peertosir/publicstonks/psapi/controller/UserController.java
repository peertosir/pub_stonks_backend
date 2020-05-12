package dev.peertosir.publicstonks.psapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping()
    public String getUser() {
        return "Get user";
    }

    @PostMapping()
    public String createUser() {
        return "Create user";
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
