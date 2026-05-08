package com.movies.demo.controller;
import com.movies.demo.service.UserService;
import com.movies.demo.dto.request.SignupRequest;
import com.movies.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.movies.demo.dto.response.UserResponse;
import com.movies.demo.dto.response.AuthResponse;
import com.movies.demo.dto.request.LoginRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

     @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    
    @PostMapping("/signup")
    public UserResponse signup(@Valid  @RequestBody SignupRequest request){
        return userService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login( @Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }


    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @Valid @RequestBody SignupRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }
}
