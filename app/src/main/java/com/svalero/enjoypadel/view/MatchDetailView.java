package com.svalero.enjoypadel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.presenter.MatchDetailPresenter;

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
        playerOneTv.setText(match.getPlayers(0).toString());
        playerTwoTv.setText(match.getPlayers(1).toString());
        playerThreeTv.setText(match.getPlayers(2).toString());
        playerFourTv.setText(match.getPlayers(3).toString());
        dateTv.setText(match.getDate());
        durationTv.setText(String.valueOf(match.getDuration()));
        scoreTv.setText(match.getMatchScore());
        locationTv.setText(match.getSportCenter().toString());
    }
}