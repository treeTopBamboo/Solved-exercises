package org.example.k_09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

//課題6で作成した設定画面
public class Setting extends Activity {
    EditText edName;
    EditText edAdd;
    Button btnOK;
    Button btnC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        edName = (EditText)findViewById(R.id.edName);
        edName.setText("新潟新座");
        edAdd = (EditText)findViewById(R.id.edAdd);
        edAdd.setText("192.168.10.10");
        btnOK = (Button)findViewById(R.id.btnOK);
        btnC = (Button)findViewById(R.id.btnC);
    }
    public void btnOK_Click(View vm) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("NAME",edName.getText().toString());
        i.putExtra("IP", edAdd.getText().toString());
        startActivity(i);
        finish();
    }
    public void setBtnC_Click(View vm){ finish(); }
}