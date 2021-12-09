package com.svalero.enjoypadel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.enjoypadel.dao.MatchDao;
import com.svalero.enjoypadel.dao.PlayerDao;
import com.svalero.enjoypadel.dao.SportCenterDao;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.domain.SportCenter;

@Database(entities = {Player.class, Match.class, SportCenter.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract MatchDao matchDao();
    public abstract SportCenterDao sportCenterDao();
}
