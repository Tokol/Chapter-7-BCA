package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class RandomZooAnimalFacts extends AppCompatActivity {

    ImageView animalImage;
    Button requestBtn;
    TextView animalName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_zoo_animal_facts);
        animalImage = findViewById(R.id.animalImg);
        requestBtn = findViewById(R.id.request_api_btn);
        animalName = findViewById(R.id.name_animal);
        requestToZooServer();

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestToZooServer();
            }
        });

    }



    void requestToZooServer(){
        RequestQueue queue = Volley.newRequestQueue(RandomZooAnimalFacts.this);
            String url = "https://zoo-animal-api.herokuapp.com/animals/rand";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                decodeResponse(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Exception", error.toString());
                    }
                }

        );

        queue.add(stringRequest);

    }

    void decodeResponse(String response){

        try{
            JSONObject jsonObject = new JSONObject(response);
            Log.d("responseInJson", jsonObject.toString());

            String animalImageLINK = jsonObject.getString("image_link");

            String animalNameText = jsonObject.getString("name");
            animalName.setText(animalNameText);


            Picasso.get().load(animalImageLINK).into(animalImage);


        }

        catch(JSONException error){
            Log.d("responseInJson", "response not found");

        }






    }





}