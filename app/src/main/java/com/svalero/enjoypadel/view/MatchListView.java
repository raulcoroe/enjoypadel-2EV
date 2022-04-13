package com.svalero.enjoypadel.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.MatchListContract;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.presenter.MatchListPresenter;

import java.util.ArrayList;
import java.util.List;

public class MatchListView extends AppCompatActivity implements AdapterView.OnItemClickListener, MatchListContract.View {

    private List<Match> matchList;
    private ArrayAdapter<Match> matchesAdapter;
    private MatchListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        presenter = new MatchListPresenter(this);
        initialize();
    }

    private void initialize() {
        matchList = new ArrayList<>();
        ListView lvMatches = findViewById(R.id.match_list);
        matchesAdapter = new ArrayAdapter<Match>(this, android.R.layout.simple_list_item_1, matchList);
        lvMatches.setAdapter(matchesAdapter);
        registerForContextMenu(lvMatches);

        lvMatches.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllMatches();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_match_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_match) {
            Intent intent = new Intent(this, AddMatchView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Match match = matchList.get(position);

        Intent intentDetail = new Intent(MatchListView.this, MatchDetailView.class);
        intentDetail.putExtra("matchId", match.getId());
        startActivity(intentDetail);
    }

    @Override
    public void listAllMatches(List<Match> matches) {
        matchList.clear();
        matchList.addAll(matches);
        matchesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}