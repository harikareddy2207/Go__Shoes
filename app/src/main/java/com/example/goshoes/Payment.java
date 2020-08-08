package com.example.goshoes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    Button payContinue;
    EditText cardname,cardno,mm,yy,cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardname = findViewById(R.id.card_name);
        cardno = findViewById(R.id.card_number);
        mm = findViewById(R.id.expiry_month);
        yy = findViewById(R.id.expiry_year);

        payContinue = findViewById(R.id.make_payment);

        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        final String received_Price =  intent.getStringExtra("shoe_price");


        payContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                Intent intent = new Intent(Payment.this, PayConfirm.class);
                intent.putExtra("shoe_name",received_Name);
                intent.putExtra("shoe_image",received_Image);
                intent.putExtra("shoe_price",received_Price);
                startActivity(intent);
                /*

                if (cardno.getText().toString().trim().length() == 0 || cardname.getText().toString().trim().length() == 0 || mm.getText().toString().trim().length() == 0 || yy.getText().toString().trim().length() == 0 || cvv.getText().toString().trim().length() == 0 )
                {
                    Toast toast = Toast.makeText(Payment.this,"Enter All Details",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    Intent intent = new Intent(Payment.this,PayConfirm.class);
                    intent.putExtra("shoe_name",received_Name);
                    intent.putExtra("shoe_image",received_Image);
                    intent.putExtra("shoe_price",received_Price);
                    startActivity(intent);
                }

                 */
            }
        });
    }
}
