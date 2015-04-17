package org.flisolsaocarlos.flisolapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.flisolsaocarlos.flisolapp.R;
import org.flisolsaocarlos.flisolapp.model.Lecture;

import java.util.ArrayList;
import java.util.List;


public class AgendaLectureAdapter extends BaseAdapter {

    private List<Lecture> lectures = new ArrayList();
    private LayoutInflater mInflater;

    public AgendaLectureAdapter(LayoutInflater layoutInflater) {
        mInflater = layoutInflater;
    }

    public void addItem(final Lecture lecture) {
        lectures.add(lecture);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lectures.size();
    }

    @Override
    public Lecture getItem(int position) {
        return lectures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.agenda_lecture_fragment, null);
            holder = new ViewHolder();
            holder.titleTxt = (TextView) convertView.findViewById(R.id.title);
            holder.lecturerTxt = (TextView) convertView.findViewById(R.id.lecturer);
            holder.scheduleBeginTxt = (TextView) convertView.findViewById(R.id.schedule_begin);
            holder.scheduleEndTxt = (TextView) convertView.findViewById(R.id.schedule_end);
            convertView.setTag(holder);
            convertView.setBackgroundResource(R.drawable.custom_agenda_list);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lecture lecture = lectures.get(position);
        holder.titleTxt.setText(lecture.getTitle());
        holder.lecturerTxt.setText(lecture.getLecturer());
        holder.scheduleBeginTxt.setText(lecture.getScheduleBegin());
        holder.scheduleEndTxt.setText(lecture.getScheduleEnd());

        //TODO fix this temporary solution
        holder.titleTxt.setTextColor(Color.parseColor("#424242"));
        holder.lecturerTxt.setTextColor(Color.parseColor("#424242"));
        holder.scheduleBeginTxt.setTextColor(Color.parseColor("#424242"));
        holder.scheduleEndTxt.setTextColor(Color.parseColor("#424242"));

        return convertView;
    }

    public static class ViewHolder {
        public TextView titleTxt;
        public TextView lecturerTxt;
        public TextView scheduleBeginTxt;
        public TextView scheduleEndTxt;
    }
}

