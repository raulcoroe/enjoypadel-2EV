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
import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.presenter.CenterListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CenterListView extends AppCompatActivity implements AdapterView.OnItemClickListener, CenterListContract.View {

    private List<Center> centerList;
    private ArrayAdapter<Center> centerAdapter;
    private CenterListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_center);

        presenter = new CenterListPresenter(this);
        initialize();
    }


    public void initialize(){
        centerList = new ArrayList<>();
        ListView lvCenters = findViewById(R.id.center_list);
        centerAdapter = new ArrayAdapter<Center>(this, android.R.layout.simple_list_item_1, centerList);
        lvCenters.setAdapter(centerAdapter);
        registerForContextMenu(lvCenters);

        lvCenters.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllCenters();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_center_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_center) {
            Intent intent = new Intent(this, AddCenterView.class);
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
        Center center = centerList.get(info.position);

        if (item.getItemId() == R.id.delete_center) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.sure)
                        .setPositiveButton(R.string.yes,
                                (dialog, which) -> {
                                    presenter.deleteCenter(center);
                                    centerList.remove(center);
                                    centerAdapter.notifyDataSetChanged();
                                }
                        ).setNegativeButton(R.string.no,
                        (dialog, which) -> dialog.dismiss());
                builder.create().show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Center center = centerList.get(position);

        Intent intentDetail = new Intent(CenterListView.this, CenterDetailView.class);
        intentDetail.putExtra("centerId", center.getId());
        startActivity(intentDetail);
    }

    @Override
    public void listAllCenters(List<Center> centers) {
        centerList.clear();
        centerList.addAll(centers);
        centerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}