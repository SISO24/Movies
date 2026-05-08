package com.movies.demo.dto.request;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank(message="Username cannot be empty")
    //Not blank checks for not null , no empty "", no spaces " "
    @Size(min=3,max=20,message = "Username cannot be between 3 and 20 characters")
    private String username;

    @NotBlank(message="Password cannot be empty")
    @Size(min=6, message="Password must be atleast 6 characters")
    private String password;
    
}
