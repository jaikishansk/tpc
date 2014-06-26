/*
 * Date: 4/5/12 
 */

package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 */

import java.util.* ;

public class CourseStreamInfo
{
    private static final HashMap courses = new HashMap();
    private static final HashMap streams = new HashMap();
    
    static { 
        courses.put(new Integer(100),"BSc");
        courses.put(new Integer(101),"BE");
        courses.put(new Integer(102),"BTech");
        courses.put(new Integer(103),"BCA"); 
        
        courses.put(new Integer(200),"MSc");
        courses.put(new Integer(201),"ME");
        courses.put(new Integer(202),"MTech");
        courses.put(new Integer(203),"MCA");

        streams.put(new Integer(50),"CSE");
        streams.put(new Integer(51),"IT");
        streams.put(new Integer(52),"Mech");
        streams.put(new Integer(53),"ETC");
        streams.put(new Integer(54),"Elect");
    }
    
    public static String getCourse(int courseId) { 
        return (String)courses.get(new Integer(courseId));
    }
    public static String getStream(int streamId) { 
        return (String)streams.get(new Integer(streamId));
    }
}
