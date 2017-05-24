package com.jonmid.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jonmid.sqlite.Adapters.AdapterUser;
import com.jonmid.sqlite.Data.DataUser;
import com.jonmid.sqlite.Models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create, listbtn;
    ListView lista;
    DataUser dataUser;
    List<User> userList;
    AdapterUser adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button) findViewById(R.id.id_btn_create);
        listbtn = (Button) findViewById(R.id.id_btn_list);
        lista = (ListView) findViewById(R.id.id_lv_mylist);
        dataUser = new DataUser(this);
        dataUser.open();


        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = dataUser.findAll();
                adapterUser = new AdapterUser(getApplicationContext(), userList);
                lista.setAdapter(adapterUser);
            }
        });
    }

    /*private void createData(){
        User user = new User();
        user.setName("jonmid");
        user.setEmail("jamideros@hotmail.com");
        dataUser.create(user);
    }*/

    public void goRegisterUser(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
}
