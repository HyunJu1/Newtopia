package com.example.msi.testshin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by msi on 2017-02-25.
 */

public class NewsBoard extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
{
    DBHelper dbh;
    EditText edit_newsUrl;
    Spinner optionSpinner;
    EditText edit_subject;
    EditText edit_field;
    EditText edit_name;
    String option;
    String[] items = { "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일", };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_board);
        edit_newsUrl = (EditText)findViewById(R.id.textUrl);
        dbh = new DBHelper(this);

        optionSpinner = (Spinner) findViewById(R.id.spinner);
        optionSpinner .setOnItemSelectedListener(this);
// adapter를 정의하고 items을 dropdown 형태로 붙인다.
        ArrayAdapter<String> aa= new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
// spin에  adapter를 붙여넣는다.
        optionSpinner.setAdapter(aa);

    }
    public void onClickRegisterButton(View v){
        String newsUrl = edit_newsUrl.getText().toString() ;
        String subject = edit_subject.getText().toString();
        String field = edit_field.getText().toString();
        String name = edit_name.getText().toString();

        if (dbh.insertBoard(newsUrl,option,subject,field,name)){
            Toast.makeText(getApplicationContext(), "insert Movie", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "insert fail", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        // 특정한 spinner 내의 항목 호출
        option = items[arg2];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        // 선택이 해제될때
    }

}


