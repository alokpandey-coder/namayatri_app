package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.ProfileDto;
import com.namayatri.namayatri.Payload.UserDto;
import com.namayatri.namayatri.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    //URL: http://localhost:8080/api/new-user/userInfo
    @GetMapping("/userInfo")
    public ResponseEntity<ProfileDto> getUserProfile(@AuthenticationPrincipal User user){

       ProfileDto info_user = userService.getUserInfo(user);

       return new ResponseEntity<>(info_user,HttpStatus.OK);
    }
}
