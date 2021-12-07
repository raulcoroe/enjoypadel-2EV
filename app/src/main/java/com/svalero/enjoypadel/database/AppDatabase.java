package com.svalero.enjoypadel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.enjoypadel.dao.MatchDao;
import com.svalero.enjoypadel.dao.PlayerDao;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

@Database(entities = {Player.class, Match.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract MatchDao matchDao();
}
