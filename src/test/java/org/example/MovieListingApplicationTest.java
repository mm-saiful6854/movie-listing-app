package org.example;

import org.example.model.UserDTO;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieListingApplicationTest {

    private static UserService userService;
    private static String userEmail;

    @BeforeAll
    public static void setup() {
        userEmail = "saiful@gmail.com";
        userService = new UserService();
    }


    @Test
    public void userRegistrationByEmail(){
        UserDTO registeredUser = userService.userRegistration(userEmail);
        assertNotNull(registeredUser);
        assertEquals(userEmail, registeredUser.getEmail());
    }

    @Test
    public void editUserPersonalDetails() throws Exception {
        String username="Md Saiful Islam";
        int age= 27;
        String religion = "islam";

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userEmail);
        userDTO.setUsername(username);
        userDTO.setAge(age);
        userDTO.setReligion(religion);
        UserDTO updatedProfile = userService.updateUserProfile(userDTO);

        assertNotNull(updatedProfile);
        assertEquals(username, updatedProfile.getUsername());

        assertThrows(Exception.class, ()->{
            UserDTO invalidUser = new UserDTO();
            invalidUser.setEmail("unknown@gmail.com");
            userService.updateUserProfile(invalidUser);
        });
    }

    @Test
    public void addMovieToUserFavoriteList(){


    }

    @Test
    public void removeMovieToUserFavoriteList(){

    }

    @Test
    public void searchMovieFromUserFavoriteList(){

    }

    //----------------------------------------------------------------

    @Test
    public void searchMovieByTitle(){

    }

    @Test
    public void searchMovieByCast(){

    }

    @Test
    public void searchMovieByCategory(){

    }

    @Test
    public void viewMovieDetails(){

    }

    @Test
    public void addMovieToSystem(){

    }

}