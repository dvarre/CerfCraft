package fr.cerfcraft.adapter;


import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import fr.cerfcraft.CraftActivity;
import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.R;
import fr.cerfcraft.WritingNotesActivity;
import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.ShowingNotesActivity;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    Context context;
    List<Note> notes;

    public NoteAdapter(List<Note> notes, Context context) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.note, parent, false);

        return new NoteViewHolder(adapterLayout);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        String txt = note.getName()+"\n"+note.getNoteTxt();
        holder.noteButton.setText(txt);
        //String uri = "@drawable/"+ note.getIcone();
        //int biomeDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
        //Log.d(TAG, String.valueOf(biomeDrawableId));
        //Drawable biomeDrawable = context.getResources().getDrawable(biomeDrawableId);
        //biomeDrawable.setBounds(0,0,390,300);
        //holder.noteButton.setCompoundDrawables(biomeDrawable,null,null,null);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setListNote(List<Note> listNote){this.notes = listNote;}

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        Button noteButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteButton = itemView.findViewById(R.id.NoteButton);

        }
    }



}
