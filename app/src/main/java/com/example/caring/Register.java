package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.User;

import es.dmoral.toasty.Toasty;

public class Register extends AppCompatActivity {

    private EditText username, password;
    private Button btn;
    private TextView textView;
    private Context context;
    private DbHandler userDb;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.insertEmail);
        password = findViewById(R.id.insertPassoword);
        btn = findViewById(R.id.buttonRegister);
        context = this;
        userDb = new DbHandler(context);

        //Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);



        //Add validations
        awesomeValidation.addValidation(this,R.id.insertEmail, RegexTemplate.NOT_EMPTY,R.string.invalid_input);
        awesomeValidation.addValidation(this,R.id.insertPassoword, RegexTemplate.NOT_EMPTY,R.string.invalid_input);



        User user = userDb.getSingleUser();
        if(user != null){
            startActivity(new Intent(context, Login.class));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_username = username.getText().toString();
                String input_password = password.getText().toString();

                User user = new User(input_username, input_password);
                boolean status = userDb.addUser(user);

                if(status){
                    Toasty.success(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, Login.class));
                }else {
                    Toasty.error(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, Register.class));
                }

            }
        });


    }
}