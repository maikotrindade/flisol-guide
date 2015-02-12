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

package org.flisolsaocarlos.flisol.service;

import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.provider.impl.EditionDaoImpl;
import org.flisolsaocarlos.flisol.provider.impl.HostingPlaceDaoImpl;

public class EditionService {

    static final String TAG = EditionService.class.getName();
    private EditionDaoImpl editionDaoImpl;

    public EditionService() {
        editionDaoImpl = new EditionDaoImpl();
    }

    public Edition getByYear(final int year) {
        return editionDaoImpl.findByYear(year);
    }

    public HostingPlace getHostingPlaceByEdition(final Edition edition) {
        HostingPlaceDaoImpl hostingPlaceDao = new HostingPlaceDaoImpl();
        return hostingPlaceDao.findByEdition(edition);
    }
}
