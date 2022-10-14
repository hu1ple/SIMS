package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_TmyCourse extends AppCompatActivity {

    private String Tid;
    private String Course_name;
    private String Tname;
    private SQLiteDatabase myDb;
    private MyDAO myDAO;
    //private DBHelper dbHelper;
    private ListView listView;
    private List<Map<String,String>> listData;
    private Map<String,String> listItem;
    private SimpleAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmy_course);

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        Intent intent = getIntent(); //获得 student_activity中的intent中的数据，即学生的学号
        Tid = intent.getStringExtra("T_id");
        //下面在数据库中搜索学生的学号，得到学生姓名、选课等信息
        Cursor c = myDb.rawQuery("select * from Tpersonal_info where 工号=?", new String[]{Tid});
        c.moveToFirst();
        Tname = c.getString(1);

        listView = findViewById(R.id.listView_tmycourse);
        listData = new ArrayList<Map<String,String>>();

        c = myDb.rawQuery("select * from Course where 授课老师=?", new String[]{Tname});  //在Course表中找到该老师对应的课程
        if(c.getCount()!= 0 ) {

            c.moveToFirst();
            Course_name = c.getString(0);
            for(int i=0;i<c.getColumnCount();i++)
            {
                String key = c.getColumnName(i);
                if(key != "授课老师") {
                    String value;
                    if (i == c.getColumnCount() - 1) {
                        Integer count = c.getInt(i);
                        value = count.toString();
                    } else value = c.getString(i);
                    listItem = new HashMap<String, String>();
                    listItem.put("_key", key);
                    listItem.put("_value", value);
                    listData.add(listItem);
                }
            }
            listAdapter = new SimpleAdapter(this,listData,R.layout.list_item,
                    new String[]{"_key", "_value"},
                    new int[]{R.id.key,R.id.value});
            listView.setAdapter(listAdapter);
        }

        Button bt_list = findViewById(R.id.bt_checkAll);
        View.OnClickListener checkList  = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_studentList.class);
                intent.putExtra("course",Course_name);
                startActivity(intent);
            }

        };
        bt_list.setOnClickListener(checkList);
    }







}