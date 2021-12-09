package com.svalero.enjoypadel.acitivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.SportCenter;

import java.util.ArrayList;
import java.util.List;

public class SportCenterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<SportCenter> centers;
    private ArrayAdapter<SportCenter> centerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_center);

        centers = new ArrayList<>();
        ListView lvCenters = findViewById(R.id.center_list);
        centerAdapter = new ArrayAdapter<SportCenter>(this, android.R.layout.simple_list_item_1, centers);
        lvCenters.setAdapter(centerAdapter);
        registerForContextMenu(lvCenters);

        lvCenters.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        centers.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        centers.addAll(db.sportCenterDao().getAll());
        centerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_center_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_center) {
            Intent intent = new Intent(this, AddCenterActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.center_contextual_menu, menu);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        SportCenter center = centers.get(info.position);

        if (item.getItemId() == R.id.delete_center) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Si",
                                (dialog, which) -> {
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                            AppDatabase.class, "tournament").allowMainThreadQueries()
                                            .fallbackToDestructiveMigration().build();
                                    db.sportCenterDao().delete(center);
                                    centers.remove(center);
                                    centerAdapter.notifyDataSetChanged();
                                }
                        ).setNegativeButton("No",
                        (dialog, which) -> dialog.dismiss());
                builder.create().show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SportCenter center = centers.get(position);

        Intent intentDetail = new Intent(SportCenterActivity.this, CenterDetailActivity.class);
        intentDetail.putExtra("name", center.getName());
        intentDetail.putExtra("latitude", center.getLatitude());
        intentDetail.putExtra("longitude", center.getLongitude());
        startActivity(intentDetail);
    }
}