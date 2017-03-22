package com.example.administrator.xmldemo.service;

import com.example.administrator.xmldemo.bean.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SAXService {
    public List<Person> getPersons(InputStream inputStream) throws Exception {
        //得到 SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //得到SAX解析器
        SAXParser parser = factory.newSAXParser();
        PersonParse parse = new PersonParse();
        parser.parse(inputStream,parse);
        inputStream.close();
        //返回这个方法
        return parse.getPersons();
    }


    private final class PersonParse extends DefaultHandler {
        private List<Person> persons = null;
        private String tag = null;
        private Person person = null;

        public List<Person> getPersons(){
            return persons;
        }

        @Override   //开始解析文档 XML文件
        public void startDocument() throws SAXException {
            //创建一个工作空间
            persons = new ArrayList<>();
        }

        @Override   //开始检查元素
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //判断元素节点是否等于person
            if("person".equals(localName)){
                person = new Person();
                person.setId(Integer.parseInt(attributes.getValue(0)));
            }
            tag = localName;
        }

        @Override //结束检查元素
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if("person".equals(localName)){
                persons.add(person);
                person = null;
            }
            tag = null;
        }

        @Override //检查空格
        public void characters(char[] ch, int start, int length) throws SAXException {
            if(tag !=null){
                String data = new String(ch,start,length);//获取文本节点的数据
                if("name".equals(tag)){
                    person.setName(data);
                }else if("age".equals(tag)){
                    person.setAge(Short.parseShort(data));
                }
            }
        }
    }
}
