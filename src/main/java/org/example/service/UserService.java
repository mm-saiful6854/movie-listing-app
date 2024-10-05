package org.example.service;

import lombok.NonNull;
import org.example.model.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    Map<String, UserDTO> users = new HashMap<>();


    public UserDTO userRegistration(String email) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        users.put(userDTO.getEmail(), userDTO);
        return userDTO;
    }


    public UserDTO updateUserProfile(@NonNull UserDTO userDTO) throws Exception {
        if(userDTO.getEmail()==null || !users.containsKey(userDTO.getEmail())) {
            throw new Exception("User is not found");
        }
        UserDTO user = users.get(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setReligion(userDTO.getReligion());
        return user;
    }
}
