package com.example.mywork;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import android.content.res.*;

/*
    SQLite数据库打开助手DbHelper作为抽象类SQLiteOpenHelper的子类
    需要重写2个抽象方法onCreate()和onUpgrade()
*/

//这是个为数据库操作而写得自定义类
//目前数据库包括两个表，一个是账户表，一个是成绩表
public class DBHelper extends SQLiteOpenHelper {
    //private Context context;
    public static final String TB_NAME_1 = "Saccount";//表名
    public static final String TB_NAME_2 = "Stranscripts";
    public static final String TB_NAME_3 = "Spersonal_info";
    public static final String TB_NAME_4 = "Smy_course";
    public static final String TB_NAME_6 = "Taccount";
    public static  final String TB_NAME_5 = "Course";
    public static final String TB_NAME_7 = "Tpersonal_info";
    //public static final int N = 16;
     //构造方法：第1参数为上下文，第2参数库库名，第3参数为游标工厂，第4参数为版本
    public DBHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);  //创建或打开数据库
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //当表不存在时，创建表
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME_1 + "( id varchar ," +
                "password varchar," + "name varchar" +   ")");           //sql命令，创建账户表，及其字段

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TB_NAME_2+ "( 学号 varchar," + "姓名 varchar,"
                + "高等数学 integer," + "线性代数 integer,"
                + "大学物理 integer," +"离散数学 integer,"
                + "数据结构 integer,"+  "计算机组成原理 integer,"
                + "算法分析与设计 integer," + "概率论与数理统计 integer,"
                +"java程序设计 integer," +"羽毛球 integer,"
                +"网球 integer," + "乒乓球 integer,"+
                "飞盘 integer,"+"AI视觉概论 integer,"+ "单片机原理及接口技术 integer," +"通信原理C integer" +     ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_3+ "(学号 varchar," + "姓名 varchar," + "班级 varchar," + "年级 varchar," + "性别 varchar," +
                "学院 varchar," +"专业 varchar," +"民族 varchar" +    ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_4+ "(学号 varchar," + "姓名 varchar,"
                + "高等数学 varchar," + "线性代数 varchar,"
                + "大学物理 varchar," +"离散数学 varchar,"
                + "数据结构 varchar,"+  "计算机组成原理 varchar,"
                + "算法分析与设计 varchar," + "概率论与数理统计 varchar,"
                +"java程序设计 varchar," +"羽毛球 varchar,"
                +"网球 varchar," + "乒乓球 varchar,"+
                "飞盘 varchar,"+"AI视觉概论 varchar," + "单片机原理及接口技术 varchar," + "通信原理C varchar" +   ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_5 +"(课程名称 varchar," + "课程编号 varchar,"+ "授课老师 varchar," +"课程学分 int,"
                +"考察方式 varchar," +"课程性质 varchar," +"总学时 varchar," +" 课程类别 varchar," +"开课学院 varchar," +"选课人数 int" +")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME_6 + "( id varchar ," +
                "password varchar," + "name varchar" +   ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_7 + "(工号 varchar," + "姓名 varchar, "+"学院 varchar," +  "教授课程 varchar," +  "职称 varchar" +  ")");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 执行SQL命令
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_4);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_5);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_6);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_7);
        onCreate(db);
    }


    /*
public static final String[] course = {"高等数学", "线性代数","大学物理","离散数学",
                                             "数据结构", "计算机组成原理","算法分析与设计","概率论与数理统计",
                                            "java程序设计","羽毛球","网球","乒乓球",
                                            "飞盘","AI视觉概论","单片机原理及接口技术","通信原理C"};
    public static final String[] course_id ={"B030001","B030002","B030003","B030004",
                                             "B030005","B030006","B030007","B030008",
                                             "B030009","B030010","B030011","B030012",
                                             "B030013","B030014","B030015","B030016"};
    public static final String[] teacher = {"蔡云峰","叶军","李兴鳌","张琳",
                                            "陈蕾","李维维","陈春玲","朱洪强",
                                            "成小惠","刘晓瑜","李娜","滕勇",
                                            "张小飞","程小刚","倪晓军","高欢芹"};
        //下面是向账户表中添加数据 0122 zsh 张守慧； 0121 zyc 张宇宸
       insert_Saccount("0122","zsh","张守慧",db);
        insert_Saccount("0121","zyc","张宇宸",db);
        insert_Saccount("0120","zsh","张延恒",db);
        insert_Saccount("0119","wyx","汪宇翔",db);
        insert_Saccount("0123","zzh","张棕浩",db);
        //in_Saccount(db);*/


    //下面是向成绩表中添加数据

        /*int[][] tran={{97,100,100,93,93,95,95,95,95,99,-1,-1,-1,88,90,-1},
                {93,90,89,91,92,91,94,90,92,-1,-1,80,-1,-1,90,-1},
                {92,91,94,90,92,93,90,89,91,-1,95,-1,-1,-1,-1,89},
                {92,97,94,91,96,93,91,89,98,-1,-1,-1,99,88,99,-1},
                {96,93,91,89,98,92,97,94,91,-1,-1,-1,95,88,91,-1}};

        insert_transcripts("0122","张守慧",tran[0],db);
        insert_transcripts("0121","张宇宸", tran[1],db);
        insert_transcripts("0120","张延恒", tran[2],db);
        insert_transcripts("0123","张棕浩", tran[3],db);
        insert_transcripts("0119","汪宇翔", tran[4],db);*/

    // in_Stranscripts("E:\\source\\Mywork\\Stranscripts.txt", db);



       /* insert_personal_info("0122","张守慧","B200301","2020","男",db);
        insert_personal_info("0121","张宇宸","B200301","2020","男",db);
        insert_personal_info("0123","张棕浩","B200301","2020","男",db);
        insert_personal_info("0120","张延恒","B200301","2020","男",db);
        insert_personal_info("0119","汪宇翔","B200301","2020","男",db);*/



       /* int[][] select = new int[5][N];
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<N;j++)
                if(tran[i][j] == -1)
                    select[i][j] = 0;
                else select[i][j] = 1;
        }

        insert_my_course("0122","张守慧",select[0],db);
        insert_my_course("0121","张宇宸", select[1],db);
        insert_my_course("0120","张延恒", select[2],db);
        insert_my_course("0123","张棕浩", select[3],db);
        insert_my_course("0119","汪宇翔", select[4],db);*/


        /*for(int i=0;i<N;i++)
        {
            insert_Course(course[i],course_id[i],teacher[i],db );
        }

        */

    /*
    void insert_Saccount(String sid, String spassword, String sname,SQLiteDatabase Db)
    {
        ContentValues values = new ContentValues();
        values.put("id", sid);values.put("password", spassword);values.put("name", sname);
        Db.insert(TB_NAME_1, null, values);

    }
    void insert_transcripts(String sid , String sname , int[] arr, SQLiteDatabase Db)
    {
        ContentValues values = new ContentValues();
        values.put("学号", sid); values.put("姓名",sname);
        for(int i=0;i<N;i++)
        {
            values.put(course[i], arr[i]);
        }
        Db.insert(TB_NAME_2,null,values);
    }
    void insert_personal_info(String sid, String sname, String sclass,String grade, String gender,SQLiteDatabase Db)
    {
        ContentValues values = new ContentValues();
        values.put("学号", sid);values.put("姓名", sname);values.put("班级",sclass);values.put("年级",grade) ;values.put("性别", gender);
        Db.insert(TB_NAME_3,null,values);
    }
    void insert_my_course(String sid, String sname, int[] select, SQLiteDatabase Db)
    {
        ContentValues values = new ContentValues();
        values.put("学号", sid);values.put("姓名",sname);
        for(int i=0;i<N;i++)
        {
            if(select[i] == 1)
                values.put(course[i], "已选");
            else values.put(course[i],"未选");
        }
        Db.insert(TB_NAME_4,null,values);
    }
    void insert_Course(String scourse, String sid,String teacher,SQLiteDatabase Db)
    {
        ContentValues values = new ContentValues();
        values.put("课程名称",scourse);
        values.put("课程编号",sid);
        values.put("授课老师",teacher);
        Cursor c = Db.rawQuery("select * from Smy_course ",null);
        c.moveToFirst();
        int count = 0;
        while(!c.isAfterLast()){
            @SuppressLint("Range") String res = c.getString(c.getColumnIndex(scourse));
            if(res.equals("已选")) count++;
            c.moveToNext();
        }
        //Cursor c = Db. query("Smy_course.txt",new String[]{scourse},"?=?",new String[]{scourse,"已选"},null,null,null,null);
        values.put("选课人数",count);
        Db.insert(TB_NAME_5,null,values);
        //Toast.makeText(getApplicationContext(),c.getCount(),Toast.LENGTH_SHORT).show();
    }*/
    /*
    void in_Saccount( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = context.getResources().getAssets().open("Saccount.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("id",arr[0]);values.put("password",arr[1]);
                values.put("name",arr[2]);
                Db.insert(TB_NAME_1,null,values);

            }
        }catch(IOException e){

        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /*void in_Smycourse( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Saccount.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("学号",arr[0]);values.put("姓名",arr[1]);
                for(int i=0;i<N;i++)
                {
                    values.put(course[i],arr[i+2]);
                }
                Db.insert(TB_NAME_4,null,values);

            }
        }catch(IOException e){

        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    void in_Stranscripts(String file,SQLiteDatabase Db){

        try {
            String str = null;
            FileReader input = new FileReader(file);
            BufferedReader br = new BufferedReader(input);
            while((str = br.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("学号", arr[0]); values.put("姓名",arr[1]);
                for(int i=0;i<N;i++)
                {
                    values.put(course[i], Integer.parseInt(arr[i+2]));
                }
                Db.insert(TB_NAME_2,null,values);
            }
        }catch(IOException e){

        }
    }

    void in_personalinfo(String file, SQLiteDatabase Db)
    {
        try {
            String str = null;
            FileReader input = new FileReader(file);
            BufferedReader br = new BufferedReader(input);
            while((str = br.readLine())!=null){
                String arr[] = str.split(" ");
                String sql = "insert into Spersonal_info(学号,姓名,班级,年级,性别) values(arr(0),arr(1),arr(2),arr(3),arr(4))";
                Db.execSQL(sql);

            }
        }catch(IOException e){

        }
    }

    void in_Smycourse( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = BaseActivity.mContext.getResources().getAssets().open("Saccount.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
           // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("学号",arr[0]);values.put("姓名",arr[1]);
                for(int i=0;i<N;i++)
                {
                    values.put(course[i],arr[i+2]);
                }
                Db.insert(TB_NAME_4,null,values);

            }
        }catch(IOException e){

        }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }
    }
    void in_Course(String file, SQLiteDatabase Db)
    {
        try {
            String str = null;
            FileReader input = new FileReader(file);
            BufferedReader br = new BufferedReader(input);
            while((str = br.readLine())!=null){
                String arr[] = str.split(" ");
                String sql = "insert into Course.txt(课程名称,课程编号,授课老师,课程学分,选课人数) values(arr(0),arr(1),arr(2),arr(3),arr(4),arr(5))";
                Db.execSQL(sql);
            }
        }catch(IOException e){

        }
    }*/


}



 /*
        ContentValues values = new ContentValues();
        values.put("id", "0122");
        values.put("password", "zsh");
        values.put("name", "张守慧");
        db.insert(TB_NAME_1,null, values);
        ContentValues values1 = new ContentValues();
        values1.put("id", "0121");
        values1.put("password","zyc");
        values1.put("name", "张宇宸");
        db.insert(TB_NAME_1, null, values1);
        values = new ContentValues();
        values.put("id", "0120");values.put("password", "zyh");
        values.put("name", "张延恒");
        db.insert(TB_NAME_1, null, values);*/


/*values = new ContentValues();
        values.put("学号", "0122"); values.put("姓名","张守慧");values.put("高等数学", 93);values.put("线性代数", 100);values.put("大学物理", 97);values.put("离散数学", 95);
        db.insert(TB_NAME_2,null,values);
        values = new ContentValues();
        values.put("学号", "0121"); values.put("姓名","张宇宸");values.put("高等数学", 90);values.put("线性代数", 95);values.put("大学物理", 95);values.put("离散数学",-1);
        db.insert(TB_NAME_2,null, values);

        values = new ContentValues();
        values.put("学号", "0120"); values.put("姓名","张延恒");values.put("高等数学", 90);values.put("线性代数", 93);values.put("大学物理", -1);values.put("离散数学", 95);
        db.insert(TB_NAME_2,null,values);*/


/*values = new ContentValues();
        values.put("学号", "0122");values.put("姓名", "张守慧");values.put("班级","B200301");values.put("年级","2020级") ;values.put("性别", "男");
        db.insert(TB_NAME_3,null,values);

        values = new ContentValues();
        values.put("学号", "0121");values.put("姓名", "张宇宸");values.put("班级","B200301");values.put("年级","2020级") ;values.put("性别", "男");
        db.insert(TB_NAME_3,null,values);

        values = new ContentValues();
        values.put("学号", "0120");values.put("姓名", "张延恒");values.put("班级","B200301");values.put("年级","2020级") ;values.put("性别", "男");
        db.insert(TB_NAME_3,null,values);*/
/*values = new ContentValues();
        values.put("学号", "0122");values.put("姓名","张守慧");values.put("高等数学","已选");values.put("线性代数","已选") ;values.put("大学物理", "已选");values.put("离散数学", "已选");
        db.insert(TB_NAME_4,null,values);
        values = new ContentValues();
        values.put("学号", "0121");values.put("姓名","张宇宸");values.put("高等数学","已选");values.put("线性代数","已选") ;values.put("大学物理", "已选");values.put("离散数学", "未选");
        db.insert(TB_NAME_4,null,values);
        values = new ContentValues();
        values.put("学号", "0120");values.put("姓名","张延恒");values.put("高等数学","已选");values.put("线性代数","已选") ;values.put("大学物理", "未选");values.put("离散数学", "已选");
        db.insert(TB_NAME_4,null,values);*/
