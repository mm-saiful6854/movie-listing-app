package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.model.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<String, UserDTO> users = new HashMap<>();


    public UserDTO userRegistration(String email) throws IllegalArgumentException {
        if(users.containsKey(email)) {
           throw new IllegalArgumentException("This email: "+email +" is already registered.");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        users.put(userDTO.getEmail(), userDTO);
        return userDTO;
    }


    public UserDTO updateUserProfile(@NonNull UserDTO userDTO) throws Exception {
        if(userDTO.getEmail()==null || !users.containsKey(userDTO.getEmail())) {
            throw new Exception("User " + (userDTO.getEmail()!=null?userDTO.getEmail():"") + " is not valid user to system. Please register first.");
        }
        UserDTO user = users.get(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setReligion(userDTO.getReligion());
        return user;
    }

    public boolean addMovieToUserFavoriteList(String userEmail, MovieDTO movie) {
        UserDTO user = users.get(userEmail);
        if(user==null) {
            throw new IllegalArgumentException("User "+userEmail+" is not registered.");
        }
        return user.getFavoriteMovieList().add(movie);
    }

    public boolean removeMovieToUserFavoriteList(String userEmail, MovieDTO movie) {
        UserDTO user = users.get(userEmail);
        if(user==null) {
            throw new IllegalArgumentException("User "+userEmail+" is not registered.");
        }
        return user.getFavoriteMovieList().remove(movie);
    }

    public List<MovieDTO> getUserFavoriteList(String userEmail) {
        UserDTO user = users.get(userEmail);
        if(user==null) {
            throw new IllegalArgumentException("User "+userEmail+" is not registered.");
        }
        return user.getFavoriteMovieList();
    }

    public UserDTO validateUser(String email) {
        return users.get(email);
    }
}
