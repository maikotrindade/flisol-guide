package org.flisolsaocarlos.flisol.provider.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisol.model.Course;
import org.flisolsaocarlos.flisol.provider.CourseDao;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.CourseColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.EditionColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.Tables;
import org.flisolsaocarlos.flisol.provider.DatabaseHelper;
import org.flisolsaocarlos.flisol.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    final static String TAG = CourseDaoImpl.class.getName();
    private SQLiteDatabase database;

    public CourseDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    public Course findById(int id) {
        Course course = null;
        String[] columns = {CourseColumns.TITLE,
                CourseColumns.DESCRIPTION,
                CourseColumns.LECTURER,
                CourseColumns.FIELD,
                CourseColumns.SCHEDULE_BEGIN,
                CourseColumns.SCHEDULE_END,
                CourseColumns.ROOM,
                CourseColumns.VACANCIES};
        Cursor cursor = database.rawQuery("SELECT " + columns + " FROM " + Tables.COURSE
                + " WHERE " + CourseColumns.ID + " = " + id, null);

        course = cursorToCourse(cursor);
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<Course>();

        String[] columns = {CourseColumns.TITLE,
                CourseColumns.DESCRIPTION,
                CourseColumns.LECTURER,
                CourseColumns.FIELD,
                CourseColumns.SCHEDULE_BEGIN,
                CourseColumns.SCHEDULE_END,
                CourseColumns.ROOM,
                CourseColumns.VACANCIES};

        Cursor cursor = database.query(Tables.COURSE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();

        return courses;
    }

    @Override
    public List<Course> findByEditionYear(int year) {
        List<Course> courses = new ArrayList<Course>();
        String columns = CourseColumns.TITLE + ", " +
                CourseColumns.DESCRIPTION + ", " +
                CourseColumns.LECTURER + ", " +
                CourseColumns.FIELD + ", " +
                CourseColumns.SCHEDULE_BEGIN + ", " +
                CourseColumns.SCHEDULE_END + ", " +
                CourseColumns.ROOM + ", " +
                CourseColumns.VACANCIES;

        final String query = "SELECT " + columns + " FROM " + Tables.EDITION + " e "
                + " INNER JOIN " + Tables.COURSE + " c "
                + " ON e." + EditionColumns.ID + " = c." + CourseColumns.EDITION
                + " WHERE e." + EditionColumns.YEAR + " = ? "
                + " ORDER BY c." + CourseColumns.TITLE;
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();

        return courses;
    }

    private Course cursorToCourse(Cursor cursor) {
        Course course = new Course();
        course.setTitle(cursor.getString(0));
        course.setDescription(cursor.getString(1));
        course.setLecturer(cursor.getString(2));
        course.setField(cursor.getString(3));
        course.setScheduleBegin(cursor.getString(4));
        course.setScheduleEnd(cursor.getString(5));
        course.setRoom(cursor.getString(6));
        course.setVacancies(cursor.getInt(7));

        return course;
    }

    @Override
    public Course create(Course course) {

        ContentValues values = new ContentValues();
        values.put(CourseColumns.TITLE, course.getTitle());
        values.put(CourseColumns.DESCRIPTION, course.getDescription());
        values.put(CourseColumns.FIELD, course.getField());
        values.put(CourseColumns.LECTURER, course.getLecturer());
        values.put(CourseColumns.SCHEDULE_BEGIN, course.getScheduleBegin());
        values.put(CourseColumns.SCHEDULE_END, course.getScheduleEnd());
        values.put(CourseColumns.ROOM, course.getRoom());
        values.put(CourseColumns.VACANCIES, course.getVacancies());

        database.insert(Tables.COURSE, null, values);

        return course;
    }

    public List<String> findYears() {
        List<String> years = new ArrayList<String>();

        final String query = "SELECT DISTINCT e." + EditionColumns.YEAR + " FROM " + Tables.EDITION + " e"
                + " JOIN " + Tables.COURSE + " c"
                + " ON e." + EditionColumns.ID + " = c." + CourseColumns.EDITION
                + " ORDER BY " + EditionColumns.YEAR + " DESC";
        final Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            years.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return years;
    }

}