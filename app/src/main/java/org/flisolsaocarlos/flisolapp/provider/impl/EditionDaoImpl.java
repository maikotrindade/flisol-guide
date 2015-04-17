/*
 * Copyleft Flisol 2015.
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.flisolsaocarlos.flisolapp.provider.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisolapp.model.Edition;
import org.flisolsaocarlos.flisolapp.provider.DatabaseContract.*;
import org.flisolsaocarlos.flisolapp.provider.DatabaseHelper;
import org.flisolsaocarlos.flisolapp.provider.EditionDao;
import org.flisolsaocarlos.flisolapp.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

public class EditionDaoImpl implements EditionDao {

    final static String TAG = EditionDaoImpl.class.getName();
    private SQLiteDatabase database;

    public EditionDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    public Edition findById(int id) {
        Edition edition = null;
        String[] columns = {EditionColumns.ID,
                EditionColumns.YEAR,
        };
        Cursor cursor = database.rawQuery("SELECT " + columns + " FROM " + Tables.EDITION
                + " WHERE " + EditionColumns.ID + " = " + id, null);

        edition = cursorToEdition(cursor);
        return edition;
    }

    @Override
    public List<Edition> findAll() {
        List<Edition> editions = new ArrayList<Edition>();

        String[] columns = {EditionColumns.ID,
                EditionColumns.YEAR,
        };
        Cursor cursor = database.query(Tables.EDITION, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Edition edition = cursorToEdition(cursor);
            editions.add(edition);
            cursor.moveToNext();
        }
        cursor.close();

        return editions;
    }

    @Override
    public Edition findByYear(int year) {
        String columns = EditionColumns.ID + ", " +
                EditionColumns.YEAR;

        final String query = "SELECT " + columns + " FROM " + Tables.EDITION + " e "
                + " WHERE e." + EditionColumns.YEAR + " = ? ";
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(year)});

        cursor.moveToFirst();
        Edition edition = cursorToEdition(cursor);
        cursor.close();

        return edition;
    }

    private Edition cursorToEdition(Cursor cursor) {
        Edition edition = new Edition();
        edition.setId(cursor.getInt(0));
        edition.setYear(cursor.getInt(1));
        return edition;
    }


}
