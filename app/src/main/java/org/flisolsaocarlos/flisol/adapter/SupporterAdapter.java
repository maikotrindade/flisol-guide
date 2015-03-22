package org.flisolsaocarlos.flisol.adapter;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Supporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SupporterAdapter extends BaseAdapter {

    final static String SUPPORTER_FILE_FOLDER = "supporter_image/";
    private List<Supporter> supporters = new ArrayList();
    private LayoutInflater mInflater;
    private AssetManager assets;

    public SupporterAdapter(LayoutInflater layoutInflater, AssetManager assets) {
        mInflater = layoutInflater;
        this.assets = assets;
    }

    public void addItem(final Supporter Supporter) {
        supporters.add(Supporter);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return supporters.size();
    }

    @Override
    public Supporter getItem(int position) {
        return supporters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Supporter supporter = supporters.get(position);
        if (convertView == null) {

            switch (supporter.getBusinessPackage()) {
                case DIAMOND:
                    convertView = mInflater.inflate(R.layout.supporter_diamond_list, null);
                    break;
                case GOLD:
                    convertView = mInflater.inflate(R.layout.supporter_gold_list, null);
                    break;
                case SILVER:
                    convertView = mInflater.inflate(R.layout.supporter_silver_list, null);
                    break;
                case BRONZE:
                    convertView = mInflater.inflate(R.layout.supporter_bronze_list, null);
                    break;
            }

            holder = new ViewHolder();
            holder.nameTxt = (TextView) convertView.findViewById(R.id.name);
            holder.websiteTxt = (TextView) convertView.findViewById(R.id.website);
            holder.logoImage = (ImageView) convertView.findViewById(R.id.logoImage);
            holder.businessPackageTxt = (TextView) convertView.findViewById(R.id.business_package);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTxt.setText(supporter.getName());
        holder.businessPackageTxt.setText(supporter.getBusinessPackage().toString());
        holder.websiteTxt.setText(supporter.getWebsite());

        //supporters' images are in folder 'assets/supporter_image'and the Entity Supporter
        //has only the filename of its image;
        try {
            final InputStream ims = assets.open(SUPPORTER_FILE_FOLDER + supporter.getImage());
            final Drawable drawable = Drawable.createFromStream(ims, null);
            holder.logoImage.setImageDrawable(drawable);
        } catch (IOException ex) {
            Log.e("m:", ex.toString());
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView nameTxt;
        public ImageView logoImage;
        public TextView businessPackageTxt;
        public TextView websiteTxt;
    }
}

