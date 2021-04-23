package com.demo.linechartexample.baseView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.demo.linechartexample.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initActivity();
    }

    protected abstract void initActivity();

    protected abstract int getContentView();
}