package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataListActivity extends AppCompatActivity {

    String url = "https://demo.hashup.tech/std/items?std_id=";
    private volatile String data="";

    JSONArray output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        String user = getIntent().getStringExtra("user");
        Log.d("user","user: "+user);

        try {
            run(user);
            Log.d("data","data string2: "+data);
        } catch (IOException e){

        }
    }

    void run(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String httpUrl = url+id;
        //String output1 = "";

        Request request = new Request.Builder()
                .url(httpUrl)
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
                try{
                    JSONObject json = new JSONObject(myResponse);
                    output = json.getJSONArray("data");
                }catch (JSONException err){
                    Log.e("err","error json");
                }


                data = myResponse;
                //output1 = myResponse;
                setData(myResponse);
                Log.d("ok",myResponse);
                try{
                    Log.d("output",output.getJSONObject(0).getString("field_a").toString());
                }catch (JSONException err){
                    Log.e("err","error json");
                }
            }
        });

        Log.d("data","data string4: "+data);
        //Log.d("data","data string5: "+output);
    }

    void setData(String input){
        data = input;
        Log.d("data","data string5: "+data);
    }
}