package com.svalero.enjoypadel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.presenter.MatchDetailPresenter;

import java.util.Objects;

public class MatchDetailView extends AppCompatActivity implements MatchDetailContract.View {

    private MatchDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        presenter = new MatchDetailPresenter(this);

        Intent intent = getIntent();
        int matchId = intent.getIntExtra("matchId", 0);
        presenter.matchDetail(matchId);
    }

    @Override
    public void loadMatchDetail(Match match) {
        TextView roundTv = findViewById(R.id.round_view);
        TextView playerOneTv = findViewById(R.id.team_one_player_one);
        TextView playerTwoTv = findViewById(R.id.team_one_player_two);
        TextView playerThreeTv = findViewById(R.id.team_two_player_one);
        TextView playerFourTv = findViewById(R.id.team_two_player_two);
        TextView dateTv = findViewById(R.id.date_view);
        TextView durationTv = findViewById(R.id.duration_view);
        TextView scoreTv = findViewById(R.id.score_view);
        TextView locationTv = findViewById(R.id.location_view);

        roundTv.setText(match.getRound());

        Player[] players = match.getPlayers();

        if (players.length == 4) {
            playerOneTv.setText(players[0].toString());
            playerTwoTv.setText(players[1].toString());
            playerThreeTv.setText(players[2].toString());
            playerFourTv.setText(players[3].toString());
        } else {
            if (players.length == 3) {
                playerOneTv.setText(players[0].toString());
                playerTwoTv.setText(players[1].toString());
                playerThreeTv.setText(players[2].toString());
                playerFourTv.setText("Jugador eliminado");
            } else {
                if (players.length == 2) {
                    playerOneTv.setText(players[0].toString());
                    playerTwoTv.setText(players[1].toString());
                    playerThreeTv.setText("Jugador eliminado");
                    playerFourTv.setText("Jugador eliminado");
                } else {
                    if (players.length == 1) {
                        playerOneTv.setText(players[0].toString());
                        playerTwoTv.setText("Jugador eliminado");
                        playerThreeTv.setText("Jugador eliminado");
                        playerFourTv.setText("Jugador eliminado");
                    } else {
                        playerOneTv.setText("Jugador eliminado");
                        playerTwoTv.setText("Jugador eliminado");
                        playerThreeTv.setText("Jugador eliminado");
                        playerFourTv.setText("Jugador eliminado");
                    }
                }
            }
        }

        if (match.getCenter() == null) {
            locationTv.setText("Centro deportivo eliminado");
        } else {
            locationTv.setText(match.getCenter().toString());
        }
        dateTv.setText(match.getDate());
        durationTv.setText(String.valueOf(match.getDuration()));
        scoreTv.setText(match.getMatchScore());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}