package com.svalero.enjoypadel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.utils.ImageUtils;

public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
    }

    public void addPlayer(View view) {
        EditText etName = findViewById(R.id.player_name);
        EditText etSurname = findViewById(R.id.player_surname);
        EditText etLevel = findViewById(R.id.player_level);
        CheckBox checkAvailability = findViewById(R.id.availability_play);
        ImageView playerImageView = findViewById(R.id.image_view);

        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, "You must add the name of a player", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String level = etLevel.getText().toString();
        boolean availability = checkAvailability.isChecked();

        byte[] playerImage = ImageUtils.fromImageViewToByteArray(playerImageView);

        Player player = new Player(name, surname, level, availability, playerImage);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "players").allowMainThreadQueries().build();
        db.playerDao().insert(player);
        Toast.makeText(this, "Player " + name + " added", Toast.LENGTH_SHORT).show();

    }
}