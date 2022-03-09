package com.mtha.mymatchinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> img_id;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    //input: JSON String
    //output: void (extracts image src url from JSON object into arraylist)
    private void extractImageUrls(String result){
        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONArray flags = jsonObj.getJSONArray("flags");
            for (int i = 0; i < 10; i++) {
                JSONObject flag = flags.getJSONObject(i);
                int image_url = flag.getInt("id");

                img_id.add(image_url);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}