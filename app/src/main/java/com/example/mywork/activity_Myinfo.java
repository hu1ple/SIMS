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

public class activity_Myinfo extends AppCompatActivity {
    private String Sid;
    private String Sname;
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
   // private DBHelper dbHelper;
    private ListView listView;
    private List<Map<String,String>> listData;
    private Map<String,String> listItem;
    private SimpleAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
       // dbHelper = new DBHelper(this, "info_System.db",null, 24); //打开数据库
        //myDb = dbHelper.getReadableDatabase();
        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        setTitle("我的信息");


        Intent intent = getIntent(); //获得 student_activity中的intent中的数据，即学生的学号
        Sid = intent.getStringExtra("S_id");
        //下面在数据库中搜索学生的学号，得到学生姓名、选课等信息
        Cursor c = myDb.rawQuery("select * from Spersonal_info where 学号=?", new String[]{Sid});
        c.moveToFirst();
        Sname = c.getString(1);

        listView = (ListView)findViewById(R.id.listView_myinfo);
        listData = new ArrayList<Map<String,String>>();
        int col_n=c.getColumnCount();
        for(int i=0;i<col_n;i++)
        {
            String columnName = c.getColumnName(i);
            String columnValue = c.getString(i);
            listItem=new HashMap<String,String >();
            listItem.put("_key",columnName);
            listItem.put("_value",columnValue);
            listData.add(listItem);
        }
        listAdapter = new SimpleAdapter(this,
                listData,
                R.layout.list_item, //自行创建的列表项布局
                new String[]{"_key","_value"},
                new int[]{R.id.key,R.id.value});
        listView.setAdapter(listAdapter);

        /*c=myDb.rawQuery("select * from Smy_course where 学号=?", new String[]{Sid});
        c.moveToFirst();
        listView = (ListView) findViewById(R.id.listView_mycourse);
        listData = new ArrayList<Map<String,String>>();
        col_n=c.getColumnCount();
        for(int i=0;i<col_n;i++)
        {
            String columnName = c.getColumnName(i);
            String columnValue = c.getString(i);
            listItem=new HashMap<String,String >();
            listItem.put("_key",columnName);
            listItem.put("_value",columnValue);
            listData.add(listItem);
        }
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
        });*/



    }
    @Override
    public void onBackPressed()
    {
        Activity activity = activity_Myinfo.this;
        //设置要返回的数据
        Intent intent = new Intent();
        activity.setResult(10, intent);
        activity.finish();
    }
}