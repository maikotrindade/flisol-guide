package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Software;
import org.flisolsaocarlos.flisol.provider.impl.SoftwareDaoImpl;

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
