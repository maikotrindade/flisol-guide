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

package org.flisolsaocarlos.flisol.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.adapter.AgendaLectureAdapter;
import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.service.ApplicationService;
import org.flisolsaocarlos.flisol.service.LectureService;

import java.util.List;

public class AgendaRoomTwoFragment extends ListFragment {

    private AgendaLectureAdapter lectureAdapter;
    private LectureService lectureService;
    private List<Lecture> lectures;

    final static Integer CURRENT_YEAR = 2015;
    final static String ROOM = "Sala 2";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lectureService = new LectureService();
        Context context = ApplicationService.getInstance().getApplicationContext();
        final LayoutInflater layoutInfl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lectureAdapter = new AgendaLectureAdapter(layoutInfl);
        lectures = lectureService.getByYearAndRoom(CURRENT_YEAR, ROOM);
        if ((lectures != null) && (!lectures.isEmpty())) {
            for (Lecture lecture : lectures) {
                lectureAdapter.addItem(lecture);
            }
            setListAdapter(lectureAdapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(ApplicationService.getInstance(), LectureActivity.class);
        Lecture lecture = lectureAdapter.getItem(position);
        intent.putExtra("lecture", lecture);

        final Edition edition = lectureService.getEditionByYear(CURRENT_YEAR);
        final HostingPlace hostingPlace = lectureService.getHostingPlaceByEdition(edition);
        final String hostingPlaceName = hostingPlace.getName();
        intent.putExtra("hostingPlace", hostingPlaceName);

        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
