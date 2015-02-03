package org.flisolsaocarlos.flisol.provider;


import org.flisolsaocarlos.flisol.model.Supporter;

import java.util.List;

public interface SupporterDao {

    public Supporter findById(int id);

    public List<Supporter> findByEditionYear(int year);
}
