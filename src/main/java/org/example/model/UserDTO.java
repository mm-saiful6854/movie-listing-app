package org.example.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserDTO {
    private String email;
    private String username;
    private int age;
    private String religion;
    private ArrayList<Integer> favoriteMovieIDs;
}
