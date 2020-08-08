package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyCartPage extends AppCompatActivity {

    private Button btn1,btn2,checkoutbtn;
    private TextView t1,shoe_price,brand_name;
    private ImageView cart_imageview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_page);



        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        String received_Price =  intent.getStringExtra("shoe_price");


        btn1 = findViewById(R.id.minusbtn);
        btn2 = findViewById(R.id.plusbtn);
        checkoutbtn = findViewById(R.id.checkout);

        cart_imageview = findViewById(R.id.shoe_cart_imageview);

        t1 = findViewById(R.id.cartpage_text);
        shoe_price = findViewById(R.id.shoe_cart_price);
        brand_name = findViewById(R.id.shoe_cart_brandname);

        t1.setText("1");
        shoe_price.setText(received_Price);
        brand_name.setText(received_Name);
        cart_imageview.setImageResource(received_Image);

    }

}
