package com.svalero.enjoypadel.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String round;
    @ColumnInfo
    private int duration;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String matchScore;
    @Ignore
    private Center center;

    @Ignore
    private Player[] players;


    public Match() {
        players = new Player[4];
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    @Override
    public String toString() {
        return "Partido: " + id + " - Ronda: " + round;
    }
}


