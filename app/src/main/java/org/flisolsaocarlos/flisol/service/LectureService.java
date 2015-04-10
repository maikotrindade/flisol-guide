package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.provider.impl.LectureDaoImpl;

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

    public Edition getEditionByYear(int year) {
        EditionService editionService = new EditionService();
        return editionService.getByYear(year);
    }

    public HostingPlace getHostingPlaceByEdition(Edition edition) {
        EditionService editionService = new EditionService();
        return editionService.getHostingPlaceByEdition(edition);
    }

    public List<Lecture> getByYear(int year) {
        return lectureDao.findByEditionYear(year);
    }

    public List<Lecture> getByYearAndRoom(int year, String room) {
        return lectureDao.findByEditionYearAndRoom(year, room);
    }

    public List<String> getYears() {
        return lectureDao.findYears();
    }
}
