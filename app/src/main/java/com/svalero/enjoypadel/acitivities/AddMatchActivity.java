package com.svalero.enjoypadel.acitivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class AddMatchActivity extends AppCompatActivity {

    private Spinner spinnerOne;
    private Spinner spinnerTwo;
    private Spinner spinnerThree;
    private Spinner spinnerFour;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private List<Player> players;
    private TextView date;
    private Button btnGoCalendar;
    private EditText round;
    private EditText duration;
    private EditText matchScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        spinnerOne = findViewById(R.id.spinner_one);
        spinnerTwo = findViewById(R.id.spinner_two);
        spinnerThree = findViewById(R.id.spinner_three);
        spinnerFour = findViewById(R.id.spinner_four);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        players = new ArrayList<>();
        players = db.playerDao().getAll();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, players);
        spinnerOne.setAdapter(adp);
        spinnerTwo.setAdapter(adp);
        spinnerThree.setAdapter(adp);
        spinnerFour.setAdapter(adp);

        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerOne = spinnerOne.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerTwo = spinnerTwo.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerThree = spinnerThree.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerFour = spinnerFour.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        round = findViewById(R.id.round);
        duration = findViewById(R.id.duration);
        date = findViewById(R.id.date);
        matchScore = findViewById(R.id.match_score);
        btnGoCalendar = findViewById(R.id.calendar_button);
        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMatchActivity.this, CalendarActivity.class);
                intent.putExtra("round", round.toString());
                startActivity(intent);
            }
        });
        // Parametros que vienen de CalendarActivity
        Intent intentImput = getIntent();
        String dateString = intentImput.getStringExtra("date");
        date.setText(dateString);
    }

    public void createMatch(View view) {
        Intent intent = getIntent();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Si",
                        (dialog, which) -> {
                            if (intent.getIntExtra("modify", 0) == 0) {
                                Match match = new Match();
                                if (round.getText().toString().equals("")) {
                                    Toast.makeText(AddMatchActivity.this, "You must enter the tournament round", Toast.LENGTH_SHORT).show();
                                } else {
                                    match.setRound(round.getText().toString());
                                    match.setDate(date.getText().toString());
                                    match.setMatchScore(matchScore.getText().toString());
                                    if (duration.getText().toString().equals("")) {
                                        match.setDuration(0);
                                    } else {
                                        match.setDuration(Integer.parseInt(duration.getText().toString()));
                                    }
                                    ;
                                    match.setPlayerOne(playerOne);
                                    match.setPlayerTwo(playerTwo);
                                    match.setPlayerThree(playerThree);
                                    match.setPlayerFour(playerFour);

                                    db.matchDao().insert(match);
                                    Toast.makeText(this, "Match " + match.getId() + " added", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                            } else {
                                Match match = db.matchDao().findById(intent.getIntExtra("matchId", 0));
                                match.setRound(round.getText().toString());
                                match.setDate(date.getText().toString());
                                match.setMatchScore(matchScore.getText().toString());
                                if (duration.getText().toString().equals("")) {
                                    match.setDuration(0);
                                } else {
                                    match.setDuration(Integer.parseInt(duration.getText().toString()));
                                }
                                ;
                                match.setPlayerOne(playerOne);
                                match.setPlayerTwo(playerTwo);
                                match.setPlayerThree(playerThree);
                                match.setPlayerFour(playerFour);
                                db.matchDao().update(match);
                                Toast.makeText(this, "Match " + match.getId() + " modified", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                ).setNegativeButton("No",
                (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}