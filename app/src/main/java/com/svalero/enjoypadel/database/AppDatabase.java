package com.svalero.enjoypadel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.enjoypadel.dao.MatchDao;
import com.svalero.enjoypadel.dao.PlayerDao;
import com.svalero.enjoypadel.dao.CenterDao;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

@Database(entities = {Player.class, Match.class, Center.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract MatchDao matchDao();
    public abstract CenterDao centerDao();
}
