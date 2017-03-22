package com.example.administrator.xmldemo.service;

import android.util.Xml;
import com.example.administrator.xmldemo.bean.Person;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 * 采用pull解析XML文件内容
 */

public class PullService {

    Person person = null;

    public List<Person> getPersons(InputStream inputStream) throws Exception {
        List<Person> persons = null;

        //得到pull解析器
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "UTF-8");

        //产生事件
        int eventType = parser.getEventType();

        //只要不是文档结束事件就循环推进
        while (eventType!=XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT://开始文档事件
                    persons = new ArrayList<>();
                    break;

                case XmlPullParser.START_TAG://开始元素事件
                    //获取解析器当前指向的元素的名称
                    String name = parser.getName();
                    //如果是person节点
                    if("person".equals(name)){
                        person = new Person();
                        person.setId(Integer.parseInt(parser.getAttributeValue(0)));
                    }
                    if(person!=null){
                        if("name".equals(name)){
                            //获取解析器当前指向元素的下一个文本的节点的值
                            person.setName(parser.nextText());
                        }

                        if("age".equals(name)){
                            person.setAge(Short.parseShort(parser.nextText()));
                        }
                    }
                    break;

                case XmlPullParser.END_TAG://结束元素事件
                    if("person".equals(parser.getName())){
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            //进入下一个元素并触发相应事件
            eventType = parser.next();
        }
        return persons;
    }
}
