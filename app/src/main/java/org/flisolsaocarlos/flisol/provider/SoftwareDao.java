package org.flisolsaocarlos.flisol.provider;

import org.flisolsaocarlos.flisol.model.Software;

import java.util.List;

public interface SoftwareDao {

    public List<Software> findAll();

    public List<Software> findByYear(int year);

}
