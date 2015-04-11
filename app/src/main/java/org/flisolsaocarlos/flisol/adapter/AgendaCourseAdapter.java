package org.flisolsaocarlos.flisol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Course;

import java.util.ArrayList;
import java.util.List;


public class AgendaCourseAdapter extends BaseAdapter {

    private List<Course> Courses = new ArrayList();
    private LayoutInflater mInflater;

    public AgendaCourseAdapter(LayoutInflater layoutInflater) {
        mInflater = layoutInflater;
    }

    public void addItem(final Course Course) {
        Courses.add(Course);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Courses.size();
    }

    @Override
    public Course getItem(int position) {
        return Courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.agenda_course_fragment, null);
            holder = new ViewHolder();
            holder.titleTxt = (TextView) convertView.findViewById(R.id.title);
            holder.LecturerTxt = (TextView) convertView.findViewById(R.id.lecturer);
            holder.scheduleBeginTxt = (TextView) convertView.findViewById(R.id.schedule_begin);
            holder.scheduleEndTxt = (TextView) convertView.findViewById(R.id.schedule_end);
          //  holder.vacanciesTxt = (TextView) convertView.findViewById(R.id.vacancies);
            convertView.setTag(holder);
            convertView.setBackgroundResource(R.drawable.custom_list);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Course Course = Courses.get(position);
        holder.titleTxt.setText(Course.getTitle());
        holder.LecturerTxt.setText(Course.getLecturer());
        holder.scheduleBeginTxt.setText(Course.getScheduleBegin());
        holder.scheduleEndTxt.setText(Course.getScheduleEnd());
       // holder.vacanciesTxt.setText(Course.getVacancies());
        return convertView;
    }

    public static class ViewHolder {
        public TextView titleTxt;
        public TextView LecturerTxt;
        public TextView scheduleBeginTxt;
        public TextView scheduleEndTxt;
      //  public TextView vacanciesTxt;
    }
}

