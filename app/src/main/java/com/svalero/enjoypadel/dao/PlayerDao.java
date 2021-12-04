package com.svalero.enjoypadel.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.svalero.enjoypadel.domain.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Insert
    void insert(Player player);
}
