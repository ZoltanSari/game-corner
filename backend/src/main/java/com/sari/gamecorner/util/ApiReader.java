package com.sari.gamecorner.util;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiReader {

    @Autowired
    private RemoteUrlReader urlReader;

    @Value("${api.key}")
    private String API_KEY;


    public JSONObject getGameFromUrl(Object id) throws UnirestException {
        String gameUrl = "https://api-v3.igdb.com/games/" + id + "?fields=*";
        return urlReader.readFromUrl(gameUrl, API_KEY);
    }

    public JSONObject getCompaniesFromUrl(Object companyId) throws UnirestException {
        String companyUrl = "https://api-v3.igdb.com/companies/" + companyId + "?fields=*";
        try {
            return urlReader.readFromUrl(companyUrl, API_KEY);
        } catch (Exception e) {
            return null;
        }
    }

    public Object getEnumFromUrl(String name, Object id) throws UnirestException {
        String companyUrl = "https://api-v3.igdb.com/" + name + "/" + id + "?fields=name";
        JSONObject genre = urlReader.readFromUrl(companyUrl, API_KEY);

        return genre.get("name");
    }

    public JSONObject getTrailerFromUrl(Object id) {
        String trailerUrl = "https://api-v3.igdb.com/game_videos/" + id + "?fields=video_id";
        try {
            return urlReader.readFromUrl(trailerUrl, API_KEY);
        } catch (Exception e) {
            return null;
        }
    }

    public String getCoverFromUrl(Object id) throws UnirestException {
        String companyUrl = "https://api-v3.igdb.com/covers/" + id + "?fields=url";

        JSONObject cover = urlReader.readFromUrl(companyUrl, API_KEY);

        return cover.get("url").toString();
    }
}