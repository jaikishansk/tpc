package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="NonSecondaryStreamInfo")
public class NonSecondaryStreamInfo extends BaseInfo
                                    implements java.io.Serializable
{
    public NonSecondaryStreamInfo(){}
    public NonSecondaryStreamInfo(int id, String name)
    {
        super.setId(id);
        super.setName(name);
    }
}
