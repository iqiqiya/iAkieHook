package com.iakie.iakiehook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
/**
 * Author: iqiqiya
 * Date: 2019/11/9
 * Time: 19:20
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isModuleActive()){
            Toast.makeText(this,"模块未激活",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "模块已激活",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isModuleActive(){
        return false;
    }
}