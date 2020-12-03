package org.example.kadai05;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub, container, false);
        Bundle bundle;
        final Activity activity = Objects.requireNonNull(getActivity());
        //タブレットの場合の表示
        if(isTwoPane) {
            bundle = Objects.requireNonNull(getArguments());
        } else {
            //スマホ環境の場合の表示
            Intent intent = activity.getIntent();
            bundle = Objects.requireNonNull(intent.getExtras());
        }

        //受け取った色変数に応じて色変更
        TableLayout tl = view.findViewById(R.id.tl_tableLayout);
        TextView txtColor = view.findViewById(R.id.txtColor);
        int id = bundle.getInt("color");
        switch (id) {
            case Color.BLUE:
                tl.setBackgroundColor(Color.BLUE);
                txtColor.setText("blue");
                break;
            case DEFAULT_COLOR:
                tl.setBackgroundColor(Color.WHITE);
                txtColor.setText("default");
                break;
            default:
                // fail safe
                break;
        }
        //閉じるボタン
        Button btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTwoPane){
                    //タブレット用の閉じる動作
                    getFragmentManager().beginTransaction().remove(SubFragment.this).commit();
                }else{
                    //スマホ用の閉じる動作
                    activity.finish();
                }
            }
        });

        return view;
    }

}
