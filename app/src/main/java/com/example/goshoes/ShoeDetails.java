package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShoeDetails extends AppCompatActivity {

    TextView gridData,pricedetail,shoe_category;
    ImageView imageView;
    Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        gridData = findViewById(R.id.brandname_detail);
        imageView = findViewById(R.id.shoedetail_image);
        cartButton = findViewById(R.id.shoecart_button);
        pricedetail = findViewById(R.id.shoeprice_detail);
        shoe_category = findViewById(R.id.shoecategory_detail);

        Intent intent = getIntent();
        final String receivedName =  intent.getStringExtra("name");
        final int receivedImage = intent.getIntExtra("image",0);
        final String receivedPrice =  intent.getStringExtra("price");
        final String categoryName =  intent.getStringExtra("category");



        gridData.setText(receivedName);
        imageView.setImageResource(receivedImage);
        pricedetail.setText(receivedPrice);
        shoe_category.setText(categoryName);
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShoeDetails.this, MyCartPage.class);
                intent.putExtra("shoe_name",receivedName);
                intent.putExtra("shoe_image",receivedImage);
                intent.putExtra("shoe_price",receivedPrice);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
