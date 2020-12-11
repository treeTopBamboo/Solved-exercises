package org.example.kadai06;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static int ACTIVITY_ID = 1;
    final Handler handler = new Handler();
    Runnable r;
    EditText editTime;
    TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //タイマー開始ボタン
    public void onStartClick(View view) {
        final Intent i = new Intent(this, org.example.kadai06.MyService.class);
        try{
            editTime = findViewById(R.id.editTime);
            timeView = findViewById(R.id.timeView);
            final Runnable r = new Runnable() {
                int count = 0;
                int countMax = Integer.parseInt(editTime.getText().toString());
                @Override
                public void run() {
                    count++;
                    if (count > countMax) {
                        //countがcountMaxより小さければcount++に戻す
                        return;
                    }else if(count == countMax){
                        //countとcountMaxが同じになったらService起動
                        startService(i);
                    }
                    //1000ミリ秒の間隔でcountを行う
                    handler.postDelayed(this, 1000);
                }
            };
            handler.post(r);
        }catch (Exception exception){
            //整数以外を入力された場合
            timeView.setText(getText(R.string.timeView_error));
        }
    }

    //タイマー停止ボタン
    public void onStopClick(View view) {
        Intent i = new Intent(this, org.example.kadai06.MyService.class);
        handler.removeCallbacks(r);
        stopService(i);
    }
}
