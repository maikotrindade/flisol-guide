package org.flisolsaocarlos.flisol.model;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.service.ApplicationService;

public class Supporter {

    int id;
    private String name;
    private String website;
    private String image;
    private BusinessPackage businessPackage;

    public enum BusinessPackage {

        DIAMOND(R.string.diamond),
        GOLD(R.string.gold),
        SILVER(R.string.silver),
        BRONZE(R.string.bronze);

        final private int resourceId;

        private BusinessPackage(int resourceId) {
            this.resourceId = resourceId;
        }

        @Override
        public String toString() {
            return ApplicationService.getInstance().getString(resourceId);
        }
    }

    public Supporter(String name, String website, String image, BusinessPackage businessPackage) {
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

    public BusinessPackage getBusinessPackage() {
        return businessPackage;
    }

    public void setBusinessPackage(BusinessPackage businessPackage) {
        this.businessPackage = businessPackage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "name='" + name + '\'' +
                '}';
    }

}
