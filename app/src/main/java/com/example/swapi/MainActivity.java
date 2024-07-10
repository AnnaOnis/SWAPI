package com.example.swapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CharactersAdapter adapter;
    private List<Character> characters;
    private static final String BASE_URL = "https://swapi.dev/api/";
    private String nextPageUrl;
    SwapiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RetrofitClient.getClient(BASE_URL).create(SwapiService.class);
        characters = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchCharacters("", BASE_URL + "people/");

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                characters.clear();
                fetchCharacters(query, BASE_URL + "people/");
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                characters.clear();
//                fetchCharacters(newText, BASE_URL + "people/");
//                return true;
                return false;
            }
        });

    }

    private void fetchCharacters(String query, String url) {

        Call<CharacterResponse> call;
        if (query.isEmpty()) {
            call = service.getCharactersFromUrl(url);
        } else {
            call = service.getCharacters(query);
        }

        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(@NonNull Call<CharacterResponse> call, @NonNull Response<CharacterResponse> response) {
                if (response.body() != null) {
                    characters.addAll(response.body().getCharacters());
                    nextPageUrl = response.body().getNext();
                    if (nextPageUrl != null) {
                        fetchCharacters(query, nextPageUrl);
                    } else {
                        adapter = createNewCharactersAdapter(characters);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CharacterResponse> call, @NonNull Throwable t) {}
        });
    }


    private CharactersAdapter createNewCharactersAdapter(List<Character> characters){
        return new CharactersAdapter(characters, character -> {
            Intent intent = new Intent(MainActivity.this, CharacterDetailActivity.class);
            intent.putExtra("character", character);
            startActivity(intent);
        });
    }
}