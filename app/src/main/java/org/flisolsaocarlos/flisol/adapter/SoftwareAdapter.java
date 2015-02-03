package org.flisolsaocarlos.flisol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Software;

import java.util.ArrayList;
import java.util.List;

public class SoftwareAdapter extends BaseAdapter {

    private List<Software> softwares = new ArrayList();
    private LayoutInflater mInflater;

    public SoftwareAdapter(LayoutInflater layoutInflater) {
        mInflater = layoutInflater;
    }

    public void addItem(final Software Software) {
        softwares.add(Software);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return softwares.size();
    }

    @Override
    public Software getItem(int position) {
        return softwares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.software_list, null);
            holder = new ViewHolder();
            holder.nameTxt = (TextView) convertView.findViewById(R.id.name);
            holder.websiteTxt = (TextView) convertView.findViewById(R.id.website);
            holder.notesTxt = (TextView) convertView.findViewById(R.id.notes);
            holder.categoryTxt = (TextView) convertView.findViewById(R.id.category);
            holder.versionTxt = (TextView) convertView.findViewById(R.id.version);
            convertView.setTag(holder);
            convertView.setBackgroundResource(R.drawable.custom_list);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Software Software = softwares.get(position);
        holder.nameTxt.setText(Software.getName());
        holder.websiteTxt.setText(Software.getWebsite());
        holder.notesTxt.setText(Software.getNotes());
        holder.categoryTxt.setText(Software.getCategory());
        holder.versionTxt.setText(Software.getVersion());

        return convertView;
    }

    public static class ViewHolder {
        public TextView nameTxt;
        public TextView websiteTxt;
        public TextView notesTxt;
        public TextView categoryTxt;
        public TextView versionTxt;
    }

}