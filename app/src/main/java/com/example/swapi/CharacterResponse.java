package com.example.swapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResponse {
    @SerializedName("results")
    private List<Character> characters;

    @SerializedName("next")
    private String next;

    public List<Character> getCharacters() {
        return characters;
    }

    public String getNext() {
        return next;
    }
}
