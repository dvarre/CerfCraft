package fr.cerfcraft.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.cerfcraft.R;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.ShowingNotesActivity;

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

        return new NoteViewHolder(adapterLayout, notes.get(0), context);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        String txt = note.getName();
        holder.noteButton.setText(txt);


        holder.noteButton.setOnClickListener((view) -> {

            Intent intent = new Intent(context, ShowingNotesActivity.class);
            String id = (new Integer(note.getId())).toString();
            intent.putExtra("Id", id);
            intent.putExtra("Name", note.getName());
            intent.putExtra("Icone", note.getIcone());
            intent.putExtra("Description", note.getNoteTxt());
            context.startActivity(intent);

        });


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
        Note note;

        RelativeLayout parentLayout;


        public NoteViewHolder(@NonNull View itemView, Note note, Context context) {
            super(itemView);
            noteButton = itemView.findViewById(R.id.NoteButton);
            this.note = note;
            //new StartShowingNote(itemView,noteButton,note,context);



        }
    }

    public static class StartShowingNote extends AppCompatActivity{


        public StartShowingNote(@NonNull View itemView, Button noteButton, Note note, Context context){
            noteButton.setOnClickListener(v -> {
                Log.d("context",context.toString());
                //ShowingNotesActivity showingNotesActivity = new ShowingNotesActivity(note);
                Intent intent = new Intent(context, ShowingNotesActivity.class);
                context.startActivity(intent);
            });

        }

    }



}
