package org.flisolsaocarlos.flisolapp.model;

import org.flisolsaocarlos.flisolapp.R;
import org.flisolsaocarlos.flisolapp.service.ApplicationService;

public class Supporter implements Comparable<Supporter> {

    int id;
    private String name;
    private String websiteTitle;
    private String website;
    private String image;
    private BusinessPackage businessPackage;

    public enum BusinessPackage {

        DIAMOND(1, R.string.diamond, "ic_diamond.png"),
        GOLD(2, R.string.gold, "ic_gold_medal.png"),
        SILVER(3, R.string.silver, "ic_silver_medal.png"),
        BRONZE(4, R.string.bronze, "ic_bronze_medal.png");

        final private int resourceId;
        final private int id;
        final private String icon;

        private BusinessPackage(int id, int resourceId, String icon) {
            this.id = id;
            this.resourceId = resourceId;
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return ApplicationService.getInstance().getString(resourceId);
        }

        public String getIcon() {
            return icon;
        }
    }

    public Supporter(String name, String website, String websiteTitle, String image, BusinessPackage businessPackage) {
        this.name = name;
        this.website = website;
        this.websiteTitle = websiteTitle;
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

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle;
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
    public int compareTo(Supporter supporter) {
        if (supporter.getBusinessPackage().getId() == this.businessPackage.getId()) {
            return 0;
        }
        else {
            return  this.businessPackage.getId() - supporter.getBusinessPackage().getId();
        }
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "name='" + name + '\'' +
                '}';
    }

}
