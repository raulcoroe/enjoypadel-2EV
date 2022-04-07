package com.svalero.enjoypadel.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.presenter.AddMatchPresenter;
import com.svalero.enjoypadel.utils.DatePickerFragment;

import java.util.List;

public class AddMatchView extends AppCompatActivity {

    private Spinner spinnerOne;
    private Spinner spinnerTwo;
    private Spinner spinnerThree;
    private Spinner spinnerFour;
    private Spinner spinnerLocation;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private String location;
    private EditText date;
    private EditText round;
    private EditText duration;
    private EditText matchScore;
    private AddMatchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        presenter = new AddMatchPresenter(this);
        initilize();
        presenter.chargeSpinners();
    }



    public void createMatch(View view) {
        Intent intent = getIntent();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure)
                .setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            if (intent.getIntExtra("modify", 0) == 0) {
                                Match match = new Match();
                                if (round.getText().toString().equals("")) {
                                    Toast.makeText(AddMatchView.this, R.string.must_tournament_round, Toast.LENGTH_SHORT).show();
                                } else {
                                    match.setRound(round.getText().toString());
                                    match.setDate(date.getText().toString());
                                    match.setMatchScore(matchScore.getText().toString());
                                    if (duration.getText().toString().equals("")) {
                                        match.setDuration(0);
                                    } else {
                                        match.setDuration(Integer.parseInt(duration.getText().toString()));
                                    }
                                    match.setPlayerOne(playerOne);
                                    match.setPlayerTwo(playerTwo);
                                    match.setPlayerThree(playerThree);
                                    match.setPlayerFour(playerFour);
                                    match.setSportCenter(location);

                                    db.matchDao().insert(match);
                                    Toast.makeText(this, R.string.match_added, Toast.LENGTH_SHORT).show();
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
                                match.setPlayerOne(playerOne);
                                match.setPlayerTwo(playerTwo);
                                match.setPlayerThree(playerThree);
                                match.setPlayerFour(playerFour);
                                match.setSportCenter(location);
                                db.matchDao().update(match);
                                Toast.makeText(this, getString(R.string.match_modified), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                ).setNegativeButton(R.string.no,
                (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public void clickDate(View view){
        if (view.getId() == R.id.match_date) {
            showDatePickerDialog();
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            date.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void initilize(){
        spinnerOne = findViewById(R.id.spinner_one);
        spinnerTwo = findViewById(R.id.spinner_two);
        spinnerThree = findViewById(R.id.spinner_three);
        spinnerFour = findViewById(R.id.spinner_four);
        spinnerLocation = findViewById(R.id.spinner_location);
        round = findViewById(R.id.round);
        duration = findViewById(R.id.duration);
        matchScore = findViewById(R.id.match_score);
        date = findViewById(R.id.match_date);
    }

    public void chargeElements(List<Player> players, List<Center> centers){
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, players);
        ArrayAdapter adpCenter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, centers);
        spinnerOne.setAdapter(adp);
        spinnerTwo.setAdapter(adp);
        spinnerThree.setAdapter(adp);
        spinnerFour.setAdapter(adp);
        spinnerLocation.setAdapter(adpCenter);


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

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = spinnerLocation.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();
        round.setText(intent.getStringExtra("round"));
        matchScore.setText(intent.getStringExtra("matchScore"));
        date.setText(intent.getStringExtra("date"));
        if (intent.getIntExtra("duration", 0) == 0){
            duration.setText("");
        } else {
            duration.setText(String.valueOf(intent.getIntExtra("duration", 0)));
        }
        if (intent.getIntExtra("modify", 0) == 1) {
            Button modifyBtn = findViewById(R.id.add_match_button);
            modifyBtn.setText(R.string.modify_match);
        }
    }
}