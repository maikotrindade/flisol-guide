package org.flisolsaocarlos.flisolapp.service;

import android.content.Context;

import org.flisolsaocarlos.flisolapp.model.Supporter;
import org.flisolsaocarlos.flisolapp.provider.impl.SupporterDaoImpl;

import java.util.Collections;
import java.util.List;

public class SupporterService {

    static final String TAG = SupporterService.class.getName();
    private SupporterDaoImpl supporterDaoImpl;

    public SupporterService(Context context) {
        supporterDaoImpl = new SupporterDaoImpl();
    }

    public Supporter getById(int id) {
        return supporterDaoImpl.findById(id);
    }

    public List<Supporter> getByYearOrderedByPackage(Integer year) {
        List<Supporter> supportersByYear = supporterDaoImpl.findByEditionYear(year);
        Collections.sort(supportersByYear);
        return supportersByYear;
    }




}
