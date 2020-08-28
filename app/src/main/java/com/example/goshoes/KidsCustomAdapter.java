package com.example.goshoes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class KidsCustomAdapter extends BaseAdapter {

    KidsFragment kidsFragment = new KidsFragment();

    @Override
    public int getCount() {
        return kidsFragment.kidsShoesImages.length;
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

        TextView name = view1.findViewById(R.id.shoes_brand_name);
        ImageView image = view1.findViewById(R.id.shoes_image);



        name.setText(kidsFragment.kidsShoesNames[position]);
        image.setImageResource(kidsFragment.kidsShoesImages[position]);
        return view1;
    }
}
