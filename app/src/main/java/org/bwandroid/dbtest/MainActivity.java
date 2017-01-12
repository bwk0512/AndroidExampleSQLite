package org.bwandroid.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text_Num; // 화면에 보이는 숫자
    int cnt = 0; // 숫자

    DBManager db; // DB 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_Num = (TextView) findViewById(R.id.textView_Num);

        db = new DBManager(this, "RECORD_LIST", "(number INTEGER)"); // DB 객체화

        if (db.GetRowCount() == 0) {
            db.InsertRow("(1)");
        }

        cnt = db.PrintData(); // DB에 있는 숫자 불러오기
        text_Num.setText("" + cnt); // 화면 갱신

       findViewById(R.id.button_Down).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        cnt--;
                        db.InputQuery("UPDATE RECORD_LIST SET number="+cnt+" WHERE number="+(cnt+1)+"");
                        text_Num.setText("" + cnt);
                    }
                }
        );
        findViewById(R.id.button_Up).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        cnt++;
                        db.InputQuery("UPDATE RECORD_LIST SET number="+cnt+" WHERE number="+(cnt-1)+"");
                        text_Num.setText("" + cnt);
                    }
                }
        );
        findViewById(R.id.button_New).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        db.InputQuery("INSERT INTO RECORD_LIST VALUES(0)");
                    }
                }
        );

    }
}
