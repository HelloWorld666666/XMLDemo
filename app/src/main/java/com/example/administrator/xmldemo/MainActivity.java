package com.example.administrator.xmldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.xmldemo.bean.Person;
import com.example.administrator.xmldemo.service.DOMService;
import com.example.administrator.xmldemo.service.PullService;
import com.example.administrator.xmldemo.service.SAXService;

import java.io.IOException;
import java.io.InputStream;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startXML(View view){
//        DOMService domService = new DOMService();
//        SAXService saxService = new SAXService();
          PullService pullService = new PullService();
        try {
            InputStream inputStream = getAssets().open("users.xml");
            List<Person> persons = pullService.getPersons(inputStream);
            //foreach()增强for循环 前面的参数是要返回的对象 后面的参数是要遍历的集合
            for(Person person:persons){
                Log.e("TAG",person.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
