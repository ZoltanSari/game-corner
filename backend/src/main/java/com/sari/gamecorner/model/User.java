package com.sari.gamecorner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private Date registrationDate;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "users_walkthrough_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> walkthroughGames = new ArrayList<>();

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "users_favourite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> favourite = new ArrayList<>();

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "users_watchlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> gameList = new ArrayList<>();

    @Builder
    public User(String name, String emailAddress, Date registrationDate, String password, List<Game> walkthroughGames, List<Game> favourite, List<Game> gameList) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.registrationDate = registrationDate;
        this.password = password;
        this.walkthroughGames = walkthroughGames;
        this.favourite = favourite;
        this.gameList = gameList;
    }

    public void addGameToWalkthroughGames(Game game) {
        if (!walkthroughGames.contains(game)) {
            this.walkthroughGames.add(game);
            game.addUser(this);
        }
    }

    public void removeGameFromWalkthroughGame(Game game) {
        this.walkthroughGames.remove(game);
    }

    public void addGameToFavouriteGames(Game game) {
        if (!favourite.contains(game)) {
            this.favourite.add(game);
            game.addUser(this);
        }
    }

    public void removeGameFromFavouriteGames(Game game) {
        this.favourite.remove(game);
    }

    public void addGameToGameList(Game game) {
        if (!gameList.contains(game)) {
            this.gameList.add(game);
            game.addUser(this);
        }
    }

    public void removeGameFromGameList(Game game) {
        this.walkthroughGames.remove(game);
    }
}
