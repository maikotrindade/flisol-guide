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

package org.flisolsaocarlos.flisolapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.flisolsaocarlos.flisolapp.R;
import org.flisolsaocarlos.flisolapp.adapter.AgendaCourseAdapter;
import org.flisolsaocarlos.flisolapp.model.Edition;
import org.flisolsaocarlos.flisolapp.model.HostingPlace;
import org.flisolsaocarlos.flisolapp.model.Course;
import org.flisolsaocarlos.flisolapp.service.ApplicationService;
import org.flisolsaocarlos.flisolapp.service.CourseService;

import java.util.List;

public class AgendaRoomThreeFragment extends ListFragment {

    private AgendaCourseAdapter courseAdapter;
    private CourseService courseService;
    private List<Course> courses;

    final static Integer CURRENT_YEAR = 2015;
    final static String ROOM = "Laboratório 206";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        courseService = new CourseService();
        Context context = ApplicationService.getInstance().getApplicationContext();
        final LayoutInflater layoutInfl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        courseAdapter = new AgendaCourseAdapter(layoutInfl);
        courses = courseService.getByYearAndRoom(CURRENT_YEAR, ROOM);
        if ((courses != null) && (!courses.isEmpty())) {
            for (Course course : courses) {
                courseAdapter.addItem(course);
            }
            setListAdapter(courseAdapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(ApplicationService.getInstance(), CourseActivity.class);
        Course course = courseAdapter.getItem(position);
        intent.putExtra("course", course);

        final Edition edition = courseService.getEditionByYear(CURRENT_YEAR);
        final HostingPlace hostingPlace = courseService.getHostingPlaceByEdition(edition);
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
