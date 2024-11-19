package vn.edu.usth.ilovechildren.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vn.edu.usth.ilovechildren.models.Data;

@Database(entities = {Data.class}, version = 1, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDAO personDao();
}
