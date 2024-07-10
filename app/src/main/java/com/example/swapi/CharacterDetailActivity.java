package com.example.swapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView detailsTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        nameTextView = findViewById(R.id.name);
        detailsTextView = findViewById(R.id.details);

        Character character = (Character) getIntent().getSerializableExtra("character");

        if (character != null) {
            nameTextView.setText(character.getName());
            String details = "Height: " + character.getHeight() + "\n" +
                    "Mass: " + character.getMass() + "\n" +
                    "Hair Color: " + character.getHairColor() + "\n" +
                    "Skin Color: " + character.getSkinColor() + "\n" +
                    "Eye Color: " + character.getEyeColor() + "\n" +
                    "Birth Year: " + character.getBirthYear() + "\n" +
                    "Gender: " + character.getGender();
            detailsTextView.setText(details);
        }
    }
}
