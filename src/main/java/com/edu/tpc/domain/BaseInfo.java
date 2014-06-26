package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 */

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseInfo
{
    @Id
    private int id ;
    @Column private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
