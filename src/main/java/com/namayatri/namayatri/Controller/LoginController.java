package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.JWTTokenDto;
import com.namayatri.namayatri.Payload.LoginDto;
import com.namayatri.namayatri.Repository.UserRepository;
import com.namayatri.namayatri.Service.LoginService;
import com.namayatri.namayatri.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {

        this.loginService = loginService;
    }

//"/api/user/login"
    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto dto){

        String  TokenValue= loginService.Login(dto);

        JWTTokenDto jwtToken = new JWTTokenDto();
        jwtToken.setToken(TokenValue);
        jwtToken.setType("JWT");

        return new ResponseEntity<>(jwtToken, HttpStatus.ACCEPTED);
    }
}
