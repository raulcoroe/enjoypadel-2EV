package com.svalero.enjoypadel.domain.dto;


public class MatchDTO {
    private String round;
    private int duration;
    private String date;
    private String matchScore;
    private long player1Id;
    private long player2Id;
    private long player3Id;
    private long player4Id;
    private long centerId;

    public MatchDTO() {
    }

    public MatchDTO(String round, int duration, String date, String matchScore, long player1Id, long player2Id, long player3Id, long player4Id, long centerId) {
        this.round = round;
        this.duration = duration;
        this.date = date;
        this.matchScore = matchScore;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.player3Id = player3Id;
        this.player4Id = player4Id;
        this.centerId = centerId;
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

    public long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(long player1Id) {
        this.player1Id = player1Id;
    }

    public long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(long player2Id) {
        this.player2Id = player2Id;
    }

    public long getPlayer3Id() {
        return player3Id;
    }

    public void setPlayer3Id(long player3Id) {
        this.player3Id = player3Id;
    }

    public long getPlayer4Id() {
        return player4Id;
    }

    public void setPlayer4Id(long player4Id) {
        this.player4Id = player4Id;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }
}
