package com.jackrams.test.domain1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testI")
public class Person {
    @Id
    @Column(name="u_id")
    private String uId;
    @Column(name = "age")
    private int age ;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "des")
    private String des;
    @Column(name = "img")
    private byte [] img;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getuId() {
        return uId;
    }

    public String getUserName() {
        return userName;
    }
}
