package com.example.msi.testshin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by msi on 2017-02-25.
 */

public class NewsBoard extends AppCompatActivity {
    DBHelper dbh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_board);
    }
    public void onClickRegisterButton(View v){
        dbh = new DBHelper(this);

    }
}
