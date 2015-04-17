package org.flisolsaocarlos.flisolapp.provider;

import org.flisolsaocarlos.flisolapp.model.Software;

import java.util.List;

public interface SoftwareDao {

    public List<Software> findAll();

    public List<Software> findByYear(int year);

}
