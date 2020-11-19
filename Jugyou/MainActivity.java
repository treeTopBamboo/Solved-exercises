package org.example.k_09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//授業課題をベースに作成
public class MainActivity extends AppCompatActivity {
    EditText edTxt = null;
    ScrollView scrollMsg = null;
    LinearLayout lineMsg = null;
    Socket sock;
    BufferedReader sockin;
    BufferedWriter sockout;
    ConnectionClass cc=new ConnectionClass();
    Thread th;
    Handler handler=new Handler();
    volatile boolean flg=true;

    String name = "長岡長野"; //課題4追加 ニックネーム
    String ip = "192.168.10.10"; //課題6追加 IPアドレス

    final int receive = 0; //課題9追加 受信_you
    final int send = 1;    //課題9追加 送信_me

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxt = findViewById(R.id.edTxt);
        scrollMsg = findViewById(R.id.scrollMsg);
        lineMsg = findViewById(R.id.lineMsg);
        //課題6追加ココから
        Intent i = getIntent();
        name = i.getStringExtra("NAME");
        ip = i.getStringExtra("IP");
        //ココまで課題6追加
    }

    @Override
    protected void onResume() {
        super.onResume();
        th=new Thread(cc);
        th.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            sock.close();
            cc.reStart();
        }catch (Exception e){
            Log.v("Error",e.toString());
        }
    }

    public void btnSend(View view){
        cc.reStart();
    }

    class ConnectionClass implements Runnable{
        @Override
        public void run() {
            try{
                //sock=new Socket("192.168.10.10",55555);
                //課題6追加
                sock = new Socket(ip,55555);
                sockin=new BufferedReader(new
                        InputStreamReader(sock.getInputStream(),"UTF-8"));
                sockout=new BufferedWriter(new
                        OutputStreamWriter(sock.getOutputStream(),"UTF-8"));
                //課題3追加ココから
                ReceiverClass rc = new ReceiverClass();
                Thread rth = new Thread(rc);
                rth.start();
                //ココまで課題3追加
                updateLog("接続しました", send); //課題9 send 追加
                //課題4追加ココから
                sockout.write(name);
                sockout.newLine();
                sockout.flush();
                //ココまで課題4追加
                while(flg){
                    stop();
                    sockout.write(edTxt.getText().toString());
                    sockout.newLine();
                    sockout.flush();
                    /* 課題4でコメントアウト
                    //updateLog(sockin.readLine());
                    //課題3追加ココから
                    updateLog("送信<<" + edTxt.getText().toString());
                    //ココまで課題3追加
                    */
                    //課題9 send 追加
                    updateLog(String.format("自分：%s",edTxt.getText().toString()), send);
                }
            }catch(Exception e){
                Log.v("Error",e.toString());
            }
        }

        synchronized void updateLog(String log, int sor){
            handler.post(new UpdateLogClass(log,sor));
        } //課題9 int sor 追加

        synchronized void stop(){
            try {
                wait();
            } catch (InterruptedException e) {
                Log.v("Error",e.toString());
            }
        }

        synchronized void reStart(){
            notify();
        }

        //課題3追加ココから
        class ReceiverClass implements Runnable {
            @Override
            public void run() {
                try {
                    while(flg){
                        updateLog(sockin.readLine(), receive); //課題9 receive 追加
                    }
                }catch (Exception e){
                    Log.v("Error", e.toString());
                }
            }
        }
        //ココまで課題3追加
    }

    class UpdateLogClass implements Runnable{
        private String logmsg;
        private int sor; //課題9 int sor 追加
        public UpdateLogClass(String log, int sor) {
            super();
            this.logmsg = log;
            this.sor = sor;
        }

        UpdateLogClass(String log){
            logmsg=log;
        }

        @Override
        public void run() {
            /* 課題5でコメントアウト
            TextView tv=new TextView(MainActivity.this);
            tv.setText(logmsg);
            lineMsg.addView(tv);
            */
            //課題5で追加ココから
            //課題9で適用するボタンの設定を変更
            Button bt = new Button(MainActivity.this);
            //bt.setBackgroundResource(R.drawable.buttondeco); //Xmlの適用 (課題9でコメントアウト)
            LinearLayout lineC = new LinearLayout(MainActivity.this);
            bt.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            bt.setText(logmsg);
            if (sor==send){
                bt.setBackgroundResource(R.drawable.buttondeco_me);
                bt.setTextColor(Color.WHITE);
                lineC.setGravity(Gravity.RIGHT);
            } else {
                bt.setBackgroundResource(R.drawable.buttondeco_you);
                bt.setTextColor(Color.WHITE);
                lineC.setGravity(Gravity.LEFT);
            }
            lineC.addView(bt);
            lineMsg.addView(lineC);
            /*bt.setTextColor(Color.WHITE);
            lineMsg.addView(bt,new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)); */
            //課題9でコメントアウト
            //ココまで課題5で追加
            scrollMsg.scrollBy(0,scrollMsg.getBottom());
        }
    }
    //課題7追加ココから
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu); //メニュー用Xmlの指定
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // menu.xml内のitemタグのidと比較
        if (id == R.id.itemAbout) {
            int versionCode = 0;
            String versionName = "";
            PackageManager packageManager = this.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(
                        this.getPackageName(), PackageManager.GET_ACTIVITIES);
                versionCode = packageInfo.versionCode;
                versionName = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            AlertDialog ald = new AlertDialog.Builder(this)
                    .setTitle("バージョン情報")
                    .setMessage("VersionCode =" + versionCode + "\n" + "Version =" + versionName)
                    .setPositiveButton("OK", null)
                    .create();
            ald.show();
        }else if (id == R.id.itemSetting) {
            Intent i = new Intent(this, Setting.class);
            startActivity(i);
        }
        return true;
    }
    //ココまで課題7追加
    //課題8で else if 追加

}

