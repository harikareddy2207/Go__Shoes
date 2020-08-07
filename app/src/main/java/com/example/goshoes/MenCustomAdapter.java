package com.example.goshoes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MenCustomAdapter extends BaseAdapter {

    MenFragment menFragment = new MenFragment();

    @Override
    public int getCount() {
        return menFragment.shoesImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_view,parent,false);
        //getting view in row_data
        TextView name = view1.findViewById(R.id.shoes_brand_name);
        ImageView image = view1.findViewById(R.id.shoes_image);



        name.setText(menFragment.shoesNames[position]);
        image.setImageResource(menFragment.shoesImages[position]);
        return view1;
    }
}
