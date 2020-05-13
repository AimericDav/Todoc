package com.cleanup.todoc.database.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocMasterDatabase extends RoomDatabase {

    // 1 - Singleton
    private static volatile TodocMasterDatabase INSTANCE;

    // 2 - On declarer nos DAO
    public abstract ProjectDao mProjectDao();
    public abstract TaskDao mTaskDao();

    // 3 - Creer ou return instance
    public static TodocMasterDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (TodocMasterDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, TodocMasterDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues Projet_Tartampion = new ContentValues();
                Projet_Tartampion.put("id", 1L);
                Projet_Tartampion.put("name", "Projet Tartampion");
                Projet_Tartampion.put("color", 0xFFEADAD1);

                ContentValues Projet_Lucidia = new ContentValues();
                Projet_Lucidia.put("id", 2L);
                Projet_Lucidia.put("name", "Projet Lucidia");
                Projet_Lucidia.put("color", 0xFFB4CDBA);

                ContentValues Projet_Circus = new ContentValues();
                Projet_Circus.put("id", 3L);
                Projet_Circus.put("name", "Projet Circus");
                Projet_Circus.put("color", 0xFFA3CED2);

                db.insert("Project", OnConflictStrategy.IGNORE, Projet_Tartampion);
                db.insert("Project", OnConflictStrategy.IGNORE, Projet_Lucidia);
                db.insert("Project", OnConflictStrategy.IGNORE, Projet_Circus);


            }
        };
    }

}
