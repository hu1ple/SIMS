package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_oneCourse_info extends AppCompatActivity {
    private String Cname;
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
   // private DBHelper dbHelper;
    private ListView listView;
    private List<Map<String,String>> listData;
    private Map<String,String> listItem;
    private SimpleAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        //dbHelper = new DBHelper(this, "info_System.db",null, 24); //打开数据库
       // myDb = dbHelper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_course_info);
        Intent intent = getIntent();
        Cname = intent.getStringExtra("couser_name");
        setTitle("课程信息");
        //Toast.makeText(getApplicationContext(),Cname,Toast.LENGTH_SHORT).show();
        listView = findViewById(R.id.listView_oneCourse);
        listData = new ArrayList<Map<String,String>>();
        Cursor c = myDb.rawQuery("select * from Course where 课程名称=?", new String[]{Cname});
        c.moveToFirst();
        for(int i=0;i<c.getColumnCount();i++)
        {
            String key = c.getColumnName(i);
            String value;
            if(i== c.getColumnCount()-1){
                Integer count = c.getInt(i);
                value = count.toString();
            }
            else value = c.getString(i);
            listItem = new HashMap<String ,String>();
            listItem.put("_key", key);
            listItem.put("_value",value);
            listData.add(listItem);
        }
        listAdapter = new SimpleAdapter(this,listData,R.layout.list_item,
                new String[]{"_key", "_value"},
                new int[]{R.id.key,R.id.value});
        listView.setAdapter(listAdapter);
    }
}