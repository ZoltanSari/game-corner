package com.sari.gamecorner.util;

import com.sari.gamecorner.model.Genre;
import com.sari.gamecorner.model.Perspective;
import com.sari.gamecorner.model.Platform;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class EnumConverter {

    public Set<Genre> convertJsonArrayToEnumGenre(ApiReader apiReader, JSONArray enumArray, String enumClass) throws UnirestException {
        Set<Genre> genres = new HashSet<>();
        JSONArray genreList = createEnumArray(apiReader, enumArray, enumClass);

        genreList.forEach(genre -> genres.add(Genre.valueOf(genre.toString()
                .replace(" ", "_")
                .replace(" ", "_")
                .replace("(", "")
                .replace(")", "")
                .replace("'", "")
                .replace("/", "_PER_")
                .replace("-", "_")
                .toUpperCase())));

        return genres;
    }

    public Set<Perspective> convertJsonArrayToEnumPerspective(ApiReader apiReader, JSONArray enumArray, String enumClass) throws UnirestException {
        Set<Perspective> perspectives = new HashSet<>();
        JSONArray perspectiveList = createEnumArray(apiReader, enumArray, enumClass);


        perspectiveList.forEach(perspective -> perspectives.add(Perspective.valueOf(perspective.toString()
                .replace(" ", "_")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "_")
                .toUpperCase())));

        return perspectives;

    }

    public Set<Platform> convertJsonArrayToEnumPlatform(ApiReader apiReader, JSONArray enumArray, String enumClass) throws UnirestException {
        Set<Platform> platforms = new HashSet<>();
        JSONArray platformList = createEnumArray(apiReader, enumArray, enumClass);

        platformList.forEach(platform -> platforms.add(Platform.valueOf(platform.toString()
                .replace(" ", "_")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "_")
                .replace("/", "_PER_")
                .toUpperCase())));

        return platforms;

    }

    private JSONArray createEnumArray(ApiReader apiReader, JSONArray enumArray, String enumClass) throws UnirestException {
        JSONArray enumList = new JSONArray();
        for (Object genre : enumArray) {
            enumList.put(apiReader.getEnumFromUrl(enumClass, genre));
        }
        return enumList;
    }
}
