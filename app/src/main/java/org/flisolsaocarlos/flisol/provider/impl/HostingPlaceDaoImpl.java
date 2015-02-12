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

package org.flisolsaocarlos.flisol.provider.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.EditionColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.HostingPlaceColumns;
import org.flisolsaocarlos.flisol.provider.DatabaseContract.Tables;
import org.flisolsaocarlos.flisol.provider.DatabaseHelper;
import org.flisolsaocarlos.flisol.provider.HostingPlaceDao;
import org.flisolsaocarlos.flisol.service.ApplicationService;

public class HostingPlaceDaoImpl implements HostingPlaceDao{

    final static String TAG = HostingPlaceDaoImpl.class.getName();
    private SQLiteDatabase database;

    public HostingPlaceDaoImpl() {
        DatabaseHelper databaseHelper = ApplicationService.getInstance().getDatabaseHelper();
        database = databaseHelper.getWritableDatabase();
    }

    @Override
    public HostingPlace findByEdition(Edition edition) {
        StringBuilder columns = new StringBuilder();
        columns.append("h.").append(HostingPlaceColumns.ID).append(", ")
               .append("h.").append(HostingPlaceColumns.NAME);

        final String query = "SELECT " + columns + " FROM " + Tables.HOSTING_PLACE + " h "
                + "JOIN " + Tables.EDITION + " e "
                + "ON e." + EditionColumns.ID + " = h." + HostingPlaceColumns.EDITION
                + " WHERE e." + EditionColumns.YEAR + " = ? ";
        final Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(edition.getYear())});

        cursor.moveToFirst();
        HostingPlace hostingPlace = cursorToHostingPlace(cursor);
        cursor.close();

        return hostingPlace;
    }

    private HostingPlace cursorToHostingPlace(Cursor cursor) {
        HostingPlace hostingPlace = new HostingPlace();
        hostingPlace.setId(cursor.getInt(0));
        hostingPlace.setName(cursor.getString(1));
        return hostingPlace;
    }


}
