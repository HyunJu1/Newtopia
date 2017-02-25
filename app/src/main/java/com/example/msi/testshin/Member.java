package com.example.msi.testshin;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 박성균 on 2015-04-11.
 */
public class Member extends Info {
    EditText NAME,PASS,PASSSIGN,NUM,MAJOR;
    String Tname, Tpass, Tpasssign,Tnum,Tmajor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member);
        NAME = (EditText) findViewById(R.id.name);
        PASS = (EditText) findViewById(R.id.password);
        PASSSIGN = (EditText) findViewById(R.id.passsign);
        NUM = (EditText) findViewById(R.id.num);
        MAJOR = (EditText) findViewById(R.id.major);


        Button join = (Button) findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tname = NAME.getText().toString();
                Tpass = PASS.getText().toString();
                Tpasssign = PASSSIGN.getText().toString();
                Tnum = NUM.getText().toString();
                Tmajor = MAJOR.getText().toString();
                Cursor cursor = database.rawQuery("SELECT name, num, major FROM " + tableName, null);
                int count = cursor.getCount();
                for(int i=0;i<count;i++) {
                    cursor.moveToNext();

                    Cname = cursor.getString(0);
                    Cnum = cursor.getString(1);
                    Cmajor = cursor.getString(2);

                }
                if (Tname.length()<2) {
                    Toast.makeText(getApplicationContext(), "이름을 정확하게 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                } else if (Tpass.length() <4) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 4자리 이상 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                } else if (Tpasssign.length() <4) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.",
                            Toast.LENGTH_SHORT).show();
                } /*else if (Tnum.length() <4 || Tname.equals(Cnum)) {
                    Toast.makeText(getApplicationContext(), "ID는 4자리이상 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }*/ /*else if (Tmajor.length() <3) {
                    Toast.makeText(getApplicationContext(), "학과이름을 정확히 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                } */else {
                    try{
                        if (database != null) {
                            database.execSQL("INSERT INTO " + tableName + "(name, pass, passCheck, num, major) VALUES" +
                                    "(" + "'" + Tname + "'" + "," + "'" + Tpass + "'" + "," + "'" + Tpasssign + "'" + "," + Tnum + "," + "'" + Tmajor + "'" + ")");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent login = new Intent(getApplication(), InitActivity.class);
                    login.putExtra("splash", "splash");
                    startActivity(login);
                    finish();
                    Toast.makeText(getApplication(), Tname + "님 회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();


                }

            } });
    }

}