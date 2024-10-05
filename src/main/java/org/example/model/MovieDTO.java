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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null and class type

        MovieDTO movieDTO = (MovieDTO) obj;
        return id == movieDTO.id; // Compare IDs
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id); // Generate hash code based on the movie ID
    }

    public static int getNextID() {
        return nextId++;
    }

    public boolean isCastIncluded(String cast){
        for(String actor: this.cast){
            if(actor.equals(cast)){
                return true;
            }
        }
        return false;
    }
}
