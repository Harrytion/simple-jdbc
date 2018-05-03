package com.jackrams;

import com.jackrams.domain.Person;

public class PersonDao extends AbstractBaseDaoImpl<Person> {


    @Override
    public Class<Person> getDomainClass() {
        return Person.class;
    }


}
