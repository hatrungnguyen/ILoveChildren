package vn.edu.usth.ilovechildren.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.edu.usth.ilovechildren.models.Data;

@Dao
public interface PersonDAO {  // Rename to match standard naming conventions

    @Insert
    void addPerson(Data data);

    @Update
    void updatePerson(Data data);

    @Delete
    void deletePerson(Data data);

    @Query("SELECT * FROM Data")
    List<Data> getPersonDate();

    @Query("SELECT * FROM Data WHERE date = :date")
    Data getDataByDate(String date);
}
