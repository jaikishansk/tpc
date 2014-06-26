package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="CoursesStream")
public class CoursesStream implements java.io.Serializable
{
     @Id
    private CoursesStreamPK primaryKey ;

    public CoursesStreamPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CoursesStreamPK primaryKey) {
        this.primaryKey = primaryKey;
    }

}
