package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.UserDto;
import com.namayatri.namayatri.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/new-user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService, UserDto dto){
        this.userService=userService;

    }
    //URL: http://localhost:8080/api/new-user/signup
    @PostMapping("/signup")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto dto){

        UserDto savedResponse =userService.createNewUser(dto);

        return new ResponseEntity<>(savedResponse,HttpStatus.CREATED);

    }
}
