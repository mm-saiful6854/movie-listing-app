package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.util.MovieCategory;

import java.util.*;

public class MovieService {
    private final Map<Integer, MovieDTO> movies;

    public MovieService(){
        movies = new HashMap<>();
    }

    public boolean addMovieToSystem(@NonNull MovieDTO movieDTO) {
        if(movies.containsKey(movieDTO.getId())) {
            throw new IllegalArgumentException("Movie ID: "+movieDTO.getId()+" already exists");
        }
        movies.put(movieDTO.getId(), movieDTO);
        return true;
    }

    public MovieDTO getMovieById(int id) {
        return movies.get(id);
    }

    public List<MovieDTO> searchByCriteria(String title, String cast, MovieCategory category) {
        List<MovieDTO> searchResult = new ArrayList<>();
        for(MovieDTO movieDTO : movies.values()) {
            if(title!=null && !Objects.equals(movieDTO.getTitle(), title)) {
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
