package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.UserDto;
import com.namayatri.namayatri.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,ModelMapper modelMapper){

        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

   User mapToEntity(UserDto dto){
      return  modelMapper.map(dto,User.class);
    }

   UserDto mapToDto(User user){
      return  modelMapper.map(user,UserDto.class);
    }


    public UserDto createNewUser(UserDto dto) {

        Optional<User> opUserName = userRepository.findByUsername(dto.getUsername());
        if(opUserName.isPresent()){
            throw new RuntimeException("UserName already exists in Record");
        }

        Optional<User> opEmailId = userRepository.findByEmail(dto.getEmail());
        if(opEmailId.isPresent()){
            throw new RuntimeException("EmailId is already exists in Record");
        }

        Optional<User> opMobile = userRepository.findByMobile(dto.getMobile());
        if(opMobile.isPresent()){
            throw new RuntimeException("Mobile Number is already exists in Record");
        }

        User user =mapToEntity(dto);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        User userinfo= userRepository.save(user);
        UserDto information =mapToDto(userinfo);
        return information;
    }
}
