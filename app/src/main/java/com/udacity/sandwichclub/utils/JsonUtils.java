package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject rootJson = new JSONObject(json);
        JSONObject name=rootJson.getJSONObject("name");
         String mainName = name.getString("mainName");
         JSONArray alsoKnownAs_jsonArray =  name.getJSONArray("alsoKnownAs");
         ArrayList<String> alsoKnownAs=new ArrayList<String>();
         for(int i =0;i<alsoKnownAs_jsonArray.length();i++){
             alsoKnownAs.add(alsoKnownAs_jsonArray.getString(i));
         }

         String placeOfOrigin=rootJson.getString("placeOfOrigin");
         String description=rootJson.getString("description");
         String image=rootJson.getString("image");

        JSONArray ingredients_jsonArray =  rootJson.getJSONArray("ingredients");
        ArrayList<String> ingredients = new ArrayList<String>();

        for(int i =0;i<ingredients_jsonArray.length();i++){
            ingredients.add(ingredients_jsonArray.getString(i));
        }

        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
