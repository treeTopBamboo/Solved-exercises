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

public class ConfigActivity extends AppCompatActivity {
    //オブジェクト一覧
    Button btnBlue;
    Button btnRed;
    Button btnYellow;
    Button btnDef;
    Button btnOK;
    Button btnC;
    Intent i;
    LinearLayout layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setThemeで背景色を変更するときはsetContentViewより前
        setContentView(R.layout.activity_config);
        //Logでアクティビティのライフサイクルを監視
        Log.d("LIFE", "config_onCreate");

        //今回は背景色の変更はsetThemeではなくLinerLayoutで適用させる
        layout = findViewById(R.id.layout);
        layout.setBackgroundColor(Color.LTGRAY); //デフォルト

    }

    public void onClick(View view) {
        //setResultでMainActivityに戻す値をセット
        try {
            layout = findViewById(R.id.layout);
            btnBlue = findViewById(R.id.btnBlue);
            btnRed = findViewById(R.id.btnRed);
            btnYellow = findViewById(R.id.btnYellow);
            btnDef = findViewById(R.id.btnDef);

            switch (view.getId()) {
                case R.id.btnBlue:
                    layout.setBackgroundColor(Color.BLUE);
                    i = new Intent();
                    i.putExtra("color", getString(R.string.btnBlue_name));
                    break;
                case R.id.btnRed:
                    layout.setBackgroundColor(Color.RED);
                    i = new Intent();
                    i.putExtra("color", getString(R.string.btnRed_name));
                    break;
                case R.id.btnYellow:
                    layout.setBackgroundColor(Color.YELLOW);
                    i = new Intent();
                    i.putExtra("color", getString(R.string.btnYellow_name));
                    break;
                case R.id.btnDef:
                    layout.setBackgroundColor(Color.LTGRAY);
                    i = new Intent();
                    i.putExtra("color", getString(R.string.btnDef_name));
                    break;
                default:
                    break;
            };
            super.onRestart();
        } catch (Exception e) {
            Toast.makeText(ConfigActivity.this, R.string.toast_C, Toast.LENGTH_SHORT).show();
        }
    }

    //OK
    public void btnOK_onClick(View view) {
        setResult(RESULT_OK, i);
        finish();
    }

    //キャンセル
    public void btnC_onClick(View v) {
        setResult(RESULT_CANCELED, i);
        finish();
    }

    @Override
    protected void onRestart() {
        Log.d("LIFE", "sub_onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d("LIFE", "sub_onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("LIFE", "sub_onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("LIFE", "sub_onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LIFE", "sub_onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFE", "sub_onDestroy");
        super.onDestroy();
    }

}
