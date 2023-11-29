package com.example.ass1_mohammad_1191413;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //def
    private Button view;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Introduction to the interfaces page
        //Learn about the tools used
        view = findViewById(R.id.viewt);
        add = findViewById(R.id.add);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_view();//Navigate to the tile display page
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_add();//Navigate to a page to add tiles
            }
        });
    }
    //Navigate to the tile display page
    private void open_view() {
        Intent intent = new Intent(this, TilesActivity.class);//Navigate to class TilesActivity
        startActivity(intent);
    }
    //Navigate to a page to add tiles
    private void open_add() {
        Intent intent = new Intent(this, AddTileActivity.class);//Navigate to class AddTileActivity
        startActivity(intent);
    }
}
