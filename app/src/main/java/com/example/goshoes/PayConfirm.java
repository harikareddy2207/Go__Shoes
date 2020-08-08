package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayConfirm extends AppCompatActivity {

    private TextView name,price;
    private Button confirmbtn;
    private ImageView confirm_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_confirm);

        name = findViewById(R.id.confirm_brandname);
        price = findViewById(R.id.confirm_price);
        confirm_image = findViewById(R.id.confirm_imageview);
        confirmbtn = findViewById(R.id.confirm_button);

        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        final String received_Price =  intent.getStringExtra("shoe_price");

        price.setText(received_Price);
        name.setText(received_Name);
        confirm_image.setImageResource(received_Image);



        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(PayConfirm.this,"Payment Successful",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Intent intent = new Intent(PayConfirm.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}
