package org.example.kadai06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final static int ACTIVITY_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClick(View view) {
        Intent i = new Intent(this, org.example.kadai06.MyService.class);
        startService(i);
    }

    public void onStopClick(View view) {
        Intent i = new Intent(this, org.example.kadai06.MyService.class);
        stopService(i);
    }
}
