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

public class activity_courseinfo extends AppCompatActivity {

    //private SQLiteDatabase myDb;
    //private DBHelper dbHelper;
    private MyDAO myDAO;
    private ListView listView;
    private List<Map<String,Object>> listData;
    private Map<String,Object> listItem;
    private SimpleAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //dbHelper = new DBHelper(this, "info_System.db",null, 24); //打开数据库
        //myDb = dbHelper.getReadableDatabase();
        myDAO = new MyDAO(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseinfo);
        setTitle("课程信息");


        Intent intent = getIntent();
        Cursor c = myDAO.get_Db().rawQuery("select * from Course",null);
        c.moveToFirst();
        listView = (ListView)findViewById (R.id.listView_mycourse);   //用listView布局来显示成绩，listItem ,listData的作用去百度
        listData = new ArrayList<Map<String,Object>>();
        listItem=new HashMap<String,Object >();
        listItem.put("_cname","课程名称");
        listItem.put("_cid","课程编号");
        listItem.put("_cteacher", "授课老师");
        listItem.put("_cnum","学分");
        listData.add(listItem);
        while(c.isAfterLast() == false)
        {
            String course_name, course_id, course_teacher;
            int course_num;
            course_name = c.getString(0);
            course_id = c. getString(1);
            course_teacher = c.getString(2);
            course_num = c.getInt(3);
            listItem=new HashMap<String,Object >();
            listItem.put("_cname",course_name);
            listItem.put("_cid",course_id);
            listItem.put("_cteacher", course_teacher);
            listItem.put("_cnum",course_num);
            listData.add(listItem);
            c.moveToNext();
        }
        listAdapter = new SimpleAdapter(this,
                listData,
                R.layout.list_item_2, //自行创建的列表项布局
                new String[]{"_cname","_cid","_cteacher","_cnum"},
                //new String[]{"_cname","_cid"},
                //new int[]{R.id.key,R.id.value});
                new int[]{R.id.cname,R.id.cid,R.id.cteacher,R.id.cnum});
       listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=1) {
                    Map<String, Object> data = listData.get(i);
                    Object course_name = data.get("_cname");
                    String Scourse_name = course_name.toString();
                    Intent intent = new Intent(getApplicationContext(), activity_oneCourse_info.class);
                    intent.putExtra("couser_name", Scourse_name);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Activity activity = activity_courseinfo.this;
        //设置要返回的数据
        Intent intent = new Intent();
        activity.setResult(9, intent);
        activity.finish();
    }
}