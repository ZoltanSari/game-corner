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

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "first_release_date")
    private Date firstReleaseDate;

    @Column(name = "company")
    private String company;

    @Column
    private String rating;

    @Builder
    public Game(String name, String storyline, String summary, String trailer, String company, String coverUrl, Date firstReleaseDate, String rating) {
        this.name = name;
        this.storyline = storyline;
        this.summary = summary;
        this.trailer = trailer;
        this.coverUrl = coverUrl;
        this.firstReleaseDate = firstReleaseDate;
        this.company = company;
        this.rating = rating;
    }
}
