package com.zybooks.volleytestapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.helloWorld);
        Button button =  (Button)findViewById(R.id.btndostuff);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://icanhazdadjoke.com/slack";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray =response.getJSONArray("attachments");

                            for(int i = 0; i< jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String text = jsonObject.getString("text");

                                tv.setText(text);
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Failed to pull data.");
                    }

                });

                requestQueue.add(jsonObjectRequest);
            }
        });




    }
}