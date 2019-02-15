package com.sari.gamecorner.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class RemoteUrlReader {

    public JSONObject readFromUrl(String url, String apiKey) throws UnirestException {
        HttpResponse<JsonNode> response =  Unirest.get(url)
                .header("user-key", apiKey)
                .header("Accept", "application/json")
                .asJson();

        return (JSONObject) response.getBody().getArray().get(0);
    }
}
