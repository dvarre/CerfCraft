package fr.cerfcraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import fr.cerfcraft.R;

@Entity
public class Note{

    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "icone")
    private String icone;

    @ColumnInfo(name = "noteTxt")
    private String noteTxt;

    public Note(int id, String name, String icone, String noteTxt){
        this.id = id;
        this.name = name;
        this.icone = icone;
        this.noteTxt = noteTxt;


    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNoteTxt() {return noteTxt;}
    public void setNoteTxt(String noteTxt) {this.noteTxt = noteTxt;}

    public String getIcone() {return icone;}
    public void setIcone(String icone) {
        this.icone = icone;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }
}
