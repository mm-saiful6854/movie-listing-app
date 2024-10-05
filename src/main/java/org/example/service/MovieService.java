package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.util.MovieCategory;

import java.util.*;

public class MovieService {
    private final Map<Integer, MovieDTO> movies;

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

    public boolean addMovieToSystem(@NonNull MovieDTO movieDTO) {
        if(movies.containsKey(movieDTO.getId())) {
            throw new IllegalArgumentException("Movie ID: "+movieDTO.getId()+" already exists");
        }
        movies.put(movieDTO.getId(), movieDTO);
        return true;
    }

    public Collection<MovieDTO> getAllMovies() {
        List<MovieDTO> allMovies = new ArrayList<>(movies.values());
        allMovies.sort(Comparator.comparing(MovieDTO::getTitle));
        return allMovies;
    }

    public MovieDTO getMovieById(int id) {
        return movies.get(id);
    }

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
