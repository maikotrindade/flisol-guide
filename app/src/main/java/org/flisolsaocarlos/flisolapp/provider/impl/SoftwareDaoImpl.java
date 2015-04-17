package org.flisolsaocarlos.flisolapp.provider.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisolapp.model.Software;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.EditionColumns;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.InstallFestColumns;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.SoftwareColumns;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.Tables;
import org.flisolsaocarlos.flisolapp.provider.DatabaseHelper;
import org.flisolsaocarlos.flisolapp.provider.SoftwareDao;
import org.flisolsaocarlos.flisolapp.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class SoftwareDaoImpl implements SoftwareDao {

    final static String TAG = SoftwareDaoImpl.class.getName();
    private SQLiteDatabase database;

    public SoftwareDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    @Override
    public List<Software> findAll() {
        List<Software> softwares = new ArrayList<Software>();

        Cursor cursor = database.query(Tables.SOFTWARE, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Software software = cursorToSoftware(cursor);
            softwares.add(software);
            cursor.moveToNext();
        }
        cursor.close();

        return softwares;
    }


    @Override
    public List<Software> findByYear(int year) {
        List<Software> softwares = new ArrayList<Software>();
        final String query = "SELECT s.* FROM " + Tables.EDITION + " e"
                + " INNER JOIN " + Tables.INSTALL_FEST + " i"
                + " ON e." + EditionColumns.ID + " = i." + InstallFestColumns.EDITION
                + " JOIN " + Tables.SOFTWARE + " s"
                + " ON s." + SoftwareColumns.INSTALL_FEST + " = i." + InstallFestColumns.ID
                + " WHERE e." + EditionColumns.YEAR + " = ? "
                + " ORDER BY " + SoftwareColumns.NAME;
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Software software = cursorToSoftware(cursor);
            softwares.add(software);
            cursor.moveToNext();
        }
        cursor.close();

        return softwares;
    }

    private Software cursorToSoftware(Cursor cursor) {
        Software software = new Software();
        software.setId(cursor.getInt(0));
        software.setName(cursor.getString(1));
        software.setWebsite(cursor.getString(2));
        software.setNotes(cursor.getString(3));
        software.setCategory(cursor.getString(4));
        software.setVersion(cursor.getString(5));

        return software;
    }
}