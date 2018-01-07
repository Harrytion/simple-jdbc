package com.jackrams;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table()
public class InfoBean {
    @Id
    private String id1;
    @Id private String id2;
}
