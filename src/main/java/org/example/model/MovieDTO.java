package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.MovieCategory;

import java.util.Arrays;

/**
 * MovieDTO represents a movie in the system.
 * It holds information about the movie's title, director, cast, category,
 * release date, budget, and a unique ID.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    /** Static counter to assign a unique ID to each movie. */
    private static int nextId = 1;

    /** Unique identifier for the movie. */
    private int id;

    /** Title of the movie. */
    private String title;

    /** Director of the movie. */
    private String director;

    /** List of actors/actresses featured in the movie. */
    private String[] cast;

    /** Category or genre of the movie (e.g., Action, Comedy, Drama). */
    private MovieCategory category;

    /** Release date of the movie (format: YYYY-MM-DD). */
    private String releaseDate;

    /** The budget of the movie (e.g., Million/Billion). */
    private String budget;

    /**
     * Provides a brief preview of the movie, displaying the ID, title, genre, and director.
     * Used in movie listings to give users an overview.
     */
    public void preview() {
        System.out.println(this.id + ". " + this.getTitle() + "; Genre: " + this.category + ", directed by " + this.getDirector());
    }

    /**
     * Displays detailed information about the movie, including ID, title, director, cast,
     * category, release date, and budget.
     * Provides a comprehensive view of the movie's information for the user.
     */
    public void display() {
        System.out.println("--------------------------Movie Details---------------------------");
        System.out.println("ID: \t\t\t\t" + this.id);
        System.out.println("Title: \t\t\t\t" + this.title);
        System.out.println("Director: \t\t\t" + this.director);
        System.out.println("Cast: \t\t\t\t" + Arrays.toString(this.cast));
        System.out.println("Category: \t\t\t" + this.category);
        System.out.println("Release Date: \t\t" + this.releaseDate);
        System.out.println("Budget: \t\t\t" + this.budget);
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Generates the next unique movie ID by incrementing the static nextId counter.
     *
     * @return the next unique movie ID.
     */
    public static int getNextID() {
        return nextId++;
    }


    /**
     * Checks if a given cast member is included in the movie's cast list.
     * This method performs a case-insensitive comparison.
     *
     * @param cast the name of the cast member to check.
     * @return true if the cast member is included, false otherwise.
     */
    public boolean isCastIncluded(String cast){
        for(String actor: this.cast){
            if(actor.toLowerCase().contains(cast.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
