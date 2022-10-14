package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class student_activity extends Activity implements View.OnClickListener {
    private String Sid;
    private String Sname;
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
   // private DBHelper dbHelper;
    private EditText et_sname;
    private Button bt_Mytranscripts;
    private Button bt_Myinfo;
    private Button bt_courseinfo;
    private Button bt_myCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        //dbHelper = new DBHelper(this, "info_System.db",null, 24);   //打开数据库
        //myDb = dbHelper.getReadableDatabase();
        //Toast.makeText(getApplicationContext(),"调用oncreate",Toast.LENGTH_SHORT).show();
        setTitle("工作台");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //下面用intent获得mainactiivty中的信息，即学生的学号
        Intent intent = getIntent();
        Sid = intent.getStringExtra("_id");
        //下面由学生的学号，到数据库中查询姓名
        Cursor c = myDb.rawQuery("select * from Saccount where id=?", new String[]{Sid});
        c.moveToFirst();
        Sname = c.getString(2);
        et_sname = findViewById(R.id.editText_student_name);
        et_sname.setText(Sname); //将姓名显示到文本框中

        bt_Mytranscripts = findViewById(R.id.bt_Mytranscripts);
        bt_Mytranscripts.setOnClickListener(this);          //给我的成绩单按钮添加监视器
        bt_Myinfo = findViewById(R.id.bt_Myinfo);
        bt_Myinfo.setOnClickListener(this);
        bt_courseinfo = findViewById(R.id.bt_courseinf);
        bt_courseinfo.setOnClickListener(this);
        bt_myCourse = findViewById(R.id.bt_Mycourse);
        bt_myCourse.setOnClickListener(this);


    }

    //监视器需要重写onclick
    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){

            case R.id.bt_Mytranscripts:
                 intent = new Intent(getApplicationContext(), activity_Mytranscripts.class);  //点击按钮后，将学号通过intent传递到Mytranscript中。
                intent.putExtra("S_id",Sid);
                //startActivity(intent);
                startActivityForResult(intent,1);     //这里涉及到activity的转移方法，略微复杂
                break;
            case R.id.bt_Myinfo:
                 intent = new Intent(getApplicationContext(), activity_Myinfo.class);
                intent.putExtra("S_id", Sid );
                startActivityForResult(intent, 2);
                break;
            case R.id.bt_courseinf:
                intent = new Intent(getApplicationContext(),activity_courseinfo.class);
                startActivityForResult(intent,3);
                break;
            case R.id.bt_Mycourse:
                intent = new Intent(getApplicationContext(), activity_SmyCourse.class);
                intent.putExtra("S_id", Sid );
                startActivityForResult(intent, 4);
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode){
            case 8:
                break;
            case 10:
                break;
            case 9:
                break;
            default:
                break;

        }
    }
}