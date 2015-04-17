package org.flisolsaocarlos.flisolapp.provider;

import org.flisolsaocarlos.flisolapp.model.Lecture;

import java.util.List;

public interface LectureDao {

    public List<Lecture> findAll();

    public List<Lecture> findByEditionYear(int year);

    public Lecture findById(int id);

    public Lecture create(Lecture lecture);
}
