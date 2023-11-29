package com.example.ass1_mohammad_1191413;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TileDetailActivity extends AppCompatActivity {

    private ImageView tileImageView; // ImageView
    private TextView type_t_v;
    private TextView size_t_v;
    private TextView color_t_v;
    private TextView decription_t_v;
    private Button but_edit;
    private Button but_remove;
    private Button back;
    private Tile current_tile;
    private ArrayList<Tile> tiles_list;
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_detail);

        type_t_v = findViewById(R.id.type_TextView);
        size_t_v = findViewById(R.id.size_TextView);
        color_t_v = findViewById(R.id.color_TextView);
        decription_t_v = findViewById(R.id.description_TextView);
        tileImageView = findViewById(R.id.img);
        but_edit = findViewById(R.id.edit);
        but_remove = findViewById(R.id.remove);
        back = findViewById(R.id.back);
        //upload data Tile
        current_tile = (Tile) getIntent().getSerializableExtra("TILE_DETAIL");
        prefs = getSharedPreferences("TilesPrefs", MODE_PRIVATE);
        //Log.d("halholaym_rem",current_tile.getImagePath());
        //Log.d("halholaym_rem",tiles_list.toString());
        Gson gson = new Gson();
        String json = prefs.getString("tiles", null);
        Type type = new TypeToken<ArrayList<Tile>>() {}.getType();
        tiles_list = gson.fromJson(json, type);
        //Log.d("halholaym_rem",json);
        //Log.d("halholaym_rem",current_tile.getImagePath());
        //Log.d("halholaym_rem",tiles_list.toString());
        if (tiles_list == null) {
            tiles_list = new ArrayList<>();
        };

        if (current_tile != null) {
            show_data();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_main();
            }
        });
        but_edit.setOnClickListener(v -> {
            //Log.d("halholaym_rem",current_tile.getImagePath());
            //Log.d("halholaym_rem",tiles_list.toString());
            Intent intent = new Intent(TileDetailActivity.this, updateTileActivity.class);
            intent.putExtra("upload_edit_tile", current_tile);
            startActivity(intent);
            //Log.d("halholaym_rem",current_tile.getImagePath());
            //Log.d("halholaym_rem",tiles_list.toString());
        });


        but_remove.setOnClickListener(v -> {
            //prefs=getSharedPreferences("TilesPrefs", 0);
           // prefs.edit().remove(current_tile.getImagePath()).commit();
            //Log.d("halholaym_rem",current_tile.getImagePath());
            //Log.d("halholaym_rem",tiles_list.toString());
            remove_tile();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //Log.d("halholaym_rem",current_tile.getImagePath());
            //Log.d("halholaym_rem",tiles_list.toString());
        });
    }

    //Navigate to the tile main page
    private void open_main() {
        Intent intent = new Intent(this, TilesActivity.class);//Navigate to class TilesActivity
        startActivity(intent);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void show_data() {
        type_t_v.setText(current_tile.getType());
        size_t_v.setText(current_tile.getSize());
        color_t_v.setText(current_tile.getColor());
        decription_t_v.setText(current_tile.getDescription());

        Glide.with(this)
                .load(current_tile.getImagePath())
                .into(tileImageView);
        Log.d("halholaym_rem",current_tile.getImagePath());
    }



    private void remove_tile() {
        //find indx by img
        if (tiles_list != null && current_tile != null) {
            int index = 0;
            for (int i = 0; i < tiles_list.size(); i++) {
                if (tiles_list.get(i).getImagePath().equals(current_tile.getImagePath())) {
                    index = i;//find
                    Log.d("halholaym_rem",String.valueOf(i));
                    Log.d("halholaym_rem",String.valueOf(index));
                    break;
                }
            }

            if (index != -1) {//delete
                Log.d("halholaym_rem",String.valueOf(index));
                tiles_list.remove(index);
                Log.d("halholaym_rem",tiles_list.toString());
                prefs = getSharedPreferences("TilesPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(tiles_list);
                Log.d("halholaym_rem",json);
                editor.putString("tiles", json);
                editor.apply();
            }
        }
    }




}
