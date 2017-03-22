package com.example.administrator.xmldemo.service;
import com.example.administrator.xmldemo.bean.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Administrator on 2017/3/22.
 * 采用DOM解析XML内容
 */

public class DOMService  {
    public List<Person> getPersons(InputStream inputStream) throws Exception{

        List<Person> persons = new ArrayList<Person>();

        //获取DOM解析工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //获取解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        //将解析的内容（流的形式）放入内存 通过返回值Document来描述解析结果
        Document document = builder.parse(inputStream);
        //获取根元素<persons>
        Element root = document.getDocumentElement();
        //得到所有person节点的集合
        NodeList personNodes = root.getElementsByTagName("person");
        for(int i = 0;i<personNodes.getLength();i++){
            Person person = new Person();
            //取得person的元素节点
            Element personElement = (Element) personNodes.item(i);
            //取得属性值 并且设置id
            person.setId(Integer.parseInt(personElement.getAttribute("id")));
            //获取person的子节点集合
            NodeList personChilds = personElement.getChildNodes();


            for(int j = 0;j<personChilds.getLength();j++){

                //判读当前节点是否是元素类型节点
                if(personChilds.item(j).getNodeType() == Node.ELEMENT_NODE){
                   Element childElement = (Element) personChilds.item(j);
                    if("name".equals(childElement.getNodeName())){
                        //获取孙节点的值 李雷
                        person.setName(childElement.getFirstChild().getNodeValue());
                    }else if("age".equals(childElement.getNodeName())){
                        person.setAge(Short.parseShort(childElement.getFirstChild().getNodeValue()));
                    }
                }
            }
           persons.add(person);
        }
        return persons;
    }
}
