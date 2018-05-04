package com.jackrams;

import com.jackrams.domain.Person;
import com.jackrams.scanner.DefaultScanner;
import com.jackrams.scanner.Scanner;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

public class TestDao {
    @Before
    public void init(){
        Scanner scanner = new DefaultScanner();
        scanner.doScan("com");
    }

    @Test
    public void testInsert()throws Exception{
        Person person =new Person();
        person.setAge(18);
        person.setDes("哈哈，我是注释");
        person.setImg(FileUtils.readFileToByteArray(new File("/home/hadoop/imgs.png")));
        person.setuId(UUID.randomUUID().toString().replaceAll("-",""));
        person.setUserName("我是姓名");

        new PersonDao().insert(person);

    }

    @Test
    public void testGenType(){
        Class<PersonDao> personDaoClass = PersonDao.class;
        ParameterizedType genericSuperclass = (ParameterizedType)personDaoClass.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        System.out.println(((Class)actualTypeArguments[0]).getPackage().getName());
    }

    @Test
    public void testField() throws Exception{
        PersonDao personDao = new PersonDao();
        Class<AbstractBaseDaoImpl> personDaoClass = AbstractBaseDaoImpl.class;
        Field domainClass = personDaoClass.getDeclaredField("domainClass");
        domainClass.setAccessible(true);
        domainClass.set(personDao,Person.class);
        System.out.print(domainClass.get(personDao));
    }

}
