package com.example.sisterslabapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;
    //when watchlist deleted user will not be deleted
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,mappedBy = "user")
    private List<WatchList> watchLists;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Rating> ratings;
}
