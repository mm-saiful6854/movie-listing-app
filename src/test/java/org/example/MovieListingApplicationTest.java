package org.example;

import org.example.model.MovieDTO;
import org.example.model.UserDTO;
import org.example.service.MovieService;
import org.example.service.UserService;
import org.example.util.MovieCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieListingApplicationTest {

    private static UserService userService;
    private static MovieService movieService;
    private static String userEmail;
    private static MovieDTO movie;

    @BeforeAll
    public static void setup() {
        userEmail = "saiful@gmail.com";
        movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("Speak No Evil (2024)");
        movie.setCategory(MovieCategory.Thriller);
        movie.setDirector("James Watkins");
        movie.setCast(new String[]{"James McAvoy", "Mackenzie Davis", "Scoot McNairy"});

        userService = new UserService();
        movieService = new MovieService();
    }


    @Test
    public void userRegistrationByEmail(){
        UserDTO registeredUser = userService.userRegistration(userEmail);
        assertNotNull(registeredUser);
        assertEquals(userEmail, registeredUser.getEmail());

        assertThrows(IllegalArgumentException.class, ()->{
            userService.userRegistration(userEmail);
        });
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
    public void addMovieToSystem(){
        boolean outcome= movieService.addMovieToSystem(movie);
        assertTrue(outcome);
    }

    @Test
    public void viewMovieDetails(){
        MovieDTO movieDTO = movieService.getMovieById(movie.getId());
        assertNotNull(movieDTO);
        assertEquals(movie.getTitle(), movieDTO.getTitle());
        assertEquals(movie.getDirector(), movieDTO.getDirector());
        assertEquals(movie.getCategory(), movieDTO.getCategory());
    }

    @Test
    public void searchMovieByTitle(){
        List<MovieDTO> searchResult = movieService.searchByCriteria(movie.getTitle(),null,null);

        assertTrue(searchResult.contains(movie));
    }

    @Test
    public void searchMovieByCast(){

    }

    @Test
    public void searchMovieByCategory(){

    }



}