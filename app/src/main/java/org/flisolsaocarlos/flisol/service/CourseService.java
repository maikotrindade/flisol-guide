package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Course;
import org.flisolsaocarlos.flisol.provider.impl.CourseDaoImpl;

import java.util.List;

public class CourseService {

    static final String TAG = CourseService.class.getName();
    private CourseDaoImpl courseDaoImpl;

    public CourseService() {
        courseDaoImpl = new CourseDaoImpl();
    }

    public Course getById(int id) {
        return courseDaoImpl.findById(id);
    }

    public List<Course> getcourses() {
        return courseDaoImpl.findAll();
    }

    public List<Course> getByYear(int year) {
        return courseDaoImpl.findByEditionYear(year);
    }
}
