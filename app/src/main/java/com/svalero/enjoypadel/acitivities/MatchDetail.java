package com.svalero.enjoypadel.acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.svalero.enjoypadel.R;

public class MatchDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        Intent intent = getIntent();
        String round = intent.getStringExtra("round");
        String playerOne = intent.getStringExtra("playerOne");
        String playerTwo = intent.getStringExtra("playerTwo");
        String playerThree = intent.getStringExtra("playerThree");
        String playerFour = intent.getStringExtra("playerFour");
        String date = intent.getStringExtra("date");
        String duration = String.valueOf(intent.getIntExtra("duration", 0));
        String score = intent.getStringExtra("matchScore");

        TextView roundTv = findViewById(R.id.round_view);
        TextView playerOneTv = findViewById(R.id.team_one_player_one);
        TextView playerTwoTv = findViewById(R.id.team_one_player_two);
        TextView playerThreeTv = findViewById(R.id.team_two_player_one);
        TextView playerFourTv = findViewById(R.id.team_two_player_two);
        TextView dateTv = findViewById(R.id.date_view);
        TextView durationTv = findViewById(R.id.duration_view);
        TextView scoreTv = findViewById(R.id.score_view);

        roundTv.setText(round);
        playerOneTv.setText(playerOne);
        playerTwoTv.setText(playerTwo);
        playerThreeTv.setText(playerThree);
        playerFourTv.setText(playerFour);
        dateTv.setText(date);
        durationTv.setText(duration);
        scoreTv.setText(score);

    }
}