package org.example;

import org.example.model.MovieDTO;
import org.example.model.UserDTO;
import org.example.service.MovieService;
import org.example.service.UserService;
import org.example.util.MovieCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class MovieListingApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static MovieService movieService = new MovieService();
    private static UserService userService = new UserService();
    private static UserDTO loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Movie Listing App");
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> {
                    isRunning = false;
                    System.out.println("Exiting the application...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your email address to login: ");
        String email = scanner.nextLine();
        loggedInUser = userService.userRegistration(email);
        if (loggedInUser != null) {
            System.out.println("Login successful!");
            showUserMenu();
        } else {
            System.out.println("Login failed. Invalid email.");
        }
    }


    private static void showUserMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Add Movie to System");
            System.out.println("3. View System Movie List");
            System.out.println("4. View Favorite Movies");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewProfile();
                case 2 -> addMovieToSystem();
                case 3 -> viewSystemMovieList();
                case 4 -> viewFavoriteMovies();
                case 5 -> {
                    isRunning = false;
                    System.out.println("Logging out...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMovieToSystem() {
        System.out.println("Enter movie title: ");
        String title = scanner.nextLine();

        System.out.println("Enter movie director: ");
        String director = scanner.nextLine();

        System.out.println("Enter movie cast (comma separated): ");
        String[] cast = scanner.nextLine().split(",");

        System.out.println("Enter movie category (e.g., Scifi, Action, Thriller, Comedy): ");
        String categoryInput = scanner.nextLine();
        MovieCategory category = MovieCategory.valueOf(categoryInput.toUpperCase());

        System.out.println("Enter movie release date (format: YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();

        System.out.println("Enter movie budget (Million/Billion): ");
        String budget = scanner.nextLine();

        // Create a new movie object
        MovieDTO movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setCast(cast);
        movie.setCategory(category);
        movie.setReleaseDate(releaseDate);
        movie.setBudget(budget);

        // Add the movie to the system
        try{
            boolean added = movieService.addMovieToSystem(movie);
            if (added) {
                System.out.println("Movie successfully added to the system.");
            } else {
                System.out.println("Failed to add movie. It may already exist.");
            }
        }catch (Exception error){
            System.out.println("Failed to add movie. "+error.getMessage());
        }

    }


    private static void viewProfile() {
        loggedInUser.display();
        editProfile();
        loggedInUser.display();
    }

    private static void editProfile() {
        System.out.print("Enter your new username or press Enter to skip: ");
        String option = scanner.nextLine();
        if(option.trim().isEmpty()) {
            System.out.println("Username is unchanged: " + (loggedInUser.getUsername()==null?"N/A":loggedInUser.getUsername()));
        } else {
            loggedInUser.setUsername(option);
            System.out.println("Username updated to: " + loggedInUser.getUsername());
        }

        System.out.print("Enter your age or press Enter to skip: ");
        option = scanner.nextLine();
        if(option.trim().isEmpty()) {
            System.out.println("Age is unchanged: " + (loggedInUser.getAge()<=0?"N/A":loggedInUser.getAge()));
        } else {
            try {
                loggedInUser.setAge(Integer.parseInt(option));
                System.out.println("Age updated to: " + loggedInUser.getAge());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Please enter a valid number.");
            }
        }

        System.out.print("Enter your religion or press Enter to skip: ");
        option = scanner.nextLine();
        if(option.trim().isEmpty()) {
            System.out.println("Religion is unchanged: " + (loggedInUser.getReligion()==null?"N/A":loggedInUser.getReligion()));
        } else {
            loggedInUser.setReligion(option);
            System.out.println("Religion updated to: " + loggedInUser.getReligion());
        }

        System.out.println("Profile update complete.");
    }


    private static void viewSystemMovieList() {
        Collection<MovieDTO> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }

        System.out.println("Do you want to search for movies by title, cast, or category? (y/n): ");
        String searchResponse = scanner.nextLine();

        List<MovieDTO> filteredMovies;
        if (searchResponse.equalsIgnoreCase("y")) {
            System.out.print("Enter movie title (or press enter to skip): ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter cast member name (or press enter to skip): ");
            String cast = scanner.nextLine().trim();

            System.out.print("Enter movie category (or press enter to skip): ");
            String categoryInput = scanner.nextLine().trim();
            MovieCategory category = null;
            if (!categoryInput.isEmpty()) {
                category = MovieCategory.valueOf(categoryInput.toUpperCase());
            }

            filteredMovies = movieService.searchByCriteria(movies, title.isEmpty() ? null : title,
                    cast.isEmpty() ? null : cast, category);
        } else {
            filteredMovies = new ArrayList<>(movies);
        }

        if (filteredMovies.isEmpty()) {
            System.out.println("No movies matched your search criteria.");
        } else {
            System.out.println("Movies in the system:");
            for (MovieDTO movie : filteredMovies) {
                movie.preview();
            }

            movieDetailPrompt(true);
        }
    }




    private static void viewFavoriteMovies() {
        System.out.println("Your Favorite Movies:");

        List<MovieDTO> favoriteMovies = userService.getUserFavoriteList(loggedInUser.getEmail());
        if (favoriteMovies.isEmpty()) {
            System.out.println("You have no favorite movies.");
            return;
        }

        System.out.println("Do you want to search your favorite movies by title, cast, or category? (y/n): ");
        String searchResponse = scanner.nextLine();

        List<MovieDTO> filteredMovies;
        if (searchResponse.equalsIgnoreCase("y")) {
            System.out.print("Enter movie title (or press enter to skip): ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter cast member name (or press enter to skip): ");
            String cast = scanner.nextLine().trim();

            System.out.print("Enter movie category (or press enter to skip): ");
            String categoryInput = scanner.nextLine().trim();
            MovieCategory category = null;
            if (!categoryInput.isEmpty()) {
                category = MovieCategory.valueOf(categoryInput.toUpperCase());
            }

            filteredMovies = movieService.searchByCriteria(favoriteMovies, title.isEmpty() ? null : title, cast.isEmpty() ? null : cast, category);
        } else {
            filteredMovies = new ArrayList<>(favoriteMovies);
        }

        if (filteredMovies.isEmpty()) {
            System.out.println("No favorite movies matched your search criteria.");
        } else {
            System.out.println("Your favorite movies:");
            for (MovieDTO movie : filteredMovies) {
                movie.preview();
            }
            movieDetailPrompt(false);
        }
    }


    private static void movieDetailPrompt(boolean promptForAddingFavorite) {
        System.out.print("Do you want to see more details about a movie? (y/n): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            System.out.print("Enter movie ID to view details: ");
            int movieId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            MovieDTO movie = movieService.getMovieById(movieId);
            if (movie != null) {
                movie.display();
                if(promptForAddingFavorite){
                    System.out.print("Do you want to add this movie to your favorites? (y/n): ");
                    String addFavorite = scanner.nextLine();
                    if (addFavorite.equalsIgnoreCase("y")) {
                        userService.addMovieToUserFavoriteList(loggedInUser.getEmail(), movie);
                        System.out.println("Movie added to favorites.");
                    }
                }

            } else {
                System.out.println("Movie not found.");
            }
        }
    }

}
