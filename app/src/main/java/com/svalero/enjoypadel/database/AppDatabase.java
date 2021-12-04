package com.svalero.enjoypadel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.enjoypadel.dao.PlayerDao;
import com.svalero.enjoypadel.domain.Player;

@Database(entities = {Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
}
