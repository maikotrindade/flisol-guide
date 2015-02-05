package org.flisolsaocarlos.flisol.service;

import android.content.Context;

import org.flisolsaocarlos.flisol.model.Supporter;
import org.flisolsaocarlos.flisol.provider.impl.SupporterDaoImpl;

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

    public List<Supporter> getByYear(Integer year) {
        return supporterDaoImpl.findByEditionYear(year);
    }
}
