package org.example.kadai04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //オブジェクト一覧
    Button btn;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setThemeは、setContentViewの前に設定する
        i = getIntent();
        Int style = i.getIntExtra("style");
        setTheme(style);

        setContentView(R.layout.activity_main);
        //Logでアクティビティのライフサイクルを監視
        Log.d("LIFE", "onCreate");
    }

    //設定ボタンClickでインテントの開始、戻ってくる引き数の設定
    public void btn_onClick(View v) {
        i = new Intent(this,org.example.kadai04.ConfigActivity.class);
        startActivityForResult(i,1);
    }

    @Override
    protected void onRestart() {
        Log.d("LIFE", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d("LIFE", "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("LIFE", "onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("LIFE", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LIFE", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFE", "onDestroy");
        super.onDestroy();
    }
}
