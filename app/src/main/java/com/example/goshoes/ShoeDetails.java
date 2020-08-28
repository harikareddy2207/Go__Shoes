package com.example.goshoes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ShoeDetails extends AppCompatActivity {

    TextView gridData,pricedetail,shoe_category;
    ImageView imageView;
    Button cartButton;
    private FirebaseAuth firebaseAuth;
    String receivedName,receivedPrice,category;
    int receivedImage,position;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public int[] menShoesImages = {R.drawable.adidas_1, R.drawable.adidas_2, R.drawable.fila_1, R.drawable.fila_2, R.drawable.puma_1, R.drawable.puma_2, R.drawable.reebok_1, R.drawable.reebok_2, R.drawable.nike_1, R.drawable.nike_2};
    public int[] womenShoesImages = {R.drawable.women_adidas_1, R.drawable.women_adidas_2, R.drawable.women_fila_1, R.drawable.women_fila_2, R.drawable.women_puma_1, R.drawable.women_puma_2, R.drawable.women_reebok_1, R.drawable.women_reebok_2, R.drawable.women_nike_1, R.drawable.women_nike_2};
    public int[] kidsShoesImages = {R.drawable.kids_adidas_1, R.drawable.kids_adidas_2, R.drawable.kids_fila_1, R.drawable.kids_fila_2, R.drawable.kids_puma_1, R.drawable.kids_puma_2, R.drawable.kids_reebok_1, R.drawable.kids_reebok_2, R.drawable.kids_nike_1, R.drawable.kids_nike_2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        gridData = findViewById(R.id.brandname_detail);
        imageView = findViewById(R.id.shoedetail_image);
        cartButton = findViewById(R.id.shoecart_button);
        pricedetail = findViewById(R.id.shoeprice_detail);
        shoe_category = findViewById(R.id.shoecategory_detail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        Intent intent = getIntent();
        receivedName =  intent.getStringExtra("name");
        receivedImage = intent.getIntExtra("image",0);
        receivedPrice =  intent.getStringExtra("price");
        position = intent.getIntExtra("position",0);
        category =  intent.getStringExtra("category");


        gridData.setText(receivedName);
        imageView.setImageResource(receivedImage);
        pricedetail.setText(receivedPrice);
        shoe_category.setText(category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendUserData();


                Intent intent = new Intent(ShoeDetails.this, HomePage.class);
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

    private void sendUserData()
    {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Cart Details").child(firebaseAuth.getUid());

        CartDetails cartDetails = new CartDetails(receivedName,receivedPrice);
        reference.setValue(cartDetails);

        StorageReference imageReference = storageReference.child("Cart Shoe Image").child(firebaseAuth.getUid());

        UploadTask uploadTask;

        if (category.equalsIgnoreCase("Men"))
        {
            uploadTask = imageReference.putFile(Uri.parse("android.resource://com.example.goshoes/" + menShoesImages[position]));

        }else if(category.equalsIgnoreCase("Women"))
        {
            uploadTask = imageReference.putFile(Uri.parse("android.resource://com.example.goshoes/" + womenShoesImages[position]));

        }else
        {
            uploadTask = imageReference.putFile(Uri.parse("android.resource://com.example.goshoes/" + kidsShoesImages[position]));

        }

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast toast = Toast.makeText(ShoeDetails.this,"Upload Failed",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast toast = Toast.makeText(ShoeDetails.this,"Added to cart",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

    }
}
