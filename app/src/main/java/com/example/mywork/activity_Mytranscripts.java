package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_Mytranscripts extends AppCompatActivity {
    private String Sid;
    private String Sname;
    private SQLiteDatabase myDb;
    //private DBHelper dbHelper;
    private MyDAO myDAO;
    private ListView listView;
    private List<Map<String,String>> listData;
    private Map<String,String> listItem;
    private SimpleAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        //dbHelper = new DBHelper(this, "info_System.db",null, 24); //打开数据库
        //myDb = dbHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytranscripts);
        setTitle("我的成绩单");

        Intent intent = getIntent(); //获得 student_activity中的intent中的数据，即学生的学号
        Sid = intent.getStringExtra("S_id");
        //下面在数据库中搜索学生的学号，得到学生姓名、成绩等信息
        Cursor c = myDb.rawQuery("select * from Saccount where id=?", new String[]{Sid});
        c.moveToFirst();
        Sname = c.getString(2);

        c = myDb.rawQuery("select * from Stranscripts where 学号=?", new String[]{Sid}); //c中保存了学生的信息

        listView = (ListView)findViewById (R.id.listView);   //用listView布局来显示成绩，listItem ,listData的作用去百度
        listData = new ArrayList<Map<String,String>>();

        c.moveToFirst();
        int col_n=c.getColumnCount();   //得到学生信息的总列数，目前共 学号，姓名，高数， 线代， 大物 5列
        for(int i=0;i<col_n;i++)       //遍历每列，得到列名和对应列的值，如i=0 时，  columnName =“学号”， columnValue = "0122"
        {
            String columnName = c.getColumnName(i);
            String columnValue = "";
            if(i>=2){                      //如果列名是科目，则值是整型，需要转换为string型
                Integer mark = c.getInt(i);
                if (mark == -1)
                    columnValue = "未选";
                else
                columnValue= mark.toString();
            }
            else
                columnValue  = c.getString(i);
            listItem=new HashMap<String,String >();
            listItem.put("_key",columnName);
            listItem.put("_value",columnValue);
            listData.add(listItem);
        }
        //下面是将信息显示到listview中
        listAdapter = new SimpleAdapter(this,
                listData,
                R.layout.list_item, //自行创建的列表项布局
                new String[]{"_key","_value"},
                new int[]{R.id.key,R.id.value});
       listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=2) {
                    Map<String, String> data = listData.get(i);
                    String course_name = data.get("_key");
                    Intent intent = new Intent(getApplicationContext(), activity_oneCourse_info.class);
                    intent.putExtra("couser_name", course_name);
                    startActivity(intent);
                }
            }
        });


    }

    //重写返回键，返回到学生工作台页面
    @Override
    public void onBackPressed()
    {
        Activity activity = activity_Mytranscripts.this;
        //设置要返回的数据
        Intent intent = new Intent();
        activity.setResult(8, intent);
        activity.finish();
    }
}