package org.example.kadai04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //オブジェクト一覧
    Button btn;
    Intent i;
    LinearLayout layout;
    Button btnBlue;
    Button btnRed;
    Button btnYellow;
    Button btnDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setThemeで背景色を変更するときはsetContentViewより前
        setContentView(R.layout.activity_main);
        //Logでアクティビティのライフサイクルを監視
        Log.d("LIFE", "onCreate");

        //今回は背景色の変更はsetThemeではなくLinerLayoutで適用させる
        layout = findViewById(R.id.layout);
        layout.setBackgroundColor(Color.LTGRAY); //デフォルト
    }

    //FIXME ActivityのVersion 1.2.0-alpha04ではstartActivityForResult()は非推奨
    //設定ボタンClickでインテントの開始、戻ってくる引き数の設定
    public void btn_onClick(View v) {
        i = new Intent(this,org.example.kadai04.ConfigActivity.class);
        startActivityForResult(i,1);
    }

    //FIXME ActivityのVersion 1.2.0-alpha04ではonActivityResult()は非推奨
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //RESULT_OKの場合
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //MainActivityの色変更
            try {
                btnBlue = findViewById(R.id.btnBlue);
                btnRed = findViewById(R.id.btnRed);
                btnYellow = findViewById(R.id.btnYellow);
                btnDef = findViewById(R.id.btnDef);
                i = getIntent();
                String color = data.getStringExtra("color");
                if (color.equals(getString(R.string.btnBlue_name))){
                    layout.setBackgroundColor(Color.BLUE);
                }else if(color.equals(getString(R.string.btnRed_name))){
                    layout.setBackgroundColor(Color.RED);
                }else if(color.equals(getString(R.string.btnYellow_name))){
                    layout.setBackgroundColor(Color.YELLOW);
                }else if(color.equals(getString(R.string.btnDef_name))){
                    layout.setBackgroundColor(Color.LTGRAY);
                }
                Toast.makeText(MainActivity.this, "Set "+ color, Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(MainActivity.this, "NotSet", Toast.LENGTH_SHORT).show();
            }
        }else {
            //RESULT_CANCELEDの場合
            Toast.makeText(MainActivity.this,R.string.toast_S, Toast.LENGTH_SHORT).show();
        }
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
