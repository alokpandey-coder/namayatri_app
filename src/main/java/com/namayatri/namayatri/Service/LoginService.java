package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Controller.JWTService;
import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.LoginDto;
import com.namayatri.namayatri.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public LoginService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public String Login(LoginDto dto) {

        Optional<User> opName = userRepository.findByUsername(dto.getUsername());

        if(opName.isPresent()){
           User user = opName.get();

           if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){

               String token = jwtService.generateToken(user.getUsername());
               return token;
           }
        }
        return "Invalid Username/Password Try Again!!!!";
    }
}
