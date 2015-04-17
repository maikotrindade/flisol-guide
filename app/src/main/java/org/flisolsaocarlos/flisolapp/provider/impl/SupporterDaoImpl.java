package org.flisolsaocarlos.flisolapp.provider.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisolapp.model.Supporter;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.EditionColumns;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.SupporterColumns;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.Tables;
import org.flisolsaocarlos.flisolapp.provider.DatabaseHelper;
import org.flisolsaocarlos.flisolapp.provider.SupporterDao;
import org.flisolsaocarlos.flisolapp.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class SupporterDaoImpl implements SupporterDao {

    final static String TAG = SupporterDaoImpl.class.getName();
    private SQLiteDatabase database;

    public SupporterDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    public Supporter findById(int id) {
        Supporter supporter = null;
        String columns = SupporterColumns.NAME + ", " +
                SupporterColumns.IMAGE + ", " +
                SupporterColumns.BUSINESS_PACKAGE + ", " +
                SupporterColumns.WEBSITE;

        Cursor cursor = database.rawQuery("SELECT " + columns + " FROM " + Tables.SUPPORTER
                + " WHERE " + SupporterColumns.ID + " = " + id, null);

        supporter = cursorToSupporter(cursor);
        return supporter;
    }

    @Override
    public List<Supporter> findByEditionYear(int year) {
        List<Supporter> supporters = new ArrayList<Supporter>();
        String columns = SupporterColumns.NAME + ", " +
                SupporterColumns.IMAGE + ", " +
                SupporterColumns.BUSINESS_PACKAGE + ", " +
                SupporterColumns.WEBSITE + ", " +
                SupporterColumns.WEBSITE_TITLE;

        final String query = "SELECT " + columns + " FROM " + Tables.EDITION + " e"
                + " INNER JOIN " + Tables.SUPPORTER + " s"
                + " ON e." + EditionColumns.ID + " = s." + SupporterColumns.EDITION
                + " WHERE e." + EditionColumns.YEAR + " = ?"
                + " ORDER BY " + SupporterColumns.NAME;
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Supporter supporter = cursorToSupporter(cursor);
            supporters.add(supporter);
            cursor.moveToNext();
        }
        cursor.close();

        return supporters;
    }

    private Supporter cursorToSupporter(Cursor cursor) {
        Supporter supporter = new Supporter();
        supporter.setName(cursor.getString(0));
        supporter.setImage(cursor.getString(1));
        supporter.setBusinessPackage(Supporter.BusinessPackage.valueOf((cursor.getString(2).toUpperCase())));
        supporter.setWebsite(cursor.getString(3));
        supporter.setWebsiteTitle(cursor.getString(4));

        return supporter;
    }
}