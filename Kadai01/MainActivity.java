package org.example.kadai01;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //editTextに入力した文字列をButton押下でtextViewに反映
    public void btnSend_onClick(View view) {
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView.setText(String.format("入力された文字は、%sです。", editText.getText().toString()));

        //要件には無かったトースト表示
        //学校で実行した際はText以外の数値も混在表示されたが別環境で実行時には出現せず
        Toast.makeText(MainActivity.this,
                String.format("%sを入力",editText.getText().toString()),
                Toast.LENGTH_SHORT).show();
    }
}
