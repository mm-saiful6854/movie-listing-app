package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.util.MovieCategory;

import java.util.*;

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

        MovieDTO movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("Speak No Evil (2024)");
        movie.setCategory(MovieCategory.THRILLER);
        movie.setDirector("James Watkins");
        movie.setCast(new String[]{"James McAvoy", "Mackenzie Davis", "Scoot McNairy"});
        movie.setReleaseDate("2024-10-04 (United States)");
        movie.setBudget("40M");
        movies.put(movie.getId(), movie);

        movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("Inception (2010)");
        movie.setCategory(MovieCategory.SCIFI);
        movie.setDirector("Christopher Nolan");
        movie.setCast(new String[]{"Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"});
        movie.setReleaseDate("Jul 16, 2010 (United States)");
        movie.setBudget("10M");
        movies.put(movie.getId(), movie);


        movie = new MovieDTO();
        movie.setId(MovieDTO.getNextID());
        movie.setTitle("The Signature (2024)");
        movie.setCategory(MovieCategory.DRAMA);
        movie.setDirector("Gajendra Vitthal Ahire");
        movie.setCast(new String[]{"Mahima Chaudhry", "Herman Dsouza", "Manoj Joshi"});
        movie.setReleaseDate("2024-10-04 (India)");
        movie.setBudget("100M");
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
        List<MovieDTO> searchResult = new ArrayList<>();
        for(MovieDTO movieDTO : movieCollection) {
            if(title!=null && !movieDTO.getTitle().toLowerCase().contains(title.toLowerCase())) {
                continue;
            }
            else if(cast!=null && !movieDTO.isCastIncluded(cast)) {
                continue;
            }
            else if(category!=null && !Objects.equals(movieDTO.getCategory(),category)) {
                continue;
            }
            searchResult.add(movieDTO);
        }

        searchResult.sort(Comparator.comparing(MovieDTO::getTitle));
        return searchResult;
    }


}
