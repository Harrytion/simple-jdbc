package com.jackrams;

import javax.persistence.*;

@Entity
@Table(name="test")
public class InfoBean {
    @Id
    @Column(name="id1")
    private String id1;
    @Column(name = "id2")
    private String id2;

    private String name;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName() {
        return name;
    }
    @Column(name = "name")
    public void setName(String name) {
        this.name = name;
    }
}
