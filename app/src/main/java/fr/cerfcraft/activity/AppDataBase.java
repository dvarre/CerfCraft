package fr.cerfcraft.activity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version=3)
public abstract class AppDataBase extends RoomDatabase {
   public abstract NoteDao noteDao();
}
