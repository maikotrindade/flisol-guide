package org.flisolsaocarlos.flisolapp.provider;


import org.flisolsaocarlos.flisolapp.model.Supporter;

import java.util.List;

public interface SupporterDao {

    public Supporter findById(int id);

    public List<Supporter> findByEditionYear(int year);
}
