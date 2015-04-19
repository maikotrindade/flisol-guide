package org.flisolsaocarlos.flisolapp.adapter;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.flisolsaocarlos.flisolapp.R;
import org.flisolsaocarlos.flisolapp.model.Supporter;

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
            convertView = mInflater.inflate(R.layout.supporter_default_list, null);
            holder = new ViewHolder();
            holder.nameTxt = (TextView) convertView.findViewById(R.id.name);
            holder.websiteTxt = (TextView) convertView.findViewById(R.id.website);
            holder.logoImage = (ImageView) convertView.findViewById(R.id.logoImage);
            holder.businessPackageImage = (ImageView) convertView.findViewById(R.id.icon);
            holder.businessPackageTxt = (TextView) convertView.findViewById(R.id.business_package);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTxt.setText(supporter.getName());
        holder.businessPackageTxt.setText(supporter.getBusinessPackage().toString());
        holder.websiteTxt.setText(supporter.getWebsiteTitle());

        //supporters' images are in folder 'assets/supporter_image'and the Entity Supporter
        //has only the filename of its image;
        try {
            final InputStream logoInput = assets.open(SUPPORTER_FILE_FOLDER + supporter.getImage());
            final Drawable logoDrawable = Drawable.createFromStream(logoInput, null);
            holder.logoImage.setImageDrawable(logoDrawable);

            InputStream packageInput = null;
            switch (supporter.getBusinessPackage()) {
                case DIAMOND:
                    packageInput = assets.open(SUPPORTER_FILE_FOLDER + Supporter.BusinessPackage.DIAMOND.getIcon());
                    break;
                case GOLD:
                    packageInput = assets.open(SUPPORTER_FILE_FOLDER + Supporter.BusinessPackage.GOLD.getIcon());
                    break;
                case SILVER:
                    packageInput = assets.open(SUPPORTER_FILE_FOLDER + Supporter.BusinessPackage.SILVER.getIcon());
                    break;
                case BRONZE:
                    packageInput = assets.open(SUPPORTER_FILE_FOLDER +Supporter.BusinessPackage.BRONZE.getIcon());
                    break;
            }
            if (packageInput != null) {
                final Drawable packageDrawable = Drawable.createFromStream(packageInput, null);
                holder.businessPackageImage.setImageDrawable(packageDrawable);
            }

        } catch (IOException ex) {
            Log.e("m:", ex.toString());
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView nameTxt;
        public ImageView logoImage;
        public TextView businessPackageTxt;
        public ImageView businessPackageImage;
        public TextView websiteTxt;
    }
}

