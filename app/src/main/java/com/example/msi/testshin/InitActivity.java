package com.example.msi.testshin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;


public class InitActivity extends  Activity {

    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        //Session.initialize(this, AuthType.KAKAO_TALK);

        initControl();

        callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);


    }

        private Button button_login, button_register, button_goMain;
    private void initControl() {
        button_login = (Button) findViewById(R.id.button_login);
        button_register= (Button) findViewById(R.id.button_register);

        button_goMain = (Button) findViewById(R.id.button_goMain);


        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(InitActivity.this, Login.class);
                InitActivity.this.startActivity(intent);

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(InitActivity.this, Member.class);
                InitActivity.this.startActivity(intent);

            }
        });
        button_goMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(InitActivity.this, MainActivity.class);
                InitActivity.this.startActivity(intent);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

private class SessionCallback implements ISessionCallback {

    @Override
    public void onSessionOpened() {

        redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        if(exception != null) {
            Logger.e(exception);
        }
        setContentView(R.layout.activity_init); // 세션 연결이 실패했을때
    }                                            // 로그인화면을 다시 불러옴
}

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}

