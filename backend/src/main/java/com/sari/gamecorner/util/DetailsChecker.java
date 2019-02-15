package com.sari.gamecorner.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DetailsChecker {

    public String checkDetailIsEmpty(JSONObject gameFromUrl, String detail) {
        try {
            return gameFromUrl.get(detail).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Object checkIsGameHasThisDetail(JSONObject gameFromUrl, String detail) {
        try {
            return gameFromUrl.getJSONArray(detail).get(0);
        } catch (Exception e) {
            return null;
        }
    }
}