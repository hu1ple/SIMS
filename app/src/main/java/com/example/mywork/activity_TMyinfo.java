package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_TMyinfo extends AppCompatActivity {
    private String Tid;
    private String Tname;
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
        setContentView(R.layout.activity_tmyinfo);


        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        setTitle("我的信息");
        Intent intent = getIntent(); //获得 student_activity中的intent中的数据，即学生的学号
        Tid = intent.getStringExtra("T_id");
        //下面在数据库中搜索学生的学号，得到学生姓名、选课等信息
        Cursor c = myDb.rawQuery("select * from Tpersonal_info where 工号=?", new String[]{Tid});
        c.moveToFirst();
        Tname = c.getString(1);

        listView = (ListView)findViewById(R.id.listView_tmyinfo);
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
    }
}