package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class ForgotPassword extends AppCompatActivity {

    private EditText password;
    private Button btn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        password = findViewById(R.id.insertPassoword);
        btn = findViewById(R.id.buttonLogin);
        context = this;

        final String message = getIntent().getStringExtra("MESSAGE");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPassword = password.getText().toString();

                if(userPassword.equals(message)){
                    Toasty.success(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, MainDashboard.class));
                }else{
                    Toasty.error(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, Login.class));
                }
            }
        });


    }
}