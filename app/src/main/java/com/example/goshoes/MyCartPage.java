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

public class MyCartPage extends AppCompatActivity {

    private Button btn1,btn2,checkoutbtn;
    private TextView t1,shoe_price,brand_name;
    private ImageView cart_imageview;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    int quantity1=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_page);



        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        final String received_Price =  intent.getStringExtra("shoe_price");


        btn1 = findViewById(R.id.minusbtn);
        btn2 = findViewById(R.id.plusbtn);
        checkoutbtn = findViewById(R.id.checkout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference childreference = databaseReference.child("Cart Details").child(firebaseAuth.getUid());

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCartPage.this, PaymentOptions.class);
                intent.putExtra("shoe_name",received_Name);
                intent.putExtra("shoe_image",received_Image);
                intent.putExtra("shoe_price",received_Price);
                startActivity(intent);
            }
        });

        cart_imageview = findViewById(R.id.shoe_cart_imageview);

        t1 = findViewById(R.id.cartpage_text);
        shoe_price = findViewById(R.id.shoe_cart_price);
        brand_name = findViewById(R.id.shoe_cart_brandname);

        t1.setText("1");
        
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("Cart Shoe Image").child(firebaseAuth.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(cart_imageview);
            }
        });

        childreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CartDetails cartDetails = snapshot.getValue(CartDetails.class);


                if (cartDetails != null) {

                    brand_name.setText(cartDetails.getShoename());
                    shoe_price.setText(cartDetails.getShoeprice());
                }
                else
                {
                    brand_name.setText("");
                    shoe_price.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast toast = Toast.makeText(MyCartPage.this,"Something Wrong",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                decrement1();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment1();
            }
        });

    }


    public void increment1 () {
        quantity1 = quantity1 + 1;
        display1(quantity1);
    }

    public void decrement1 () {
        if (quantity1>0){
            quantity1 = quantity1 - 1;
            display1(quantity1);
        }
        else
        {
            display1(quantity1);
        }
    }

    private void display1(int number) {

        t1.setText("" + number);

    }

}
