package com.example.swapi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Character  implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private String height;

    @SerializedName("mass")
    private String mass;

    @SerializedName("hair_color")
    private String hairColor;

    @SerializedName("skin_color")
    private String skinColor;

    @SerializedName("eye_color")
    private String eyeColor;

    @SerializedName("birth_year")
    private String birthYear;

    @SerializedName("gender")
    private String gender;

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }
}