package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="NonSecondaryCourseInfo")
public class NonSecondaryCourseInfo extends BaseInfo
                                    implements java.io.Serializable
{
    public NonSecondaryCourseInfo(){}
    public NonSecondaryCourseInfo(int id, String name)
    {
        super.setId(id);
        super.setName(name);
    }
}
