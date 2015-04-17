package org.flisolsaocarlos.flisolapp.provider;

public class DatabaseContract {

    public interface Tables {

        String EDITION = "edition";
        String LECTURE = "lecture";
        String COURSE = "course";
        String SUPPORTER = "supporter";
        String INSTALL_FEST = "install_fest";
        String SOFTWARE = "software";
        String HOSTING_PLACE = "hosting_place";
        String ADDRESS = "address";
    }

    public interface EditionColumns {
        String ID = "_id";
        String YEAR = "year";
    }

    public interface LectureColumns {
        String ID = "_id";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String LECTURER = "lecturer";
        String FIELD = "field";
        String SCHEDULE_BEGIN = "schedule_begin";
        String SCHEDULE_END = "schedule_end";
        String ROOM = "room";
        String EDITION = "edition_id";
    }

    public interface CourseColumns {
        String ID = "_id";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String LECTURER = "lecturer";
        String FIELD = "field";
        String SCHEDULE_BEGIN = "schedule_begin";
        String SCHEDULE_END = "schedule_end";
        String ROOM = "room";
        String VACANCIES = "vacancies";
        String EDITION = "edition_id";
    }

    public interface SupporterColumns {
        String ID = "_id";
        String NAME = "name";
        String WEBSITE_TITLE = "website_title";
        String WEBSITE = "website";
        String IMAGE = "image";
        String BUSINESS_PACKAGE = "business_package";
        String EDITION = "edition_id";
    }

    public interface InstallFestColumns {
        String ID = "_id";
        String EDITION = "edition_id";
    }

    public interface SoftwareColumns {
        String ID = "_id";
        String NAME = "name";
        String WEBSITE = "website";
        String NOTES = "notes";
        String CATEGORY = "category";
        String VERSION = "version";
        String INSTALL_FEST = "install_fest_id";
    }

    public interface HostingPlaceColumns {
        String ID = "_id";
        String NAME = "name";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
        String EDITION = "edition_id";
    }

    public interface AddressColumns {
        String ID = "_id";
        String STREET = "street";
        String NUMBER = "number";
        String DISTRICT = "district";
        String CITY = "city";
        String STATE = "state";
        String HOSTING_PLACE = "hosting_place_id";
    }
}
