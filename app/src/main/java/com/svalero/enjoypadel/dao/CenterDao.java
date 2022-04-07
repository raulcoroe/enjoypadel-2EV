package com.svalero.enjoypadel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.svalero.enjoypadel.domain.Center;

import java.util.List;

@Dao
public interface CenterDao {

    @Query("SELECT * FROM Center")
    List<Center> getAll();

    @Insert
    void insert(Center center);

    @Delete
    void delete(Center center);

    @Query("SELECT * FROM center WHERE id = :id")
    Center findById(int id);
}
