package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String url = "https://demo.hashup.tech/std/items?std_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Username = findViewById(R.id.editStdId);

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = Username.getText().toString();
                if(user.isEmpty() ){
                    Log.e("error","please fill username&password");
                }else{
                    Log.d("username","username: "+user);
                    Intent intent = new Intent(MainActivity.this, DataListActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
            }
        });

    }
}