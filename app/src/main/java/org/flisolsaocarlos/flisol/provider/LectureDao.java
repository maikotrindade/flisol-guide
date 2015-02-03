package org.flisolsaocarlos.flisol.provider;

import org.flisolsaocarlos.flisol.model.Lecture;

import java.util.List;

public interface LectureDao {

    public List<Lecture> findAll();

    public List<Lecture> findByEditionYear(int year);

    public Lecture findById(int id);

    public Lecture create(Lecture lecture);
}
