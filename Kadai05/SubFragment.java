package org.example.kadai05;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class SubFragment extends Fragment {
    private boolean isTwoPane = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = Objects.requireNonNull(getActivity());
        if(activity.findViewById(R.id.subFrame) != null) {
            isTwoPane = true;
        }
    }

    // デフォルトカラーIDの定義と変数の定義
    final private static int DEFAULT_COLOR = -1;
    static int color_id = DEFAULT_COLOR;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub, container, false);
        Bundle bundle;
        final Activity activity = Objects.requireNonNull(getActivity());
        //タブレットの場合
        if(isTwoPane) {
            bundle = Objects.requireNonNull(getArguments());
        } else {
            //スマホ環境の場合
            Intent intent = activity.getIntent();
            bundle = Objects.requireNonNull(intent.getExtras());
        }

        //閉じるボタン
        Button btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(SubFragment.this).commit();
            }
        });

        //受け取った色変数に応じて色変更
        TableLayout tl = view.findViewById(R.id.tl_tableLayout);
        TextView txtColor = view.findViewById(R.id.txtColor);
        int id = bundle.getInt("color");
        switch (id) {
            case Color.BLUE:
                tl.setBackgroundColor(Color.BLUE);
                txtColor.setText("Blue");
                break;
            case DEFAULT_COLOR:
                tl.setBackgroundResource(DEFAULT_COLOR);
                txtColor.setText("Default");
                break;
            default:
                // fail safe
                break;
        }
        return view;
    }

}
