package org.example.model;

import lombok.Data;
import org.example.util.MovieCategory;

import java.util.Arrays;

@Data
public class MovieDTO {
    private static int nextId = 1;
    private int id;
    private String title;
    private String director;
    private String[] cast;
    private MovieCategory category;
    private String releaseDate;
    private String budget;

    public void preview(){
        System.out.println(this.id+". "+this.getTitle()+"; Genre: "+this.category+", directed by "+this.getDirector());
    }

    public void display(){
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

    public static int getNextID() {
        return nextId++;
    }

    public boolean isCastIncluded(String cast){
        for(String actor: this.cast){
            if(actor.toLowerCase().contains(cast.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
