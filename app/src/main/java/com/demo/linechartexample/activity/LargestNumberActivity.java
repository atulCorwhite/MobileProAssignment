package com.demo.linechartexample.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.linechartexample.R;
import com.demo.linechartexample.baseView.BaseActivity;

public class LargestNumberActivity extends BaseActivity {
    int maxNumber;
   TextView maxNumberText;
   @Override
    protected void initActivity() {
        maxNumberText=findViewById(R.id.maxNumberText);
        int numberArray[]={30,10,50,89,799,145,899};
        maxNumber=numberArray[0];
        maxNumberCalculate(numberArray);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_base;
    }

    private void maxNumberCalculate(int[] numberArray) {
        for (int i=0;i<numberArray.length;i++){
            if(numberArray[i]>maxNumber){
                maxNumber=numberArray[i];
            }
        }
        maxNumberText.setText("Max Number is  "+   maxNumber);
    }

}