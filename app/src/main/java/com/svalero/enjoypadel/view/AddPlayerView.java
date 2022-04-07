package com.svalero.enjoypadel.view;

import static com.svalero.enjoypadel.utils.ImageUtils.byteToBitmap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;
import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.presenter.AddPlayerPresenter;
import com.svalero.enjoypadel.utils.ImageUtils;

public class AddPlayerView extends AppCompatActivity implements AddPlayerContract.View {

    private int SELECT_PICTURE_RESULT = 0;
    private int REQUEST_IMAGE_CAPTURE = 0;
    private int TAKE_PICTURE = 1;
    private AddPlayerPresenter presenter;
    private EditText etName;
    private EditText etSurname;
    private EditText etLevel;
    private CheckBox checkAvailability;
    private ImageView playerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        presenter = new AddPlayerPresenter(this);

        etName = findViewById(R.id.player_name);
        etSurname = findViewById(R.id.player_surname);
        etLevel = findViewById(R.id.player_level);
        checkAvailability = findViewById(R.id.availability_play);
        playerImageView = findViewById(R.id.player_profile);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String level = intent.getStringExtra("level");
        boolean availability = intent.getBooleanExtra("availability", false);
        byte[] image = intent.getByteArrayExtra("image");
        Bitmap bitmapImage = byteToBitmap(image);

        etName.setText(name);
        etSurname.setText(surname);
        etLevel.setText(level);
        checkAvailability.setChecked(availability);

        if (intent.getIntExtra("modify", 0) == 1) {
            playerImageView.setImageBitmap(bitmapImage);
            Button button = findViewById(R.id.add_button);
            button.setText(R.string.modify);
        }
    }


    public void addOrModifyPlayer(View view) {
        Intent intent = getIntent();

        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, R.string.must_player_name, Toast.LENGTH_SHORT).show();
            return;
        }

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String level = etLevel.getText().toString();
        if (level.equals(""))
            level = getString(R.string.not_specified);
        boolean availability = checkAvailability.isChecked();
        byte[] playerImage = ImageUtils.fromImageViewToByteArray(playerImageView);
        String finalLevel = level;

        Player player = new Player(name, surname, finalLevel, availability, playerImage);

        // Comprueba si hay que anadir o modificar
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure)
                .setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            if (intent.getIntExtra("modify", 0) == 0) {
                                presenter.addPlayer(player);
                                finish();
                                Toast.makeText(this, getString(R.string.player_added, name),Toast.LENGTH_SHORT).show();

                            } else {
                                player.setId(intent.getIntExtra("playerId", 0));
                                presenter.modifyPlayer(player);
                                finish();
                                Toast.makeText(this, getString(R.string.player_modified, name), Toast.LENGTH_SHORT).show();
                            }
                        }
                ).setNegativeButton(R.string.no,
                (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }


    public void selectPicture(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_photo)
                .setPositiveButton(R.string.take_photo,
                        (dialog, which) -> {
                            REQUEST_IMAGE_CAPTURE = 1;
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                                    PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                            }
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                            }
                        })
                .setNegativeButton(R.string.choose_photo,
                        (dialog, which) -> {
                            SELECT_PICTURE_RESULT = 1;
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, SELECT_PICTURE_RESULT);
                        }
                );
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == SELECT_PICTURE_RESULT) && (resultCode == RESULT_OK)
                && (data != null)) {
            Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into((ImageView) findViewById(R.id.player_profile));
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Coloca la foto en un ImageView que debería tener en el layout de la Activity
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.player_profile);
            imageView.setImageBitmap(imageBitmap);
        }
    }
}