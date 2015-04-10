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
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import org.flisolsaocarlos.flisol.adapter.AgendaLectureAdapter;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.service.ApplicationService;
import org.flisolsaocarlos.flisol.service.LectureService;

import java.util.List;

public class AgendaRoomOneFragment extends ListFragment {

    private AgendaLectureAdapter lectureAdapter;
    private LectureService lectureService;
    private List<Lecture> lectures;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lectureService = new LectureService();
        Context context = ApplicationService.getInstance().getApplicationContext();
        final LayoutInflater layoutInfl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lectureAdapter = new AgendaLectureAdapter(layoutInfl);
        lectures = lectureService.getByYearAndRoom(2015, "Sala 1");
        if ((lectures != null) && (!lectures.isEmpty())) {
            for (Lecture lecture : lectures) {
                lectureAdapter.addItem(lecture);
            }
            setListAdapter(lectureAdapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // do something with the data
    }




//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // do something with the data
//    }
}
