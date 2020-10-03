package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.User;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button btn;
    private TextView txt;
    private Context context;
    private DbHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createNotificationChannel();

        email = findViewById(R.id.insertEmail);
        password = findViewById(R.id.insertPassoword);
        btn = findViewById(R.id.buttonLogin);
        txt = findViewById(R.id.textForgot);
        context = this;
        loginHandler = new DbHandler(context);

        final  User user = loginHandler.getSingleUser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if(userEmail.equals(user.getUsername()) && userPassword.equals(user.getPassword())){
                    Toasty.success(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, MainDashboard.class));
                }else{
                    Toasty.error(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, MainDashboard.class));
                }
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int int_random = rand.nextInt(25) * 100000;
                String message = String.valueOf(int_random);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        context, "forgot"
                ).setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("Temporary Password")
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(100,builder.build());

                Intent intent = new Intent(context, ForgotPassword.class);
                intent.putExtra("MESSAGE", message);
                startActivity(intent);
            }
        });
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "loginChannel";
            String description = "Channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("forgot", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}