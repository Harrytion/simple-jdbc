package com.jackrams.test;

import com.jackrams.test.config.Configurations;
import com.jackrams.test.dao.PersonDao;
import com.jackrams.test.dao.UserDao;
import com.jackrams.test.domain1.Person;
import com.jackrams.test.domain2.User;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(Configurations.class);
        PersonDao personDao = context.getBean(PersonDao.class);
        UserDao userDao = context.getBean(UserDao.class);
        Person person =new Person();
        person.setAge(18);
        person.setDes("P--哈哈，我是注释");
        person.setImg(FileUtils.readFileToByteArray(new File("/home/hadoop/imgs.png")));
        person.setuId(UUID.randomUUID().toString().replaceAll("-",""));
        person.setUserName("__P我是姓名");
        User user =new User();

        user.setAge(18);
        user.setDes("UUU--哈哈，我是注释");
        user.setImg(FileUtils.readFileToByteArray(new File("/home/hadoop/imgs.png")));
        user.setuId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUserName("_UUU我是姓名UUU");

        userDao.insert(user);

        personDao.insert(person);
    }
}
