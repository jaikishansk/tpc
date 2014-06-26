package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 */

import java.util.*;

import com.edu.tpc.dao.CoursesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coursesService")
public class CoursesServiceImpl implements CoursesService
{
    private CoursesDAO coursesDAO;

    public CoursesDAO getCoursesDAO() {
        return coursesDAO;
    }

    @Autowired
    public void setCoursesDAO(CoursesDAO coursesDAO) {
        this.coursesDAO = coursesDAO;
    }

    @Override
    public Map findSecondaryCourses()
    {
        return coursesDAO.findSecondaryCourses();
    }
    @Override
    public Map findSecondaryStreams()
    {
        return coursesDAO.findSecondaryStreams();
    }
    @Override
    public Map findUGCourses(int orgId)
    {
        return coursesDAO.findUGCourses(orgId);
    }
    @Override
    public Map findPGCourses(int orgId)
    {
        return coursesDAO.findPGCourses(orgId);
    }
    @Override
    public Map findUGStreams(int orgId)
    {
        return coursesDAO.findUGStreams(orgId);
    }
    @Override
    public Map findPGStreams(int orgId)
    {
        return coursesDAO.findPGStreams(orgId);
    }
    @Override
    public String findCourseName(int courseId)
    {
        return coursesDAO.findCourseName(courseId);
    }
    @Override
    public String findStreamName(int streamId)
    {
        return coursesDAO.findStreamName(streamId);
    }
}
