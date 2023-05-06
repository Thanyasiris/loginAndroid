package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataListActivity extends AppCompatActivity {

    String url = "https://demo.hashup.tech/std/items?std_id=";
    ListView simpleList;
    JSONArray output;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        String user = getIntent().getStringExtra("user");
        Log.d("user","user: "+user);

        try {
            run(user);
            Log.d("output",data);
        } catch (IOException e){

        }
        /*
        ArrayList<String> listdata = new ArrayList<String>();
        if (output != null) {
            for (int i=0;i<output.length();i++){
                try {
                    listdata.add(output.getJSONObject(i).getString("field_a"));
                }catch (JSONException err){}
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, listdata);
        simpleList.setAdapter(arrayAdapter);
        */
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
                    JSONArray output1 = json.getJSONArray("data");

                    DataListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            output = output1;
                            try{
                                data = output1.getJSONObject(0).getString("field_a").toString();
                                Log.d("output",output1.getJSONObject(0).getString("field_a").toString());
                            }catch (JSONException err){
                                Log.e("err","error json");
                            }
                        }
                    });
                }catch (JSONException err){
                    Log.e("err","error json");
                }
            }
        });
/*
        try{
            Log.d("output",output.getJSONObject(0).getString("field_a").toString());
        }catch (JSONException err){
            Log.e("err","error json");
        }*/


    }
    void setOutput(JSONArray data){
        output = data;
    }
}
