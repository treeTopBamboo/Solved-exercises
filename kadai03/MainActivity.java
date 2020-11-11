package org.example.kadai03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final double INCH = 0.0254;
    final double FEET = 0.3048;
    final double YARD = 0.9144;
    final double MILE = 1609.344;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    EditText editTxt = findViewById(R.id.editTxt);
                    double etNum = Double.parseDouble(editTxt.getText().toString());
                    RadioGroup distance = findViewById(R.id.distance);
                    int rID = distance.getCheckedRadioButtonId();
                    double result = etNum;
                    switch (rID) {
                        case R.id.radioI:
                            result = (etNum * INCH);
                            break;
                        case R.id.radioF:
                            result = (etNum * FEET);
                            break;
                        case R.id.radioY:
                            result = (etNum * YARD);
                            break;
                        case R.id.radioM:
                            result = (etNum * MILE);
                            break;
                        default:
                            break;
                    };
                    TextView txtV = findViewById(R.id.txtV);
                    txtV.setText(result + "");
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"数値を入力してください",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}