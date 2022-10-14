package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
   // private DBHelper dbHelper;
    private EditText et_id;
    private EditText et_password;
    public  static final Integer version = 24;
    public static final String TB_NAME_1 = "Saccount";//表名
    public static final String TB_NAME_2 = "Stranscripts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("学生信息系统");

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        //dbHelper = new DBHelper(this, "info_System.db",null, version);   //创建或打开数据库
       // myDb = dbHelper.getReadableDatabase();    //对myDb操作即可实现对数据库操作


        Button bt_login =(Button)findViewById(R.id.button_login);   //找到按钮
        Button bt_importData = (Button)findViewById(R.id.bt_import);
        et_id = (EditText) findViewById(R.id.editText_account_name); //找到文本编辑框
        et_password = (EditText) findViewById(R.id.editText_account_password);

        RadioButton radioButton1,radioButton2;
        radioButton1 = findViewById(R.id.iam_teacher);
        radioButton2=findViewById(R.id.iam_student);


        //下面给登录按钮添加监视器
        View.OnClickListener login_listener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //重写onClick
                String account_name = et_id.getText().toString();      //获得用户名和密码
                String account_password = et_password.getText().toString();
                Cursor c = null;
                if(radioButton2.isChecked()) {
                     c = myDb.rawQuery("select * from Saccount where id=?", new String[]{account_name});//在数据库中搜索用户名
                }
                else {
                    c = myDb.rawQuery("select * from Taccount where id=?", new String[]{account_name});
                }

                if(c.getCount()== 0)       //如果没搜到 ,输出用户名不存在
                    Toast.makeText(getApplicationContext(),"用户名不存在！",Toast.LENGTH_SHORT).show();
                else        //数据可中存在该用户名，下面需要比对数据库中的密码和用户输入的密码
                {
                    c.moveToFirst();
                    String real_password = c.getString(1);    //获得数据可中的密码
                    //Toast.makeText(getApplicationContext(),real_password,Toast.LENGTH_SHORT).show();
                    if(account_password.equals(real_password))       //如果两个密码相等
                    {
                        //Toast.makeText(getApplicationContext(),account_name,Toast.LENGTH_SHORT).show();
                        Intent intent;
                        if(radioButton2.isChecked())
                             intent = new Intent(getApplicationContext(), student_activity.class);    //使用intent从该activity转移到新的activity
                        else
                            intent = new Intent(getApplicationContext(),activity_teacher.class);
                        intent.putExtra("_id",account_name);//intent中需要传递数据，需要将学生的学号传输到新的activity中
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        bt_login.setOnClickListener(login_listener);

        View.OnClickListener import_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),activity_importData.class);
                startActivity(intent);
            }
        };
        bt_importData.setOnClickListener(import_listener);

    }
}