package com.example.mywork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_studentList extends AppCompatActivity {

    private SQLiteDatabase myDb;
    private MyDAO myDAO;
    private String Course;
    // private DBHelper dbHelper;
    private ListView listView;
    private List<Map<String, String>> listData;
    private Map<String, String> listItem;
    private SimpleAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        myDAO = new MyDAO(this);
        myDb = myDAO.get_Db();
        Intent intent = getIntent();
        Course = intent.getStringExtra("course");

        Cursor c = myDb.rawQuery("select * from Smy_course ", null);
        c.moveToFirst();
        listView = (ListView) findViewById(R.id.listView_list);
        listData = new ArrayList<Map<String, String>>();
        listItem = new HashMap<String, String>();
        listItem.put("id", "学号");
        listItem.put("name", "姓名");
        listData.add(listItem);
        while (!c.isAfterLast()) {
            @SuppressLint("Range") String res = c.getString(c.getColumnIndex(Course));
            if (res.equals("已选")) {
                String Sid = c.getString(0), Sname = c.getString(1);
                listItem = new HashMap<String, String>();
                listItem.put("id", Sid);
                listItem.put("name", Sname);
                listData.add(listItem);

            }
            c.moveToNext();
        }
        listAdapter = new SimpleAdapter(this,
                listData,
                R.layout.list_item, //自行创建的列表项布局
                new String[]{"id", "name"},
                new int[]{R.id.key, R.id.value});
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=1) {
                    Map<String, String> data = listData.get(i);
                    customDialog(data.get("id"));
                }
            }
        });
    }

    private void customDialog(String Sid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_studentList.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(activity_studentList.this, R.layout.activity_custom, null);
        dialog.setView(dialogView);
        dialog.show();
        Button bt_info = dialogView.findViewById(R.id.btn_checkInfo);
        Button bt_transcripts = dialogView.findViewById(R.id.btn_checkTranscripts);
        Button bt_course = dialogView.findViewById(R.id.btn_checkCourse);
        bt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),activity_Myinfo.class);
                intent.putExtra("S_id", Sid );
                startActivity(intent);
            }
        });

        bt_transcripts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),activity_Mytranscripts.class);
                intent.putExtra("S_id",Sid);
                startActivity(intent);
            }
        });

        bt_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),activity_SmyCourse.class);
                intent.putExtra("S_id",Sid);
                startActivity(intent);
            }
        });

    }
}