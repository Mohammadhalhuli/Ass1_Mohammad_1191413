package com.example.ass1_mohammad_1191413;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TilesActivity extends AppCompatActivity {

    private TileAdapter call_data_tit;
    private SharedPreferences prefs;
    private ListView listView;

    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";
    private List<Tile> tiles_data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);//Introduction to the interfaces page
        //Learn about the tools used

        // Find the ListView widget by its ID in the layout
        listView = findViewById(R.id.list_view_tiles);

    // initial List the data in the tiles
        tiles_data_list = new ArrayList<>();
        Log.d("halholaym",tiles_data_list.toString());
/*
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
*/
// Create an instance of TileAdapter which is a custom adapter for the ListView
// This adapter will use `tiles_data_list` for the data to display
        call_data_tit = new TileAdapter(this, tiles_data_list);
        Log.d("halholaym",call_data_tit.toString());
// Set the TileAdapter instance as the adapter for the ListView
        listView.setAdapter(call_data_tit);

// Call the method `load_data_tit` to fetch and save the data into `tiles_data_list`
        load_data_tit();

// Set an item click listener for the ListView
// When an item is clicked, this code will navigate to TileDetailActivity
// and likely pass some data about the selected tile
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Tile selected_tile = tiles_data_list.get(position);
            Intent intent = new Intent(TilesActivity.this, TileDetailActivity.class);
            Log.d("halholaym",tiles_data_list.toString());
            // herre  would put extra data the intent to pass to the tile detailActivity
            startActivity(intent); // Start the detail activity
        //'TILE_DETAIL'une name use key show data
            Log.d("halholaym",tiles_data_list.toString());
            intent.putExtra("TILE_DETAIL", selected_tile);
            startActivity(intent);
        });
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }
    private void load_data_tit() {
        prefs = getSharedPreferences("TilesPrefs", MODE_PRIVATE);
        //prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //editor = prefs.edit();
        Gson gson = new Gson();
        //String tit = gson.toJson(new TypeToken<ArrayList<Tile>>(){}.getType());
        String json_data = prefs.getString("tiles", null);//get tile
        //System.out.println(json_data);
        Type type = new TypeToken<ArrayList<Tile>>() {}.getType();//get tile
        ArrayList<Tile> save_tile = gson.fromJson(json_data, type);
        Log.d("halholaym",json_data.toString());
        Log.d("halholaym",save_tile.toString());
        //System.out.println(save_tile);
        //editor.putString(DATA, tit);
        // editor.commit();
        //String str = prefs.getString(DATA, "");
        //if(str.equals("")) {
        //  tilesList = new ArrayList<>();
        // }
        //if (!save_tile.equals(null)) {
        if (save_tile != null) {
            tiles_data_list.clear();//work clear data Represent tile
            Log.d("halholaym",tiles_data_list.toString());
            tiles_data_list.addAll(save_tile);// add all tile
            Log.d("halholaym",tiles_data_list.toString());
            //System.out.println(tiles_data_list.addAll(save_tile));
            call_data_tit.notifyDataSetChanged();//connect data call ListView
            //System.out.println();
            Log.d("halholaym",call_data_tit.toString());
        }
    }
}
