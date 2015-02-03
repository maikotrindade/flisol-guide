package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.provider.LectureDaoImpl;

import java.util.List;

public class LectureService {

    static final String TAG = LectureService.class.getName();
    private LectureDaoImpl lectureDao;

    public LectureService() {
        lectureDao = new LectureDaoImpl();
    }

    public Lecture getById(int id) {
        return lectureDao.findById(id);
    }

    public List<Lecture> getLectures() {
        return lectureDao.findAll();
    }

    public List<Lecture> getByYear(int year) {
        return lectureDao.findByEditionYear(year);
    }
}
