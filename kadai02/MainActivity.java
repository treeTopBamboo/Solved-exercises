package org.example.kadai02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(String.format("RatingBarが切り替わりました。"));
            }
        });
    }

    public void btnSend_onClick(View view) {
        TextView textView = findViewById(R.id.textView);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        ImageView imageView = findViewById(R.id.imageView);

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
                textView.setText(String.format("ImageViewを押しました。"));
                break;
            default:
                break;
        }
    }

    public void btnSend_onCheckedChanged(View view) {
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
    }
}
