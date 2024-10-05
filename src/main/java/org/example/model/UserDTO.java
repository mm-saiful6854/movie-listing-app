package org.example.model;

import lombok.Data;

import java.util.ArrayList;

/**
 * UserDTO represents a user in the movie listing application.
 * It holds information such as the user's email, username, age, religion,
 * and a list of their favorite movies.
 */
@Data
public class UserDTO {

    /** The email address of the user. Used as a unique identifier for the user. */
    private String email;

    /** The username chosen by the user. If not provided, it will display as "N/A". */
    private String username;

    /** The age of the user. If not provided or invalid, it will display as "N/A". */
    private int age;

    /** The religion of the user. If not provided, it will display as "N/A". */
    private String religion;

    /** The list of movies that the user has marked as their favorite. */
    private ArrayList<MovieDTO> favoriteMovieList = new ArrayList<>();

    /**
     * Displays the user's profile information, including email, username, age, religion,
     * and the count of their favorite movies.
     * If certain fields are not set, they are displayed as "N/A".
     */
    public void display() {
        System.out.println("----------------------------Profile---------------------------------");
        System.out.println("Email: \t\t\t\t" + email);
        System.out.println("Username: \t\t\t" + (username == null ? "N/A" : username));
        System.out.println("Age: \t\t\t\t" + (age <= 0 ? "N/A" : age));
        System.out.println("Religion: \t\t\t" + (religion == null ? "N/A" : religion));
        System.out.println("Favorite Movie(s):  " + favoriteMovieList.size());
        System.out.println("--------------------------------------------------------------------");
    }
}
