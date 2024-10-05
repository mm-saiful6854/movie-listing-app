package org.example;

import org.example.model.MovieDTO;
import org.example.model.UserDTO;
import org.example.service.MovieService;
import org.example.service.UserService;
import org.example.util.MovieCategory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieListingApplicationTest {

    private static UserService userService;
    private static MovieService movieService;
    private static String userEmail;
    //private static MovieDTO movie;
    private static ArrayList<MovieDTO> movieList;

    @BeforeAll
    public static void setup() {
        userEmail = "saiful@gmail.com";
        movieList = new ArrayList<>();

        MovieDTO movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("Speak No Evil (2024)");
        movie.setCategory(MovieCategory.Thriller);
        movie.setDirector("James Watkins");
        movie.setCast(new String[]{"James McAvoy", "Mackenzie Davis", "Scoot McNairy"});
        movieList.add(movie);

        movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("Inception (2010)");
        movie.setCategory(MovieCategory.SciFi);
        movie.setDirector("Christopher Nolan");
        movie.setCast(new String[]{"Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"});
        movieList.add(movie);


        movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("The Signature (2024)");
        movie.setCategory(MovieCategory.Drama);
        movie.setDirector("Gajendra Vitthal Ahire");
        movie.setCast(new String[]{"Mahima Chaudhry", "Herman Dsouza", "Manoj Joshi"});
        movieList.add(movie);


        userService = new UserService();
        movieService = new MovieService();
    }


    @Test
    @Order(1)
    public void userRegistrationByEmail(){
        UserDTO registeredUser = userService.userRegistration(userEmail);
        assertNotNull(registeredUser);
        assertEquals(userEmail, registeredUser.getEmail());

        assertThrows(IllegalArgumentException.class, ()->{
            userService.userRegistration(userEmail);
        });
    }

    @Test
    @Order(2)
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
    @Order(3)
    public void addMovieToUserFavoriteList(){
        for(int i=0; i<movieList.size(); i++){
            if(i%2==1) continue;
            boolean outcome = userService.addMovieToUserFavoriteList(userEmail, movieList.get(i));
            assertTrue(outcome);
        }
    }

    @Test
    @Order(4)
    public void searchMovieFromUserFavoriteList(){


    }

    @Test
    @Order(5)
    public void removeMovieToUserFavoriteList(){
        for(int i=0; i<movieList.size(); i++){
            if(i%2==1) continue;
            boolean outcome = userService.removeMovieToUserFavoriteList(userEmail, movieList.get(i));
            assertTrue(outcome);
        }
    }

    //----------------------------------------------------------------

    @Test
    @Order(1)
    public void addMovieToSystem(){
        for(MovieDTO movieDTO : movieList){
            boolean outcome = movieService.addMovieToSystem(movieDTO);
            assertTrue(outcome);
        }
    }

    @Test
    @Order(2)
    public void viewMovieDetails(){
        MovieDTO movie = movieList.get(0);
        MovieDTO movieDTO = movieService.getMovieById(movie.getId());
        System.out.println("Movie: " + movieDTO.toString());
        assertNotNull(movieDTO);
        assertEquals(movie.getTitle(), movieDTO.getTitle());
        assertEquals(movie.getDirector(), movieDTO.getDirector());
        assertEquals(movie.getCategory(), movieDTO.getCategory());
    }

    @Test
    @Order(3)
    public void searchMovieByTitle(){
        System.out.println("Search by title");
        MovieDTO movie = movieList.get(0);
        List<MovieDTO> searchResult = movieService.searchByCriteria(movieService.getAllMovies(), movie.getTitle(),null,null);
        assertTrue(searchResult.contains(movie));
    }


    @Test
    @Order(3)
    public void searchMovieByCast(){
        System.out.println("Search by cast");
        MovieDTO movie = movieList.get(0);
        List<MovieDTO> searchResult = movieService.searchByCriteria(movieService.getAllMovies(),null,movie.getCast()[0],null);
        assertTrue(searchResult.contains(movie));
    }

    @Test
    @Order(3)
    public void searchMovieByCategory(){
        System.out.println("Search by Category");
        MovieDTO movie = movieList.get(0);
        List<MovieDTO> searchResult = movieService.searchByCriteria(movieService.getAllMovies(),null,null,MovieCategory.Thriller);
        assertTrue(searchResult.contains(movie));
    }

    @Test
    @Order(3)
    public void searchMovieByAllCriteria(){
        System.out.println("Search by All criteria");
        MovieDTO movie = movieList.get(0);
        List<MovieDTO> searchResult = movieService.searchByCriteria(movieService.getAllMovies(),movie.getTitle(),movie.getCast()[0],MovieCategory.Thriller);
        assertTrue(searchResult.contains(movie));
    }



}