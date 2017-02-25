package com.example.msi.testshin;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by hansangjun on 2017. 2. 25..
 */
public class BoardMain extends Activity{
    InputMethodManager imm;
    EditText commentText;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.board_main);


//        TextView tvTitle = (TextView)findViewById(R.id.textView1);
//        TextView tvArtist = (TextView)findViewById(R.id.textView2);
////        ImageView iv = (ImageView)findViewById(R.id.imageView1);
//
//        Intent intent = getIntent(); // 보내온 Intent를 얻는다
//        tvTitle.setText(intent.getStringExtra("title"));
//        tvArtist.setText(intent.getStringExtra("artist"));
////        iv.setImageResource(intent.getIntExtra("img", 0))


        ListView listview;
        final CommentListViewAdapter adapter;
        // Adapter 생성
        adapter = new CommentListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.commentList);
        listview.setAdapter(adapter);

//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;
//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_menu_camera),
//                "hsj3171", "난 이의견 반대일세") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                CommentListViewItem item = (CommentListViewItem) parent.getItemAtPosition(position);

                String idStr = item.getIdStr();
                String commentStr = item.getCommentStr();

                Drawable iconDrawable = item.getIcon();

                // TODO : use item data.
                Toast.makeText(BoardMain.this, "댓글 클릭됨",
                        Toast.LENGTH_SHORT).show();


            }
        });

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        listview.setVerticalScrollBarEnabled(false);
        Button commentbutton1 = (Button) findViewById(R.id.commentButton);
        commentbutton1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText ed1 = (EditText) findViewById(R.id.commentText);

                String str;
                str = ed1.getText().toString();
                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_camera),
                        "hsj3171", str);

                hideKeyboard();
            }
        });


        commentText = (EditText) findViewById(R.id.commentText);


    }

    private void hideKeyboard(){
        imm.hideSoftInputFromWindow(commentText.getWindowToken(),0);
    }

}
