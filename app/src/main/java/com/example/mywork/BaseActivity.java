package com.example.mywork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity baseActivity;//传递给非activity的类使用
    public static Context mContext;//传递给非activity的类使用
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity=this;//传递给非activity的类使用
        mContext=this.getBaseContext();//传递给非activity的类使用
    }

}