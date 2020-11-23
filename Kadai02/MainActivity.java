package org.example.kadai02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //オブジェクトの一覧
    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    ImageView imageView;
    CheckBox checkBox;
    Switch switch1;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CheckBoxを切り替えた時の操作
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = findViewById(R.id.checkBox);
                TextView textView = findViewById(R.id.textView);
                textView.setText(String.format(isChecked + "（%s）",checkBox.getText()));
            }
        });
        
        //Switchを切り替えた時の操作
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Switch switch1 = findViewById(R.id.switch1);
                TextView textView = findViewById(R.id.textView);
                textView.setText(String.format(isChecked + "（%s）",switch1.getText()));
            }
        });

        //RatingBarを切り替えた時の操作
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean isChanged) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(String.format(Locale.JAPAN,rating + "（RatingBar）"));
            }
        });
    }

    //ButtonとImageViewを押した時の動作
    public void btnSend_onClick(View view) {
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);

        switch (view.getId()) {
            case R.id.button1:
                textView.setText(String.format("%sを押しました。",button1.getText()));
                break;
            case R.id.button2:
                textView.setText(String.format("%sを押しました。",button2.getText()));
                break;
            case R.id.button3:
                textView.setText(String.format("%sを押しました。",button3.getText()));
                break;
            case R.id.imageView:
                //imageView.getText()にするとText以外の数値も混在して表示するためString指定
                textView.setText(R.string.imageView_text);
                break;
            default:
                break;
        }
    }

    //要件を満たすプログラムを組み上げられなかったため以下はコメントアウト
    /*public void btnSend_onCheckedChanged(View view) {
        TextView textView = findViewById(R.id.textView);
        Switch switch1 = findViewById(R.id.switch1);
        CheckBox checkBox = findViewById(R.id.checkBox);

        switch (view.getId()) {
            case R.id.switch1:
                textView.setText(String.format("%sが切り替わりました。", switch1.getText()));
                break;
            case R.id.checkBox:
                textView.setText(String.format("%sが切り替わりました。", checkBox.getText()));
                break;
            default:
                break;
        }
    }*/
}
