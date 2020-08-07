package com.example.goshoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText forgotpwd;
    private Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        forgotpwd = findViewById(R.id.ForgotEmail);
        submitbtn = findViewById(R.id.ForgotSubmitBtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = forgotpwd.getText().toString().trim();

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                //mp.start();

                if(email.length() == 0)
                {
                    Toast toast = Toast.makeText(ForgotPassword.this,"Please Enter Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
                else
                {

                    Toast toast = Toast.makeText(ForgotPassword.this,"Password Reset Email sent",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();

                    Intent i = new Intent(ForgotPassword.this,MainActivity.class);
                    startActivity(i);


                }

            }
        });
    }
}
