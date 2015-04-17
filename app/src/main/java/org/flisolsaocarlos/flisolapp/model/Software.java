package org.flisolsaocarlos.flisolapp.model;

public class Software {

    private int id;
    private String name;
    private String website;
    private String notes;
    private String category;
    private String version;

    public Software() {
    }

    public Software(final String name, final String website, final String notes, final String category, final String version) {
        this.name = name;
        this.website = website;
        this.notes = notes;
        this.category = category;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Software{" +
                "name='" + name + '\'' +
                '}';
    }
}
