package com.edu.tpc.domain;

/**
 * @author Jaikishan
 * Date: 18/10/11
*/

import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name="SecondaryStreamsInfo")
public class SecondaryStreamInfo extends BaseInfo
                                 implements java.io.Serializable
{

}
