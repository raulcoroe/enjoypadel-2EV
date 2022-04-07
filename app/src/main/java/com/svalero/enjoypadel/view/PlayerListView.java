package com.svalero.enjoypadel.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.adapter.PlayerAdapter;
import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.presenter.PlayerListPresenter;

import java.util.ArrayList;
import java.util.List;

public class PlayerListView extends AppCompatActivity implements PlayerListContract.View {

    private List<Player> playerList;
    private PlayerAdapter playerAdapter;
    private PlayerListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        presenter = new PlayerListPresenter(this);
        initialize();
    }

    public void initialize(){
        playerList = new ArrayList<>();
        ListView lvPlayers = findViewById(R.id.player_list);
        playerAdapter = new PlayerAdapter(this, playerList);
        lvPlayers.setAdapter(playerAdapter);
        registerForContextMenu(lvPlayers);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllPlayers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_player_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_player) {
            Intent intent = new Intent(this, AddPlayerView.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this, PreferenceView.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Preferencia sobre restringir modificar y eliminar
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean removeDelete = preferencias.getBoolean("restrict", false);

        if (!removeDelete)
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Player player = playerList.get(info.position);

        switch (item.getItemId()) {
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.sure)
                        .setPositiveButton(R.string.yes,
                                (dialog, which) -> {
                                    presenter.deletePlayer(player);
                                    playerList.remove(player);
                                    playerAdapter.notifyDataSetChanged();
                                }
                        ).setNegativeButton(R.string.no,
                        (dialog, which) -> dialog.dismiss());
                builder.create().show();
                return true;


            case R.id.action_edit:
                Intent intent = new Intent(this, AddPlayerView.class);
                intent.putExtra("name", player.getName());
                intent.putExtra("surname", player.getSurname());
                intent.putExtra("level", player.getLevel());
                intent.putExtra("availability", player.isAvailability());
                intent.putExtra("image", player.getImage());
                intent.putExtra("modify", 1);
                intent.putExtra("playerId", player.getId());
                startActivity(intent);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void listAllPlayers(List<Player> players) {
        playerList.clear();
        playerList.addAll(players);
        playerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }
}