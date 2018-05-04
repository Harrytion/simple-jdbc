package com.jackrams.test;

import com.jackrams.domain.Example;
import com.jackrams.domain.SelectExample;
import com.jackrams.helpper.PropertyHelpper;
import com.jackrams.test.config.Configurations;
import com.jackrams.test.dao.PersonDao;
import com.jackrams.test.dao.UserDao;
import com.jackrams.test.domain1.Person;
import com.jackrams.test.domain2.User;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(Configurations.class);
        PersonDao personDao = context.getBean(PersonDao.class);
        UserDao userDao = context.getBean(UserDao.class);
        Person person =new Person();
        person.setAge(18);
        person.setDes("P--哈哈，我是注释");
        person.setImg(FileUtils.readFileToByteArray(new File("D:\\data\\img\\photo1_1.jpg")));
        person.setuId(UUID.randomUUID().toString().replaceAll("-",""));
        person.setUserName("__P我是姓名");
        User user =new User();

        user.setAge(18);
        user.setDes("UUU--哈哈，我是注释");
        user.setImg(FileUtils.readFileToByteArray(new File("D:\\data\\img\\photo1_10.jpg")));
        user.setuId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUserName("_UUU我是姓名UUU");

        userDao.insert(user);

        personDao.insert(person);


        SelectExample example = SelectExample.custom().addPage(1).addPageSize(5);
         example =((SelectExample) example.andLike(PropertyHelpper.getPropertyByField(User.class, "des"), "UUU%").end()).addPage(1).addPageSize(5)
                 .addAsc(PropertyHelpper.getPropertyByField(User.class,"uId"));
        List<User> users = userDao.selectListByExample(example);
        for(User usert : users){
            String s = usert.getuId();
            System.out.println(s);

        }


    }

}
