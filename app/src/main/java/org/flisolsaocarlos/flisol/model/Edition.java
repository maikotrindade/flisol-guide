package org.flisolsaocarlos.flisol.model;

import java.util.List;

public class Edition {

    private int id;
    private List<Course> courses;
    private List<Lecture> lectures;
    private List<Supporter> supporters;
    private InstallFest installFest;
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InstallFest getInstallFest() {
        return installFest;
    }

    public void setInstallFest(InstallFest installFest) {
        this.installFest = installFest;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Supporter> getSupporters() {
        return supporters;
    }

    public void setSupporters(List<Supporter> supporters) {
        this.supporters = supporters;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Edition{"
                + "year=" + year
                + '}';
    }
}
