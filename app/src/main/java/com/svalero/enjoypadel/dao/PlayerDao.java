package com.svalero.enjoypadel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.enjoypadel.domain.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);

    @Query("SELECT * FROM player WHERE id = :id")
    Player findById(int id);

    @Query("SELECT * FROM player WHERE availability = 1")
    List<Player> getAvailablePlayers();
}
