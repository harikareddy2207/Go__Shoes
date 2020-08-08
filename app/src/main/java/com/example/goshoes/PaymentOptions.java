package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentOptions extends AppCompatActivity {

    private Button btncontinue;
    private RadioButton rcreditdebit,rcod;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        btncontinue = findViewById(R.id.continuepayment);
        rcreditdebit =findViewById(R.id.creditdebitcard);
        rcod = findViewById(R.id.cod);
        group = findViewById(R.id.radioGroup);

        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        final String received_Price =  intent.getStringExtra("shoe_price");


        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(PaymentOptions.this, Payment.class);
                intent.putExtra("shoe_name",received_Name);
                intent.putExtra("shoe_image",received_Image);
                intent.putExtra("shoe_price",received_Price);
                startActivity(intent);

            }
        });
    }

    public void checkButton (View v)
    {
        int radioId = group.getCheckedRadioButtonId();

        rcreditdebit = findViewById(radioId);
        rcod = findViewById(radioId);
    }
}
