package org.example.kadai05;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.Objects;

public class MainFragment extends Fragment {
    TextView textView;
    //スマホとタブレットの判定用
    private boolean isTwoPane = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = Objects.requireNonNull(getActivity());
        //subFrameの有無で利用レイアウトの判定
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

        try{
            //Blueクリックで青色設定用の変数
            Button btnBlue = view.findViewById(R.id.btn_setBlue);
            btnBlue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_id = Color.BLUE;
                    //青色ボタンでのフラグメント起動準備
                    FragmentManager manager = Objects.requireNonNull(getFragmentManager());
                    SubFragment fragment = new SubFragment();
                    //フラグメントに青色用の値を設定
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
                }
            });
            //Defaultクリックでデフォルト設定用の変数
            Button btnDefault = view.findViewById(R.id.btn_setDefault);
            btnDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_id = DEFAULT_COLOR;
                    //デフォルトボタンでのフラグメント起動準備
                    FragmentManager manager = Objects.requireNonNull(getFragmentManager());
                    SubFragment fragment = new SubFragment();
                    //フラグメントにデフォルト用値を設定
                    Bundle bundle = new Bundle();
                    bundle.putInt("color", color_id);
                    //タブレットならFragmentで
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
                }
            });

        }catch (Exception e){
            //エラーした時用の表示
            textView.findViewById(R.id.textView);
            textView.setText(String.format("Fragment Error"));
        }
        return view;
    }

}
