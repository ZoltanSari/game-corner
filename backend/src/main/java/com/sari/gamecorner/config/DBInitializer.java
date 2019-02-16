package com.sari.gamecorner.config;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.sari.gamecorner.model.Game;
import com.sari.gamecorner.service.GameService;
import com.sari.gamecorner.util.ApiReader;
import com.sari.gamecorner.util.DetailsChecker;
import com.sari.gamecorner.util.EnumConverter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;


@Component
public class DBInitializer {

    @Autowired
    private EnumConverter enumConverter;

    @Autowired
    private ApiReader apiReader;

    @Autowired
    private DetailsChecker detailsChecker;

    @Autowired
    private GameService gameService;

    private final int TOTAL_GAME = 150;


//    @PostConstruct
    public void getGames() throws UnirestException {
        Game game;

        for (int i = 1; i < TOTAL_GAME; i++) {
            JSONObject gameFromUrl = apiReader.getGameFromUrl(i);
            JSONObject companyFromUrl = apiReader.getCompaniesFromUrl(detailsChecker.checkIsGameHasThisDetail(gameFromUrl, "involved_companies"));
            JSONObject trailerFromUrl = apiReader.getTrailerFromUrl(detailsChecker.checkIsGameHasThisDetail(gameFromUrl, "videos"));

            game = Game.builder().name(detailsChecker.checkDetailIsEmpty(gameFromUrl, "name"))
                    .summary(detailsChecker.checkDetailIsEmpty(gameFromUrl, "summary"))
                    .storyline(detailsChecker.checkDetailIsEmpty(gameFromUrl, "storyline"))
                    .trailer(detailsChecker.checkDetailIsEmpty(trailerFromUrl, "video_id"))
                    .company(detailsChecker.checkDetailIsEmpty(companyFromUrl, "name"))
                    .genres(enumConverter.convertJsonArrayToEnumGenre
                            (apiReader, gameFromUrl.getJSONArray("genres"), "genres"))
                    .gameModes(enumConverter.convertJsonArrayToEnumPerspective
                            (apiReader, gameFromUrl.getJSONArray("player_perspectives"), "player_perspectives"))
                    .platforms(enumConverter.convertJsonArrayToEnumPlatform
                            (apiReader, gameFromUrl.getJSONArray("platforms"), "platforms"))
                    .coverUrl(apiReader.getCoverFromUrl(gameFromUrl.get("cover")))
                    .firstReleaseDate(convertTimestampToDate
                            (gameFromUrl.get("first_release_date").toString()))
                    .rating(detailsChecker.checkDetailIsEmpty(gameFromUrl, "aggregated_rating")).build();

            gameService.addGame(game);
        }
    }

    private Date convertTimestampToDate(String str_date) {
        return new java.util.Date(Long.parseLong(str_date));
    }

}
