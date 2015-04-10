package org.flisolsaocarlos.flisol.provider.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.provider.DatabaseContract;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.*;
import org.flisolsaocarlos.flisol.provider.DatabaseHelper;
import org.flisolsaocarlos.flisol.provider.LectureDao;
import org.flisolsaocarlos.flisol.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class LectureDaoImpl implements LectureDao {

    final static String TAG = LectureDaoImpl.class.getName();
    private SQLiteDatabase database;

    public LectureDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    public Lecture findById(int id) {
        Lecture lecture = null;
        String[] columns = {LectureColumns.TITLE,
                LectureColumns.DESCRIPTION,
                LectureColumns.LECTURER,
                LectureColumns.FIELD,
                LectureColumns.SCHEDULE_BEGIN,
                LectureColumns.SCHEDULE_END,
                LectureColumns.ROOM
        };
        Cursor cursor =  database.rawQuery("SELECT " + columns + " FROM " + Tables.LECTURE
                + " WHERE " + LectureColumns.ID + " = " + id , null);

        lecture = cursorToLecture(cursor);
        return lecture;
    }

    @Override
    public List<Lecture> findAll() {
        List<Lecture> lectures = new ArrayList<Lecture>();

        String[] columns = {LectureColumns.TITLE,
                LectureColumns.DESCRIPTION,
                LectureColumns.LECTURER,
                LectureColumns.FIELD,
                LectureColumns.SCHEDULE_BEGIN,
                LectureColumns.SCHEDULE_END,
                LectureColumns.ROOM
        };
        Cursor cursor = database.query(DatabaseContract.Tables.LECTURE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lecture lecture = cursorToLecture(cursor);
            lectures.add(lecture);
            cursor.moveToNext();
        }
        cursor.close();

        return lectures;
    }

    public List<Lecture> findByEditionYear(int year) {
        List<Lecture> lectures = new ArrayList<Lecture>();
        String columns = LectureColumns.TITLE + ", " +
                LectureColumns.DESCRIPTION + ", " +
                LectureColumns.LECTURER + ", " +
                LectureColumns.FIELD + ", " +
                LectureColumns.SCHEDULE_BEGIN + ", " +
                LectureColumns.SCHEDULE_END + ", " +
                LectureColumns.ROOM;

        final String query = "SELECT " + columns + " FROM " + Tables.EDITION + " e "
                + " INNER JOIN " + Tables.LECTURE + " l "
                + " ON e." + EditionColumns.ID + " = l." + LectureColumns.EDITION
                + " WHERE e." + EditionColumns.YEAR + " = ? "
                + " ORDER BY l." + LectureColumns.TITLE;
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lecture lecture = cursorToLecture(cursor);
            lectures.add(lecture);
            cursor.moveToNext();
        }
        cursor.close();

        return lectures;
    }

    private Lecture cursorToLecture(Cursor cursor) {
        Lecture lecture = new Lecture();
        lecture.setTitle(cursor.getString(0));
        lecture.setDescription(cursor.getString(1));
        lecture.setLecturer(cursor.getString(2));
        lecture.setField(cursor.getString(3));
        lecture.setScheduleBegin(cursor.getString(4));
        lecture.setScheduleEnd(cursor.getString(5));
        lecture.setRoom(cursor.getString(6));

        return lecture;
    }

    public List<Lecture> findByEditionYearAndRoom(int year, String room) {
        List<Lecture> lectures = new ArrayList<Lecture>();
        String columns = LectureColumns.TITLE + ", " +
                LectureColumns.DESCRIPTION + ", " +
                LectureColumns.LECTURER + ", " +
                LectureColumns.FIELD + ", " +
                LectureColumns.SCHEDULE_BEGIN + ", " +
                LectureColumns.SCHEDULE_END + ", " +
                LectureColumns.ROOM;

        final String query = "SELECT " + columns + " FROM " + Tables.EDITION + " e"
                + " INNER JOIN " + Tables.LECTURE + " l"
                + " ON e." + EditionColumns.ID + " = l." + LectureColumns.EDITION
                + " WHERE e." + EditionColumns.YEAR + " = ?"
                + " AND l." + LectureColumns.ROOM + " LIKE '" + room + "'"
                + " ORDER BY l." + LectureColumns.SCHEDULE_BEGIN;
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lecture lecture = cursorToLecture(cursor);
            lectures.add(lecture);
            cursor.moveToNext();
        }
        cursor.close();

        return lectures;
    }


    public Lecture create(Lecture lecture) {

        ContentValues values = new ContentValues();
        values.put(LectureColumns.TITLE, lecture.getTitle());
        values.put(LectureColumns.DESCRIPTION, lecture.getDescription());
        values.put(LectureColumns.FIELD, lecture.getField());
        values.put(LectureColumns.LECTURER, lecture.getLecturer());
        values.put(LectureColumns.SCHEDULE_BEGIN, lecture.getScheduleBegin());
        values.put(LectureColumns.SCHEDULE_END, lecture.getScheduleEnd());
        values.put(LectureColumns.ROOM, lecture.getRoom());

        database.insert(DatabaseContract.Tables.LECTURE, null, values);

        return lecture;
    }

    public List<String> findYears() {
        List<String> years = new ArrayList<String>();

        final String query = "SELECT DISTINCT e." + EditionColumns.YEAR + " FROM " + Tables.EDITION + " e"
                + " JOIN " + Tables.LECTURE + " l"
                + " ON e." + EditionColumns.ID + " = l." + LectureColumns.EDITION
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