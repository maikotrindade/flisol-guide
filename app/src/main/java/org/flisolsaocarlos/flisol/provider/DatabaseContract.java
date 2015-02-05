package org.flisolsaocarlos.flisol.provider;

public class DatabaseContract {

    public interface Tables{
        
        String EDITION = "edition";
        String LECTURE = "lecture";
        String COURSE = "course";
        String SUPPORTER = "supporter";
        String INSTALL_FEST = "install_fest";
        String SOFTWARE = "software";
        String OPERATIONAL_SYSTEM = "operational_system";
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
}
