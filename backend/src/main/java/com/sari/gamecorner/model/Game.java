package com.sari.gamecorner.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Lob
    @Column(name = "storyline")
    private String storyline;

    @Lob
    @Column
    private String summary;

    @Column
    private String trailer;

    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    @ToString.Exclude
    private Set<Genre> genres;

    @Column(name = "game_modes")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Perspective.class)
    @ToString.Exclude
    private Set<Perspective> gameModes;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "first_release_date")
    private Date firstReleaseDate;

    @Column(name = "company")
    private String company;

    @Column
    private String rating;

    @Builder
    public Game(String name, String storyline, String summary, String trailer, Set<Genre> genres, Set<Perspective> gameModes, String company, String coverUrl, Date firstReleaseDate, String rating) {
        this.name = name;
        this.storyline = storyline;
        this.summary = summary;
        this.trailer = trailer;
        this.genres = genres;
        this.coverUrl = coverUrl;
        this.firstReleaseDate = firstReleaseDate;
        this.company = company;
        this.rating = rating;
    }
}
