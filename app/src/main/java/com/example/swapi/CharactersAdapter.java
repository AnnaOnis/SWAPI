package com.example.swapi;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {
    private final List<Character> characters;
    private final OnCharacterClickListener listener;


    public CharactersAdapter(List<Character> characters, OnCharacterClickListener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.nameTextView.setText(character.getName());
        holder.idTextView.setText(String.format("#%d", position + 1));
        holder.itemView.setOnClickListener(v -> listener.onCharacterClick(character));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        private final TextView idTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            idTextView = itemView.findViewById(R.id.id_text_view);
        }
    }
}
