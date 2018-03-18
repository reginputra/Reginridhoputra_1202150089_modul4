package com.example.android.reginridhoputra_1202150089_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tampilNama(View view) {
        Intent i = new Intent(getApplicationContext(),MahasiswaActivity.class);
        startActivity(i);
    }

    public void tampilGambar(View view) {
        Intent i = new Intent(getApplicationContext(),GambarActivity.class);
        startActivity(i);
    }
}
