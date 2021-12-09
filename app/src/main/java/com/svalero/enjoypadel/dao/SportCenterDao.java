package com.svalero.enjoypadel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.enjoypadel.domain.SportCenter;

import java.util.List;

@Dao
public interface SportCenterDao {

    @Query("SELECT * FROM sportcenter")
    List<SportCenter> getAll();

    @Insert
    void insert(SportCenter center);

    @Delete
    void delete(SportCenter center);
}
