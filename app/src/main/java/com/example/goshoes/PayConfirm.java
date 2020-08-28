package com.example.goshoes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PayConfirm extends AppCompatActivity {

    private TextView name,price;
    private Button confirmbtn;
    private ImageView confirm_image;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_confirm);

        name = findViewById(R.id.confirm_brandname);
        price = findViewById(R.id.confirm_price);
        confirm_image = findViewById(R.id.confirm_imageview);
        confirmbtn = findViewById(R.id.confirm_button);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference childreference = databaseReference.child("Cart Details").child(firebaseAuth.getUid());


        Intent intent = getIntent();
        final String s =  intent.getStringExtra("shoe_quantity");


        childreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CartDetails cartDetails = snapshot.getValue(CartDetails.class);

                String s1 = cartDetails.getShoeprice();
                int x = Integer.valueOf(s1.substring(1));
                int y = Integer.valueOf(s);
                String mix = String.valueOf(x*y);


                if (cartDetails != null) {

                    name.setText(cartDetails.getShoename());
                    price.setText("$"+mix);
                }
                else
                {
                    name.setText("");
                    price.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast toast = Toast.makeText(PayConfirm.this,"Something Wrong",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });


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
