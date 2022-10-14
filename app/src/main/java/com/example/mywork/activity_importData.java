package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class activity_importData extends AppCompatActivity {

    private SQLiteDatabase myDb;
    private MyDAO myDAO;
    //private DBHelper dbHelper;
    //public   String[] course  = new String[100];
    //public static  int N = 0;
    public static final String[] course = {"高等数学", "线性代数","大学物理","离散数学",
            "数据结构", "计算机组成原理","算法分析与设计","概率论与数理统计",
            "java程序设计","羽毛球","网球","乒乓球",
            "飞盘","AI视觉概论","单片机原理及接口技术","通信原理C"};
    public static final int N = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data);
        Intent intent = getIntent();
        setTitle("数据导入");

        myDAO = new MyDAO(this);
        //dbHelper = new DBHelper(this, "info_System.db",null, 24);   //打开数据库
        myDb = myDAO.get_Db();


        myDb.execSQL("DELETE FROM " + "Saccount");
        myDb.execSQL("DELETE FROM " + "Stranscripts");
        myDb.execSQL("DELETE FROM " + "Spersonal_info");
        myDb.execSQL("DELETE FROM " + "Smy_course");
        myDb.execSQL("DELETE FROM " + "Course");
        myDb.execSQL("DELETE FROM " + "Taccount");
        myDb.execSQL("DELETE FROM " + "Tpersonal_info");
        in_Saccount(myDb);

        in_Stranscript(myDb);
        in_Smycourse(myDb);
        in_Course(myDb);
        in_Spersonal_info(myDb);
        in_Taccount(myDb);
        in_Tpersonal_info(myDb);

        Toast.makeText(getApplicationContext(),"数据更新成功",Toast.LENGTH_SHORT).show();

    }
    void in_Saccount( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Saccount.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("id",arr[0]);values.put("password",arr[1]);
                values.put("name",arr[2]);
                Db.insert("Saccount",null,values);

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
    void in_Stranscript(SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Stranscript.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("学号",arr[0]);values.put("姓名",arr[1]);
                for(int i=0;i<N;i++)
                    values.put(course[i],arr[i+2]);
                Db.insert("Stranscripts",null,values);

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
    void in_Smycourse( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Smy_course.txt");
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
                    if(arr[i+2].equals("1"))
                        values.put(course[i],"已选");
                    else values.put(course[i],"未选");
                }
                Db.insert("Smy_course",null,values);

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
    void in_Spersonal_info( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Spersonal_info.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("学号",arr[0]);values.put("姓名",arr[1]);
                values.put("班级",arr[2]);values.put("年级", arr[3]);
                values.put("性别", arr[4]);values.put("学院",arr[5]);
                values.put("专业", arr[6]);values.put("民族",arr[7]);
                Db.insert("Spersonal_info",null,values);

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

    void in_Course( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Course.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            int cnt = 0;
            // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){

                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                String key[] ={"课程名称","课程编号","授课老师","课程学分","考察方式","课程性质","总学时","课程类别","开课学院"};
                for(int i=0;i<9;i++){
                    values.put(key[i],arr[i]);
                }
                //course[cnt] = arr[0];
                cnt++;
                Cursor c = Db.rawQuery("select * from Smy_course ",null);
                c.moveToFirst();
                int count = 0;
                while(!c.isAfterLast()){
                    @SuppressLint("Range") String res = c.getString(c.getColumnIndex(arr[0]));
                    if(res.equals("已选")) count++;
                    c.moveToNext();
                }
               // N = cnt;
                values.put("选课人数",count);
                Db.insert("Course",null,values);

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
    void in_Taccount( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Taccount.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("id",arr[0]);values.put("password",arr[1]);
                values.put("name",arr[2]);
                Db.insert("Taccount",null,values);

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

    void in_Tpersonal_info( SQLiteDatabase Db)
    {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getResources().getAssets().open("Tpersonal_info.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            // FileReader input = new FileReader(file);
            //BufferedReader br = new BufferedReader(input);
            while((str = bufferedReader.readLine())!=null){
                String arr[] = str.split(" ");
                ContentValues values = new ContentValues();
                values.put("工号",arr[0]);values.put("姓名",arr[1]);
                values.put("学院",arr[2]);values.put("教授课程", arr[3]);
                values.put("职称", arr[4]);
                Db.insert("Tpersonal_info",null,values);

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
}
