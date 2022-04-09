package com.svalero.enjoypadel.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.svalero.enjoypadel.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;

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
    private Center sportCenter;

    @Ignore
    private List<Player> players;


    public Match() {
        players = new ArrayList<>();
    }

    public Player getPlayers(int position) {
        return players.get(position);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
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

    public Center getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(Center sportCenter) {
        this.sportCenter = sportCenter;
    }

    @Override
    public String toString() {
        return "Partido: " + id + " - Ronda: " + round;
    }
}


