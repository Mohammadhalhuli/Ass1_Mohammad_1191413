package com.example.ass1_mohammad_1191413;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
//ListView with Image
//https://www.youtube.com/watch?v=zEU7lpAjaGo
//https://stackoverflow.com/questions/30340936/how-to-add-condition-in-android-view-getview-int-position-view-convertview-vi
public class TileAdapter extends ArrayAdapter<Tile> {

    private TileDetailActivity tileDetailActivity;
    private int[] imgs;
    private int i=0;
    private Context conte;

        public TileAdapter(Context context, List<Tile> tiles) {
            
            super(context, 0, tiles);
            int[] imgs = new int[0];
            this.imgs=imgs;
        }
    //get a View that display in the drop down popup the data at the specified position in the data set.
    //Get a View that displays the data at the specified position in the data set.
    @Override
    public View getView(int pos, View view_data, ViewGroup parent_view) {
        Tile tile_index = getItem(pos);//Challenge its placement
        if (view_data == null) {
            view_data = LayoutInflater.from(getContext()).inflate(R.layout.item_tile, parent_view, false);
        }
        //show image listView
        ImageView tile_img = view_data.findViewById(R.id.tile_image);
        /*if(tile_img==null){
            Glide.with(getContext()).load(Uri.parse(tile_index.getImagePath())).into(tile_img);
        }*/
        /*if(parent_view == null){
            LayoutInflater lay=(LayoutInflater) conte.getSystemService(conte.LAYOUT_INFLATER_SERVICE);
            parent_view
        }*/
        if (tile_index.getImagePath() != null && !tile_index.getImagePath().isEmpty()) {
            Glide.with(getContext()).load(Uri.parse(tile_index.getImagePath())).into(tile_img);
        }
        //return Super.getView(pos,view_data,parent_view);
        return view_data;//show data listView
    }
    }
