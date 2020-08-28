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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        btncontinue = findViewById(R.id.continuepayment);
        rcreditdebit =findViewById(R.id.creditdebitcard);
        rcod = findViewById(R.id.cod);

        Intent intent = getIntent();
        final String s =  intent.getStringExtra("shoe_quantity");


        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rcreditdebit.isChecked())
                {
                    Intent intent = new Intent(PaymentOptions.this, Payment.class);
                    intent.putExtra("shoe_quantity",s);
                    startActivity(intent);
                }
                else
                {
                    if (rcod.isChecked())
                    {
                        Intent intent = new Intent(PaymentOptions.this, PayConfirm.class);
                        intent.putExtra("shoe_quantity",s);
                        startActivity(intent);
                    }
                }



            }
        });
    }

}
