package com.jonmid.sqlite;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jonmid.sqlite.Data.DataUser;
import com.jonmid.sqlite.Models.User;

public class RegisterUserActivity extends AppCompatActivity {

    TextInputEditText name_user, email_user;
    Button register;
    DataUser dataUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name_user = (TextInputEditText) findViewById(R.id.id_txt_name);
        email_user = (TextInputEditText) findViewById(R.id.id_txt_email);
        register = (Button) findViewById(R.id.id_btn_register);
        dataUser = new DataUser(this);
        dataUser.open();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createData();
                goMain();

            }
        });


    }
    private void createData(){

        User user = new User();
        user.setName(name_user.getText().toString());
        user.setEmail(email_user.getText().toString());
        dataUser.create(user);
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
