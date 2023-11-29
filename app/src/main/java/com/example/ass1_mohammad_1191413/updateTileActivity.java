package com.example.ass1_mohammad_1191413;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class updateTileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText type;
    private EditText size;
    private EditText color;
    private EditText description;
    private Button save;
    private ArrayList<Tile> tiles_list;
    private Tile currentt_tile;
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";
    private Uri img_path;
    private TextView txtResults;
    Button but_select_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tile);

        but_select_img = findViewById(R.id.addimage);
        but_select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_img_selector();
            }
        });//intent With external activity
        type = findViewById(R.id.txt_Type);
        size = findViewById(R.id.txt_Size);
        color = findViewById(R.id.txt_Color);
        description = findViewById(R.id.txt_Description);
        save = findViewById(R.id.save);
        //chek set data not null or empt
        currentt_tile = (Tile) getIntent().getSerializableExtra("upload_edit_tile");
        prefs = getSharedPreferences("TilesPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("tiles", null);
        Type type = new TypeToken<ArrayList<Tile>>() {}.getType();
        tiles_list = gson.fromJson(json, type);
        Log.d("halholaym_update",tiles_list.toString());
        Log.d("halholaym_update",json);
        Log.d("halholaym_update",currentt_tile.toString());

        if (currentt_tile != null) {
            add_tile_data();
        }
        //save daata
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTile();
            }
        });
        loadTiles();
    }
    /**ImagePicker*/
    //https://www.youtube.com/watch?v=v6YvUxpgSYQ
    //https://github.com/Dhaval2404/ImagePicker
    //With external activity
    private void open_img_selector() {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
// the method is call activity you launched exits giv the requestCode you start it with
// resultCode it return and any add data .
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img_path = data.getData();
    }
    private void add_tile_data() {
        //Here the data was taken from the user in order to add it
        type.setText(currentt_tile.getType());
        size.setText(currentt_tile.getSize());
        color.setText(currentt_tile.getColor());
        description.setText(currentt_tile.getDescription());
        //Uri-->path image analysis tack path image
        img_path = Uri.parse(currentt_tile.getImagePath());
        Log.d("halholaym_update",currentt_tile.toString());
    }
    //sharedprefs and Gson
    //https://www.youtube.com/watch?v=0IdzL0iuRSQ
    //save data with array list
    //https://www.youtube.com/watch?v=xjOyvwRinK8
    private void saveTile() {
        String S_type = type.getText().toString();
        String S_size = size.getText().toString();
        String S_color = color.getText().toString();
        String S_description = description.getText().toString();
        String S_img_path_str;
        // chek img_path not null
        if (img_path != null) {
            // If img_path not null -->conver path to String
            S_img_path_str = img_path.toString();
            Log.d("halholaym_update",S_img_path_str);
        } else {
            // If img_path is null -->empty
            S_img_path_str = "";
            Log.d("halholaym_update",S_img_path_str);
        }

        //currentt_tile if not equal Update an existing tile
        if (currentt_tile != null) {

            //Update an existing tile
            // Find the tile in the list and update it
            for (int i = 0; i < tiles_list.size(); i++) {
                Tile tile = tiles_list.get(i);
                if (tile.getImagePath().equals(currentt_tile.getImagePath())) { // Assuming you have a getId() method
                    tile.setType(S_type);
                    Log.d("halholaym_update",S_type);
                    tile.setSize(S_size);
                    tile.setColor(S_color);
                    tile.setDescription(S_description);
                    tile.setImagePath(S_img_path_str);
                    Log.d("halholaym_update",S_img_path_str);
                    break;
                }
            }
        } else {
            // Add new tiles
            Tile newTile = new Tile(S_type, S_size, S_color, S_description, S_img_path_str);
            Log.d("halholaym_update",newTile.toString());
            //System.out.println(newTile);
            //Gson gson = new Gson();
            //String tit = gson.toJson(newTile);

            //editor.putString(DATA, tit);
            //editor.commit();

            //txtResults.setText(tit);
            tiles_list.add(newTile);//add on arraylist....save new add
            Log.d("halholaym_update",tiles_list.toString());
        }
        prefs=getApplicationContext().getSharedPreferences("TilesPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        Gson gson=new Gson();
        String json=gson.toJson(tiles_list);
        Log.d("halholaym_update",json);
        editor.putString("tiles",json);
        editor.commit();
        loadTiles();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    //save data with array list
    //https://www.youtube.com/watch?v=xjOyvwRinK8
    private void loadTiles() {
        //'TilesPrefs' to work save name data
        prefs = getSharedPreferences("TilesPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
          /*prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        String tit = gson.toJson(new TypeToken<ArrayList<Tile>>(){}.getType());
        editor.putString(DATA, tit);
        editor.commit();
        String str = prefs.getString(DATA, "");
        if(str.equals("")) {
            tilesList = new ArrayList<>();
        }*/
        //save data Which was done work data sava array list
        String json_data = prefs.getString("tiles", null);
        Log.d("halholaym_update",json_data.toString());
        //System.out.println(json_data);
        //System.out.println(tiles_list);
        Type tock_data=new TypeToken<ArrayList<Tile>>(){}.getType();
        tiles_list = gson.fromJson(json_data,tock_data);
        Log.d("halholaym_update",tock_data.toString());
        Log.d("halholaym_update",tiles_list.toString());
        //System.out.println(tiles_list);
        if (tiles_list == null) {
            //arry_L is Empty
            tiles_list = new ArrayList<>();
            //editor = prefs.edit();
        }
    }
}
