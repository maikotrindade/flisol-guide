package org.flisolsaocarlos.flisol.model;

public class Supporter {

    int id;
    private String name;
    private String website;
    private String image;
    private String businessPackage;

    public Supporter(String name, String website, String image, String businessPackage) {
        this.name = name;
        this.website = website;
        this.image = image;
        this.businessPackage = businessPackage;
    }

    public Supporter() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusinessPackage() {
        return businessPackage;
    }

    public void setBusinessPackage(String businessPackage) {
        this.businessPackage = businessPackage;
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "name='" + name + '\'' +
                '}';
    }

}
