package org.flisolsaocarlos.flisol.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.flisolsaocarlos.flisol.model.Address;
import org.flisolsaocarlos.flisol.model.Course;
import org.flisolsaocarlos.flisol.model.DatabaseContainer;
import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.model.InstallFest;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.model.Software;
import org.flisolsaocarlos.flisol.model.Supporter;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.AddressColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.CourseColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.EditionColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.HostingPlaceColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.InstallFestColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.LectureColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.SoftwareColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.SupporterColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.Tables;
import org.flisolsaocarlos.flisol.util.JSONHandler;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "flisol.db";
    private static final String TAG = DatabaseHelper.class.getName();
    private Context context;

    private static final String EDITION_CREATE = "CREATE TABLE " +
            Tables.EDITION
            + "(" + EditionColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EditionColumns.YEAR + " INTEGER NOT NULL ) ";

    private static final String LECTURE_CREATE = "CREATE TABLE " +
            Tables.LECTURE
            + "(" + LectureColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LectureColumns.TITLE + " TEXT NOT NULL, "
            + LectureColumns.DESCRIPTION + " TEXT NOT NULL, "
            + LectureColumns.LECTURER + " TEXT NOT NULL, "
            + LectureColumns.FIELD + " TEXT NOT NULL, "
            + LectureColumns.SCHEDULE_BEGIN + " TEXT NOT NULL, "
            + LectureColumns.SCHEDULE_END + " TEXT NOT NULL, "
            + LectureColumns.ROOM + " TEXT NOT NULL, "
            + LectureColumns.EDITION + " INTEGER REFERENCES " + Tables.EDITION + "( " + EditionColumns.ID + " ) ON UPDATE CASCADE)";

    private static final String COURSE_CREATE = "CREATE TABLE " +
            Tables.COURSE
            + "(" + CourseColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CourseColumns.TITLE + " TEXT NOT NULL, "
            + CourseColumns.DESCRIPTION + " TEXT NOT NULL, "
            + CourseColumns.LECTURER + " TEXT NOT NULL, "
            + CourseColumns.FIELD + " TEXT NOT NULL, "
            + CourseColumns.SCHEDULE_BEGIN + " TEXT NOT NULL, "
            + CourseColumns.SCHEDULE_END + " TEXT NOT NULL, "
            + CourseColumns.ROOM + " TEXT NOT NULL, "
            + CourseColumns.VACANCIES + " INTEGER NOT NULL, "
            + CourseColumns.EDITION + " INTEGER REFERENCES " + Tables.EDITION + "( " + EditionColumns.ID + " ) ON UPDATE CASCADE)";

    private static final String SUPPORTER_CREATE = "CREATE TABLE " +
            Tables.SUPPORTER
            + "(" + SupporterColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SupporterColumns.NAME + " TEXT NOT NULL, "
            + SupporterColumns.IMAGE + " TEXT NOT NULL, "
            + SupporterColumns.BUSINESS_PACKAGE + " TEXT NOT NULL, "
            + SupporterColumns.WEBSITE + " INTEGER NOT NULL, "
            + SupporterColumns.EDITION + " INTEGER REFERENCES " + Tables.EDITION + "( " + EditionColumns.ID + " ) ON UPDATE CASCADE)";

    private static final String HOSTING_PLACE_CREATE = "CREATE TABLE " +
            Tables.HOSTING_PLACE
            + "(" + HostingPlaceColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HostingPlaceColumns.NAME + " TEXT NOT NULL, "
            + HostingPlaceColumns.LATITUDE + " REAL NOT NULL, "
            + HostingPlaceColumns.LONGITUDE + " REAL NOT NULL, "
            + HostingPlaceColumns.EDITION + " INTEGER REFERENCES " + Tables.EDITION + "( " + EditionColumns.ID + " ) ON UPDATE CASCADE)";

    private static final String ADDRESS_CREATE = "CREATE TABLE " +
            Tables.ADDRESS
            + "(" + AddressColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AddressColumns.STREET + " TEXT NOT NULL, "
            + AddressColumns.NUMBER + " INTEGER, "
            + AddressColumns.DISTRICT + " TEXT NOT NULL, "
            + AddressColumns.CITY + " TEXT NOT NULL, "
            + AddressColumns.STATE + " TEXT NOT NULL, "
            + AddressColumns.HOSTING_PLACE + " INTEGER REFERENCES " + Tables.HOSTING_PLACE + "( " + HostingPlaceColumns.ID + " ) ON UPDATE CASCADE)";


    private static final String INSTALL_FEST_CREATE = "CREATE TABLE " +
            Tables.INSTALL_FEST
            + "(" + InstallFestColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InstallFestColumns.EDITION + " INTEGER REFERENCES " + Tables.EDITION + "( " + EditionColumns.ID + " ) ON UPDATE CASCADE)";

    private static final String SOFTWARE_CREATE = "CREATE TABLE " +
            Tables.SOFTWARE
            + "(" + SoftwareColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SoftwareColumns.NAME + " TEXT NOT NULL, "
            + SoftwareColumns.WEBSITE + " TEXT NOT NULL, "
            + SoftwareColumns.NOTES + " TEXT NOT NULL, "
            + SoftwareColumns.CATEGORY + " TEXT NOT NULL, "
            + SoftwareColumns.VERSION + " TEXT NOT NULL, "
            + SoftwareColumns.INSTALL_FEST + " INTEGER REFERENCES " + Tables.INSTALL_FEST + "( " + InstallFestColumns.ID + " ) ON UPDATE CASCADE)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EDITION_CREATE);
        database.execSQL(LECTURE_CREATE);
        database.execSQL(COURSE_CREATE);
        database.execSQL(SUPPORTER_CREATE);
        database.execSQL(HOSTING_PLACE_CREATE);
        database.execSQL(ADDRESS_CREATE);

        database.execSQL(INSTALL_FEST_CREATE);
        database.execSQL(SOFTWARE_CREATE);
        importData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

         /*
        * It's not using the property 'ON DELETE CASCADE' because it generates compatibility
        * problems since the minimum API used is 15. This is a possible solution commented below.
        * Check this subject out: <http://stackoverflow.com/questions/2545558/foreign-key-constraints-in-android-using-sqlite-on-delete-cascade>
        * Recommendation: <https://code.google.com/p/sqlite-manager/wiki/ForeignKeys>
        * */

        database.execSQL("DROP TABLE IF EXISTS " + Tables.EDITION);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.LECTURE);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.COURSE);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.SUPPORTER);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.HOSTING_PLACE);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.ADDRESS);

        database.execSQL("DROP TABLE IF EXISTS " + Tables.INSTALL_FEST);
        database.execSQL("DROP TABLE IF EXISTS " + Tables.SOFTWARE);
        onCreate(database);
    }

    //    This could be a solution, it's very reasonable, but it's not working properly
    //
    //    @Override
    //    public void onOpen(SQLiteDatabase db) {
    //        super.onOpen(db);
    //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
    //            if (! db.isReadOnly()) {
    //                db.execSQL("PRAGMA foreign_keys = ON;");
    //            }
    //        }
    //    }
    //
    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    //    @Override
    //    public void onConfigure(SQLiteDatabase db) {
    //        super.onConfigure(db);
    //        db.setForeignKeyConstraintsEnabled(true);
    //    }

    private void importData(SQLiteDatabase database) {

        final DatabaseContainer databaseContainer = JSONHandler.parseJSON(context);

        for (Edition edition : databaseContainer.getEditions()) {
            ContentValues editionValues = new ContentValues();
            editionValues.put(EditionColumns.YEAR, edition.getYear());

            final long insertedEditionId = database.insert(Tables.EDITION, null, editionValues);
            editionValues.clear();

            final List<Lecture> lectures = edition.getLectures();
            if (lectures != null) {
                for (Lecture lecture : lectures) {
                    ContentValues values = new ContentValues();
                    values.put(LectureColumns.TITLE, lecture.getTitle());
                    values.put(LectureColumns.DESCRIPTION, lecture.getDescription());
                    values.put(LectureColumns.FIELD, lecture.getField());
                    values.put(LectureColumns.LECTURER, lecture.getLecturer());
                    values.put(LectureColumns.SCHEDULE_BEGIN, lecture.getScheduleBegin());
                    values.put(LectureColumns.SCHEDULE_END, lecture.getScheduleEnd());
                    values.put(LectureColumns.ROOM, lecture.getRoom());
                    values.put(LectureColumns.EDITION, insertedEditionId);

                    database.insert(Tables.LECTURE, null, values);
                    values.clear();
                }
            }

            final List<Course> courses = edition.getCourses();
            if (courses != null) {
                for (Course course : courses) {
                    ContentValues values = new ContentValues();
                    values.put(CourseColumns.TITLE, course.getTitle());
                    values.put(CourseColumns.DESCRIPTION, course.getDescription());
                    values.put(CourseColumns.FIELD, course.getField());
                    values.put(CourseColumns.LECTURER, course.getLecturer());
                    values.put(CourseColumns.SCHEDULE_BEGIN, course.getScheduleBegin());
                    values.put(CourseColumns.SCHEDULE_END, course.getScheduleEnd());
                    values.put(CourseColumns.ROOM, course.getRoom());
                    values.put(CourseColumns.VACANCIES, course.getVacancies());
                    values.put(CourseColumns.EDITION, insertedEditionId);

                    database.insert(Tables.COURSE, null, values);
                    values.clear();
                }
            }

            final List<Supporter> supporters = edition.getSupporters();
            if (supporters != null) {
                for (Supporter supporter : supporters) {
                    ContentValues values = new ContentValues();
                    values.put(SupporterColumns.NAME, supporter.getName());
                    values.put(SupporterColumns.IMAGE, supporter.getImage());
                    values.put(SupporterColumns.BUSINESS_PACKAGE, supporter.getBusinessPackage().toString());
                    values.put(SupporterColumns.WEBSITE, supporter.getWebsite());
                    values.put(SupporterColumns.EDITION, insertedEditionId);

                    database.insert(Tables.SUPPORTER, null, values);
                    values.clear();
                }
            }

            final HostingPlace hostingPlace = edition.getHostingPlace();
            if (hostingPlace != null) {

                ContentValues hpValues = new ContentValues();
                hpValues.put(HostingPlaceColumns.NAME, hostingPlace.getName());
                hpValues.put(HostingPlaceColumns.LATITUDE, hostingPlace.getLatitude());
                hpValues.put(HostingPlaceColumns.LONGITUDE, hostingPlace.getLongitude());
                hpValues.put(HostingPlaceColumns.EDITION, insertedEditionId);
                final long insertedHostingPlaceId = database.insert(Tables.HOSTING_PLACE, null, hpValues);
                hpValues.clear();

                final Address address = hostingPlace.getAddress();
                if (address != null) {
                    ContentValues addressValues = new ContentValues();
                    addressValues.put(AddressColumns.STREET, address.getStreet());
                    addressValues.put(AddressColumns.NUMBER, address.getNumber());
                    addressValues.put(AddressColumns.DISTRICT, address.getDistrict());
                    addressValues.put(AddressColumns.CITY, address.getCity());
                    addressValues.put(AddressColumns.STATE, address.getState());
                    addressValues.put(AddressColumns.HOSTING_PLACE, insertedHostingPlaceId);
                    database.insert(Tables.ADDRESS, null, addressValues);
                    addressValues.clear();
                }
            }

            final InstallFest installFest = edition.getInstallFest();
            if (installFest != null) {
                ContentValues installFestValues = new ContentValues();
                installFestValues.put(InstallFestColumns.EDITION, insertedEditionId);
                final long insertedInstallFestId = database.insert(Tables.INSTALL_FEST, null, installFestValues);

                final List<Software> softwares = installFest.getSoftwares();
                if (softwares != null) {
                    for (Software software : softwares) {
                        ContentValues values = new ContentValues();
                        values.put(SoftwareColumns.NAME, software.getName());
                        values.put(SoftwareColumns.CATEGORY, software.getCategory());
                        values.put(SoftwareColumns.NOTES, software.getNotes());
                        values.put(SoftwareColumns.VERSION, software.getVersion());
                        values.put(SoftwareColumns.WEBSITE, software.getWebsite());
                        values.put(SoftwareColumns.INSTALL_FEST, insertedInstallFestId);

                        database.insertOrThrow(Tables.SOFTWARE, null, values);
                        values.clear();
                    }
                }
            }
        }
    }


}
