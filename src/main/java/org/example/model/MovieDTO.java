package org.example.model;

import lombok.Data;
import org.example.util.MovieCategory;

@Data
public class MovieDTO {
    private static int nextId = 1;
    private int id;
    private String title;
    private String director;
    private String[] cast;
    private MovieCategory category;

    public static int getNextID() {
        return nextId++;
    }

    public boolean isCastIncluded(String cast){
        return false;
    }
}
