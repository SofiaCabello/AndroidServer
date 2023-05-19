package org.example.json;

import com.google.gson.Gson;

import java.util.List;

public abstract class JSONHandler {
    protected Gson gson;

    public JSONHandler(){
        gson = new Gson();
    }

    public abstract List<String> handleJSON(String json);
}
