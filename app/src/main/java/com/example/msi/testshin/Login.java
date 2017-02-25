package com.example.msi.testshin;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Info{
    EditText idText;
    EditText passText;
    String Id;
    String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getIntent().getExtras() == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.id);
        passText = (EditText) findViewById(R.id.pass);

    }

    public void login(View v) {

        if (database != null) {
            Cursor cursor = database.rawQuery("SELECT name, num, pass FROM " + tableName, null);
            int count = cursor.getCount();
            for(int i=0;i<count;i++) {
                cursor.moveToNext();

                Cname = cursor.getString(0);
                Cnum = cursor.getString(1);
                Cpass = cursor.getString(2);
            }
            Id = idText.getText().toString();
            Pass = passText.getText().toString();
            if (Id.equals(Cnum) && Pass.equals(Cpass)) {
                Intent main = new Intent(getApplication(), MainActivity.class);
                main.putExtra("splash", "splash");
                startActivity(main);
                Toast.makeText(getApplicationContext(), Cname + "님 환영합니다.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "정확한 정보를 입력하세요.",
                        Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }

    }

    public void member(View view){

        Intent member = new Intent(getApplication(), Member.class);
        member.putExtra("splash", "splash");
        startActivity(member);

    }

}