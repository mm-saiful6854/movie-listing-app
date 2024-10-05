package org.example.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserDTO {
    private String email;
    private String username;
    private int age;
    private String religion;
    private ArrayList<MovieDTO> favoriteMovieList = new ArrayList<>();

    public void display(){
        System.out.println("----------------------------Profile---------------------------------");
        System.out.println("Email: \t\t\t" + email);
        System.out.println("Username: \t\t" + (username==null?"N/A":username));
        System.out.println("Age: \t\t\t" + (age<=0?"N/A":age));
        System.out.println("Religion: \t\t" + (religion==null?"N/A":religion));
        System.out.println("--------------------------------------------------------------------");
    }
}
