package com.svalero.enjoypadel.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @ColumnInfo
    private String playerOne;
    @ColumnInfo
    private String playerTwo;
    @ColumnInfo
    private String playerThree;
    @ColumnInfo
    private String playerFour;
    @ColumnInfo
    private String sportCenter;


    public Match() {
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

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getPlayerThree() {
        return playerThree;
    }

    public void setPlayerThree(String playerThree) {
        this.playerThree = playerThree;
    }

    public String getPlayerFour() {
        return playerFour;
    }

    public void setPlayerFour(String playerFour) {
        this.playerFour = playerFour;
    }

    public String getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(String sportCenter) {
        this.sportCenter = sportCenter;
    }

    @Override
    public String toString() {
        return "Match: "+ id + " - Round: " + round;
    }
}


