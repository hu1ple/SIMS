package com.example.mywork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDAO {
    private SQLiteDatabase myDb;  //类的成员
    private DBHelper dbHelper;  //类的成员

    public MyDAO(Context context) {  //构造方法，参数为上下文对象
        //第1参数为上下文，第2参数为数据库名
        dbHelper = new DBHelper(context,"info_System.db",null, 26);
        myDb = dbHelper.getReadableDatabase();
    }
    public SQLiteDatabase get_Db(){
        return myDb;
    }
}
