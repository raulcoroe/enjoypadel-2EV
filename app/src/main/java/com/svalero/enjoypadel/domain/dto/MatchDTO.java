package com.svalero.enjoypadel.domain.dto;


public class MatchDTO {
    private String round;
    private int duration;
    private String date;
    private String matchScore;
    private long player1;
    private long player2;
    private long player3;
    private long player4;
    private long center;

    public MatchDTO() {
    }

    public MatchDTO(String round, int duration, String date, String matchScore, long player1Id, long player2Id, long player3Id, long player4Id, long centerId) {
        this.round = round;
        this.duration = duration;
        this.date = date;
        this.matchScore = matchScore;
        this.player1 = player1Id;
        this.player2 = player2Id;
        this.player3 = player3Id;
        this.player4 = player4Id;
        this.center = centerId;
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

    public long getPlayer1() {
        return player1;
    }

    public void setPlayer1(long player1) {
        this.player1 = player1;
    }

    public long getPlayer2() {
        return player2;
    }

    public void setPlayer2(long player2) {
        this.player2 = player2;
    }

    public long getPlayer3() {
        return player3;
    }

    public void setPlayer3(long player3) {
        this.player3 = player3;
    }

    public long getPlayer4() {
        return player4;
    }

    public void setPlayer4(long player4) {
        this.player4 = player4;
    }

    public long getCenter() {
        return center;
    }

    public void setCenter(long center) {
        this.center = center;
    }
}
