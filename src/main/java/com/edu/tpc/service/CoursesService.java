package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 * Date: 18/10/11
 */
import java.util.*;

public interface CoursesService
{
    public Map findSecondaryCourses();
    public Map findSecondaryStreams();
    public Map findUGCourses(int orgId);
    public Map findPGCourses(int orgId);
    public Map findUGStreams(int orgId);
    public Map findPGStreams(int orgId);
    public String findCourseName(int courseId);
    public String findStreamName(int streamId);
}
