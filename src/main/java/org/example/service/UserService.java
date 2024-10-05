package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.model.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UserService class handles user registration, profile updates, and management
 * of users' favorite movie lists.
 */
public class UserService {

    /** A map that stores users with their email as the key and UserDTO as the value. */
    private final Map<String, UserDTO> users = new HashMap<>();

    /**
     * Registers a new user to the system using their email address.
     *
     * @param email the email address of the user to register.
     * @return the newly registered UserDTO object.
     * @throws IllegalArgumentException if the email is already registered in the system.
     */
    public UserDTO userRegistration(String email) throws IllegalArgumentException {
        if (users.containsKey(email)) {
            throw new IllegalArgumentException("This email: " + email + " is already registered.");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        users.put(userDTO.getEmail(), userDTO);
        return userDTO;
    }

    /**
     * Updates the profile of an existing user.
     *
     * @param userDTO the UserDTO object containing updated profile information.
     * @return the updated UserDTO object.
     * @throws Exception if the user is not registered in the system.
     */
    public UserDTO updateUserProfile(@NonNull UserDTO userDTO) throws Exception {
        if (userDTO.getEmail() == null || !users.containsKey(userDTO.getEmail())) {
            throw new Exception("User " + (userDTO.getEmail() != null ? userDTO.getEmail() : "") +
                    " is not valid in the system. Please register first.");
        }
        UserDTO user = users.get(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setReligion(userDTO.getReligion());
        return user;
    }

    /**
     * Adds a movie to the user's favorite movie list.
     *
     * @param userEmail the email of the user.
     * @param movie     the MovieDTO object representing the movie to be added.
     * @return true if the movie was successfully added, false otherwise.
     * @throws IllegalArgumentException if the user is not registered in the system.
     */
    public boolean addMovieToUserFavoriteList(String userEmail, MovieDTO movie) {
        UserDTO user = users.get(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User " + userEmail + " is not registered.");
        }
        return user.getFavoriteMovieList().add(movie);
    }

    /**
     * Removes a movie from the user's favorite movie list.
     *
     * @param userEmail the email of the user.
     * @param movie     the MovieDTO object representing the movie to be removed.
     * @return true if the movie was successfully removed, false otherwise.
     * @throws IllegalArgumentException if the user is not registered in the system.
     */
    public boolean removeMovieToUserFavoriteList(String userEmail, MovieDTO movie) {
        UserDTO user = users.get(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User " + userEmail + " is not registered.");
        }
        return user.getFavoriteMovieList().remove(movie);
    }

    /**
     * Retrieves the list of favorite movies for a user.
     *
     * @param userEmail the email of the user.
     * @return a list of MovieDTO objects representing the user's favorite movies.
     * @throws IllegalArgumentException if the user is not registered in the system.
     */
    public List<MovieDTO> getUserFavoriteList(String userEmail) {
        UserDTO user = users.get(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User " + userEmail + " is not registered.");
        }
        return user.getFavoriteMovieList();
    }

    /**
     * Validates if a user with a given email exists in the system.
     *
     * @param email the email of the user to validate.
     * @return the UserDTO object if the user exists, null otherwise.
     */
    public UserDTO validateUser(String email) {
        return users.get(email);
    }
}
