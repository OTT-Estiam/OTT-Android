package com.estiam.ott_estiam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = new Intent(getBaseContext(),PlayerActivity.class);
        startActivity(intent);
    }
}
