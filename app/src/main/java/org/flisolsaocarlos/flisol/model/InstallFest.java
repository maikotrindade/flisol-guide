package org.flisolsaocarlos.flisol.model;

import java.util.List;

public class InstallFest {

    private int id;
    private List<Software> softwares;

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftware(List<Software> softwares) {
        this.softwares = softwares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
