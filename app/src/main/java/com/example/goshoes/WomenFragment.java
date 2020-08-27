package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WomenFragment extends Fragment {

    GridView gridView;

    public String[] womenShoesNames = {"Adidas","Adidas","Fila","Fila","Puma","Puma","Reebok","Reebok","Nike","Nike"};
    public int[] womenShoesImages = {R.drawable.women_adidas_1, R.drawable.women_adidas_2, R.drawable.women_fila_1, R.drawable.women_fila_2, R.drawable.women_puma_1, R.drawable.women_puma_2, R.drawable.women_reebok_1, R.drawable.women_reebok_2, R.drawable.women_nike_1, R.drawable.women_nike_2};
    public String[] womenShoesPrice = {"$150","$230","$180","$160","$250","$200","$300","$240","$190","$130"};
    public String Category = "Women";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_women,container,false);


        gridView = view.findViewById(R.id.women_grid_view);

        WomenCustomAdapter customAdapter = new WomenCustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(),ShoeDetails.class);
                intent.putExtra("name",womenShoesNames[position]);
                intent.putExtra("image",womenShoesImages[position]);
                intent.putExtra("price",womenShoesPrice[position]);
                intent.putExtra("category",Category);
                intent.putExtra("position",position);
                startActivity(intent);

            }

        });
        return view;

    }
}
