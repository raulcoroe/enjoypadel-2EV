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

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.presenter.AddMatchPresenter;
import com.svalero.enjoypadel.utils.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class AddMatchView extends AppCompatActivity implements AddMatchContract.View {

    private Spinner spinnerOne;
    private Spinner spinnerTwo;
    private Spinner spinnerThree;
    private Spinner spinnerFour;
    private Spinner spinnerLocation;
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;
    private Center location;
    private EditText date;
    private EditText round;
    private EditText duration;
    private EditText matchScore;
    private AddMatchPresenter presenter;
    private List<Player> playerList;
    private List<Center> centerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        presenter = new AddMatchPresenter(this);
        playerList = new ArrayList<>();
        centerList = new ArrayList<>();
        presenter.loadPlayers();
        presenter.loadCenters();
        initilize();
    }

    public void initilize() {
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

    public void loadPlayers(List<Player> players) {
        playerList = players;
        chargeElements();
    }

    public void loadCenters(List<Center> centers) {
        centerList = centers;
        chargeElements();
    }

    public void chargeElements() {
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, playerList);
        ArrayAdapter adpCenter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, centerList);
        spinnerOne.setAdapter(adp);
        spinnerTwo.setAdapter(adp);
        spinnerThree.setAdapter(adp);
        spinnerFour.setAdapter(adp);
        spinnerLocation.setAdapter(adpCenter);


        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerOne = (Player) spinnerOne.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerTwo = (Player) spinnerTwo.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerThree = (Player) spinnerThree.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                playerFour = (Player) spinnerFour.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = (Center) spinnerLocation.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();
        round.setText(intent.getStringExtra("round"));
        matchScore.setText(intent.getStringExtra("matchScore"));
        date.setText(intent.getStringExtra("date"));
        if (intent.getIntExtra("duration", 0) == 0) {
            duration.setText("");
        } else {
            duration.setText(String.valueOf(intent.getIntExtra("duration", 0)));
        }
        if (intent.getIntExtra("modify", 0) == 1) {
            Button modifyBtn = findViewById(R.id.add_match_button);
            modifyBtn.setText(R.string.modify_match);
        }
    }

    public void createMatch(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure)
                .setPositiveButton(R.string.yes,
                        (dialog, which) -> {
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

                                Player[] players = new Player[]{playerOne, playerTwo, playerThree, playerFour};
                                match.setPlayers(players);
                                match.setCenter(location);
                                presenter.addMatch(match);
                                finish();
                            }
                        }
                ).setNegativeButton(R.string.no,
                (dialog, which) -> dialog.dismiss());
        builder.create().
                show();
    }

    public void clickDate(View view) {
        if (view.getId() == R.id.match_date) {
            showDatePickerDialog();
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month + 1) + " / " + year;
            date.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}