package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_teacher extends AppCompatActivity implements View.OnClickListener {

    private String Tname;
    private String Tid;
    private EditText et_tname;
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
    //private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
       // dbHelper = new DBHelper(this, "info_System.db",null, 24);   //打开数据库
       // myDb = dbHelper.getReadableDatabase();
        Intent intent = getIntent();
        Tid = intent.getStringExtra("_id");
        Toast.makeText(getApplicationContext(),Tid,Toast.LENGTH_SHORT).show();

        et_tname = findViewById(R.id.editText_teacher_name);
        Cursor c = myDb.rawQuery("select * from Taccount where id=?", new String[]{Tid});
        c.moveToFirst();
        Tname = c.getString(2);
        et_tname = findViewById(R.id.editText_teacher_name);
        et_tname.setText(Tname); //将姓名显示到文本框中

        Button bt_myinfo = findViewById(R.id.bt_tMyinfo);
        Button bt_course = findViewById(R.id.bt_tcourseinf);
        Button bt_transcript = findViewById(R.id.bt_transcripts);
        Button bt_mycourse = findViewById(R.id.bt_tMycourse);
        bt_myinfo.setOnClickListener(this);
        bt_mycourse.setOnClickListener(this);
        bt_course.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.bt_tMyinfo:
                intent = new Intent(getApplicationContext(), activity_TMyinfo.class);
                intent.putExtra("T_id",Tid);
                startActivity(intent);
                break;
            case R.id.bt_tMycourse:
                intent = new Intent(getApplicationContext(),activity_TmyCourse.class);
                intent.putExtra("T_id",Tid);
                startActivity(intent);
                break;
            case R.id.bt_tcourseinf:
                intent = new Intent(getApplicationContext(),activity_courseinfo.class);
                startActivity(intent);
            default:break;
        }
    }
}