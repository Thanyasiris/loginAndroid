package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    //String url = "http://203.150.107.144:7777/login";
    String url = "https://publicobject.com/helloworld.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Username = findViewById(R.id.editUsername);
        EditText Password = findViewById(R.id.editPassword);

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();
                if(user.isEmpty() || pass.isEmpty()){
                    Log.e("error","please fill username&password");
                }else{
                    Log.d("username","username: "+user);
                    Log.d("password","password: "+pass);
                }
            }
        });

        try {
            run();
        } catch (IOException e){

        }
    }

    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                Log.d("ok",myResponse);
            }
        });
    }
}