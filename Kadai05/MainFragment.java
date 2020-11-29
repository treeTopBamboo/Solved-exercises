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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.Objects;

public class MainFragment extends Fragment {
    //スマホとタブレットの判定用
    private boolean isTwoPane = false;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = Objects.requireNonNull(getActivity());
        if(activity.findViewById(R.id.subFrame) != null) {
            isTwoPane = true;
        }
    }

    // SubFragmentに渡す色設定用変数の定義
    final private static int DEFAULT_COLOR = -1;
    static int color_id = DEFAULT_COLOR;

    //SubFragmentに表示させる
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Activity activity = Objects.requireNonNull(getActivity());
        //レイアウトファイルからViewオブジェクト生成
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Blueクリックで青色設定用の変数
        Button btnBlue = view.findViewById(R.id.btn_setBlue);
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_id = Color.BLUE;
            }
        });
        //Defaultクリックでデフォルト設定用の変数
        Button btnDefault = view.findViewById(R.id.btn_setDefault);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_id = DEFAULT_COLOR;
            }
        });

        //フラグメント起動準備
        FragmentManager manager = Objects.requireNonNull(getFragmentManager());
        SubFragment fragment = new SubFragment();
        //フラグメントに値を設定
        Bundle bundle = new Bundle();
        bundle.putInt("color", color_id);
        //タブレットならFragment
        if (isTwoPane) {
            fragment.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.subFrame, fragment)
                    .commit();
        } else {
            //スマホならFragment情報ををActivityに
            Intent intent = new Intent(getActivity(), SubActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        return view;
    }

}
