package com.movies.demo.service;
import com.movies.demo.dto.response.UserResponse;
import com.movies.demo.entity.UserEntity;
import com.movies.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Map;
import com.movies.demo.service.JwtService;
import com.movies.demo.dto.response.UserResponse;
import com.movies.demo.dto.request.SignupRequest;
import com.movies.demo.dto.request.LoginRequest;
import com.movies.demo.dto.response.AuthResponse;
import java.util.stream.Collectors;
import com.movies.demo.exception.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired 
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers(){
        return userRepo.findAll()
        .stream()
        .map(user->new UserResponse(user.getId(),user.getUsername()))
        .collect(Collectors.toList());
    }

    public UserResponse getUserById(Integer id){
UserEntity user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with this id " + id));
return new UserResponse(user.getId(),user.getUsername());

    }
   
    
    public UserResponse signup(SignupRequest request){
        Optional<UserEntity> existing=userRepo.findByUsername(request.getUsername());
        if(existing.isPresent()){
            throw new RuntimeException("Username already exists");
        }
        UserEntity user= new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity saved= userRepo.save(user);
        return new UserResponse(saved.getId(),saved.getUsername());
    }

//     public Map<String,String> login(UserEntity user){
//         Optional<UserEntity> existing= userRepo.findByUsername(user.getUsername());
//         if(existing.isEmpty()){
//             throw new RuntimeException("User does not exist, please signup first");
//         }
//         UserEntity foundUser=existing.get();
//         if(!foundUser.getPassword().equals(user.getPassword())){
//             throw new RuntimeException("Incorrect password");
//         }
// return foundUser;
//     }


public AuthResponse login(LoginRequest request) {
    Optional<UserEntity> existing = userRepo.findByUsername(request.getUsername());
    if (existing.isEmpty()) {
        throw new RuntimeException("User does not exist, please signup first");
    }
    UserEntity foundUser = existing.get();
    if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
        throw new RuntimeException("Incorrect password");
    }
    String token = jwtService.generateToken(foundUser.getUsername());
   return new AuthResponse(token,foundUser.getUsername());
}

    public UserResponse updateUser(Integer id, SignupRequest request){
       UserEntity existing= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cannot find user"+id));
        existing.setUsername(request.getUsername());
        existing.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity saved=userRepo.save(existing);
        return new UserResponse(saved.getId(),saved.getUsername());
    }

public  String deleteUserById(Integer id){
userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found wih this id"+id));
userRepo.deleteById(id);
return "User deleted Successfully";
}


}
