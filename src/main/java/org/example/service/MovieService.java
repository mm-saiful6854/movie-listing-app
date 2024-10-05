package org.example.service;

import lombok.NonNull;
import org.example.model.MovieDTO;
import org.example.util.MovieCategory;

import java.util.*;
import java.util.stream.Collectors;

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
        return null;
    }

    public List<MovieDTO> searchByCriteria(String title, String cast, MovieCategory category) {
        List<MovieDTO> searchResult = new ArrayList<>();
        return searchResult;
    }





}
