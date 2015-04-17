package org.flisolsaocarlos.flisolapp.service;

import org.flisolsaocarlos.flisolapp.model.Software;
import org.flisolsaocarlos.flisolapp.provider.impl.SoftwareDaoImpl;

import java.util.List;

public class SoftwareService {

    static final String TAG = SoftwareService.class.getName();
    private SoftwareDaoImpl softwareDao;

    public SoftwareService() {
        softwareDao = new SoftwareDaoImpl();
    }

    public List<Software> getSoftwares() {
        return softwareDao.findAll();
    }

    public List<Software> getByYear(int year) {
        return softwareDao.findByYear(year);
    }
}
