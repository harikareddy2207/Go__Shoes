package com.example.goshoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KidsFragment extends Fragment {

    GridView gridView;

    public String[] kidsShoesNames = {"Adidas","Adidas","Fila","Fila","Puma","Puma","Reebok","Reebok","Nike","Nike"};
    public int[] kidsShoesImages = {R.drawable.kids_adidas_1, R.drawable.kids_adidas_2, R.drawable.kids_fila_1, R.drawable.kids_fila_2, R.drawable.kids_puma_1, R.drawable.kids_puma_2, R.drawable.kids_reebok_1, R.drawable.kids_reebok_2, R.drawable.kids_nike_1, R.drawable.kids_nike_2};
    public String[] kidsShoesPrice = {"$100","$130","$80","$60","$150","$100","$200","$140","$90","$80"};
    public String Category = "Kids";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_kids,container,false);

        gridView = view.findViewById(R.id.kids_grid_view);

        KidsCustomAdapter customAdapter = new KidsCustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent = new Intent(getContext(),ShoeDetails.class);
                //intent.putExtra("name",kidsShoesNames[position]);
                //intent.putExtra("image",kidsShoesImages[position]);
                //intent.putExtra("price",kidsShoesPrice[position]);
                //intent.putExtra("category",Category);
                //startActivity(intent);
            }

        });
        return view;

    }
}
