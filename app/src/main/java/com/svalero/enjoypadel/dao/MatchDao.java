package com.svalero.enjoypadel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM match")
    List<Match> getAll();

    @Insert
    void insert(Match match);

    @Delete
    void delete(Match match);

    @Query("SELECT * FROM match WHERE id = :matchId")
    Match findById(int matchId);

    @Update
    void update(Match match);
}
