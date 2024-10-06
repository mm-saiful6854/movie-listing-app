package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.util.MovieCategory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The MovieService class handles operations related to managing and retrieving movies,
 * including adding movies to the system, searching, and retrieving movie details by ID.
 */
public class MovieService {
    /** A map that stores movies with their ID as the key and MovieDTO as the value. */
    private final Map<Integer, MovieDTO> movies;

    /**
     * Constructor initializes the movie list with some predefined movies.
     */
    public MovieService(){
        movies = new HashMap<>();

        //system default movie list
        MovieDTO movie = new MovieDTO(MovieDTO.getNextID(),"Speak No Evil (2024)","James Watkins",new String[]{"James McAvoy", "Mackenzie Davis", "Scoot McNairy"},MovieCategory.THRILLER,"2024-10-04 (United States)","40M");
        movies.put(movie.getId(), movie);

        movie = new MovieDTO(MovieDTO.getNextID(),"Inception (2010)","Christopher Nolan",new String[]{"Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"},MovieCategory.SCIFI,"Jul 16, 2010 (United States)","10M");
        movies.put(movie.getId(), movie);

        movie = new MovieDTO(MovieDTO.getNextID(),"The Signature (2024)","Gajendra Vitthal Ahire",new String[]{"Mahima Chaudhry", "Herman Dsouza", "Manoj Joshi"},MovieCategory.DRAMA,"2024-10-04 (India)","100M");
        movies.put(movie.getId(), movie);

        movie = new MovieDTO(MovieDTO.getNextID(),"Deadpool & Wolverine (2024)","Shawn Levy",new String[]{"Ryan Reynolds", "Hugh Jackman", "Emma Corrin"},MovieCategory.COMEDY,"Jul 26, 2024 (United States)","100M");
        movies.put(movie.getId(), movie);

        movie = new MovieDTO(MovieDTO.getNextID(),"It Ends with Us (2024)","Justin Baldoni",new String[]{"Blake Lively", "Justin Baldoni", "Jenny Slate"},MovieCategory.ROMANCE,"Aug 09, 2024 (United States)","15M");
        movies.put(movie.getId(), movie);
    }


    /**
     * Adds a new movie to the system.
     *
     * @param movieDTO the MovieDTO object representing the movie to be added.
     * @return true if the movie was successfully added, false otherwise.
     * @throws IllegalArgumentException if a movie with the same ID already exists in the system.
     */
    public boolean addMovieToSystem(@NonNull MovieDTO movieDTO) {
        if(movies.containsKey(movieDTO.getId())) {
            throw new IllegalArgumentException("Movie ID: "+movieDTO.getId()+" already exists");
        }
        movies.put(movieDTO.getId(), movieDTO);
        return true;
    }

    /**
     * Retrieves all movies from the system.
     *
     * @return a collection of all MovieDTO objects sorted by title.
     */
    public Collection<MovieDTO> getAllMovies() {
        List<MovieDTO> allMovies = new ArrayList<>(movies.values());
        allMovies.sort(Comparator.comparing(MovieDTO::getTitle));
        return allMovies;
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the unique ID of the movie.
     * @return the MovieDTO object representing the movie, or null if the movie is not found.
     */
    public MovieDTO getMovieById(int id) {
        return movies.get(id);
    }


    /**
     * Searches movies by the given criteria (title, cast, and category).
     *
     * @param movieCollection the collection of movies to search in.
     * @param title           the title of the movie to search for (or null to ignore).
     * @param cast            the cast member's name to search for (or null to ignore).
     * @param category        the movie category to search for (or null to ignore).
     * @return a list of MovieDTO objects that match the search criteria, sorted by title.
     */
    public List<MovieDTO> searchByCriteria(@NonNull Collection<MovieDTO> movieCollection, String title, String cast, MovieCategory category) {
        return movieCollection.stream()
                .filter(movieDTO -> (title == null || movieDTO.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(movieDTO -> (cast == null || movieDTO.isCastIncluded(cast)))
                .filter(movieDTO -> (category == null || movieDTO.getCategory() == category))
                .sorted(Comparator.comparing(MovieDTO::getTitle))
                .collect(Collectors.toList());
    }


}
