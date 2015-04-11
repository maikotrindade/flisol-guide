package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Course;
import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.provider.impl.CourseDaoImpl;

import java.util.List;

public class CourseService {

    static final String TAG = CourseService.class.getName();
    private CourseDaoImpl courseDao;

    public CourseService() {
        courseDao = new CourseDaoImpl();
    }

    public Course getById(int id) {
        return courseDao.findById(id);
    }

    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    public List<Course> getByYear(int year) {
        return courseDao.findByEditionYear(year);
    }

    public List<String> getYears() {
        return courseDao.findYears();
    }

    public Edition getEditionByYear(int year) {
        EditionService editionService = new EditionService();
        return editionService.getByYear(year);
    }

    public List<Course> getByYearAndRoom(int year, String room) {
        return courseDao.findByYearAndRoom(year, room);
    }

    public HostingPlace getHostingPlaceByEdition(Edition edition) {
        EditionService editionService = new EditionService();
        return editionService.getHostingPlaceByEdition(edition);
    }
}
