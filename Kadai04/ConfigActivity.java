package org.example.kadai04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ConfigActivity extends AppCompatActivity {
    //オブジェクト一覧
    Button btnBlue;
    Button btnRed;
    Button btnYellow;
    Button btnDef;
    Button btnOK;
    Button btnC;
    Intent i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //テーマの設定はsetContentViewの前に行う
        setContentView(R.layout.activity_config);
        Log.d("LIFE", "config_onCreate");
    }

    public void btnBlue_onClick(View view){
        //setResultでMainActivityに戻す値をセット
        i = new Intent(this, org.example.kadai04.MainActivity.class);
        i.putExtra("style", Int(R.style.BlueTheme));
        setResult(RESULT_OK, i);
        startActivity(i);
    }

    //TODO キャンセル以外のボタンのプログラム



    //キャンセルボタンでConfigActivityを終了
    public void btnC_onClick(View v) {
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
