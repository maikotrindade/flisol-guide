package org.flisolsaocarlos.flisolapp.model;

import java.util.List;

public class DatabaseContainer {

    private List<Edition> editions;

    public List<Edition> getEditions() {
        return this.editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    @Override
    public String toString() {
        return "FlisolContainer{"
                + "editions=" + editions
                + '}';
    }
}
