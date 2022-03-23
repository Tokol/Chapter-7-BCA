package com.example.chapter7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            String marsPhotoUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/Perseverance/latest_photos?api_key=6BdWiVpuMwANxgWRRIlrZrqVZkKpxCihizQNYpAj";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, marsPhotoUrl, new Response.Listener<String>() {
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





        )
        {
            @Nullable
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> params = new HashMap<>();

                                   // practising how to send params in server
//                                   params.put("email", animalName.getText().toString() );
//
//                                      params.put("password", animalName.getText().toString() );
//                                           params.put("email", animalName.getText().toString() );
//
//                                              params.put("password", animalName.getText().toString() );
                                    return   params;
            }
        } ;

        queue.add(stringRequest);

    }

    ArrayList<NasaPresevenceRoverData>    decodeResponse(String response){

        ArrayList<NasaPresevenceRoverData> nasaPresevenceRoverDataList = new ArrayList<NasaPresevenceRoverData>();


        try{
            JSONObject jsonObject = new JSONObject(response);



            JSONArray dataArray = jsonObject.getJSONArray("latest_photos");

            Log.d("responseInJson", dataArray.toString());

            for (int i=0; i<dataArray.length(); i++){


                JSONObject getDataObject  = dataArray.getJSONObject(i);
                String id = getDataObject.getString("id");
                String sol = getDataObject.getString("sol");

                //camera object
                JSONObject cameraObject = getDataObject.getJSONObject("camera");

                String cameraId = cameraObject.getString("id");
                String name = cameraObject.getString("name");
                // camera object ended


                String imageSource = getDataObject.getString("img_src");

                nasaPresevenceRoverDataList.add(
                  new NasaPresevenceRoverData(id,sol,imageSource)
                );



            }


            return nasaPresevenceRoverDataList;

        }

        catch(JSONException error){
            Log.d("responseInJson", "response not found");

        }


        return nasaPresevenceRoverDataList;



    }





}

class NasaPresevenceRoverData {
    String id;
    String sol;
    String imageLink;


    NasaPresevenceRoverData(String id,
            String sol,
            String imageLink){

        this.id = id;
        this.sol = sol;
        this.imageLink = imageLink;

    }
}