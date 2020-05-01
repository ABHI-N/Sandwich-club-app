package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView origin_tv=findViewById(R.id.origin_ans_tv);
        TextView description_tv=findViewById(R.id.description_ans_tv);
        TextView ingredients_tv=findViewById(R.id.ingredients_ans_tv);
        TextView also_know_as_tv=findViewById(R.id.also_known_ans_tv);


        origin_tv.setText(getResources().getString(R.string.place_of_origin_ans,sandwich.getPlaceOfOrigin()));
        description_tv.setText(getResources().getString(R.string.description_ans,sandwich.getDescription()));
        ingredients_tv.setText(getResources().getString(R.string.ingredients_ans,list_to_string(sandwich.getIngredients())));

        also_know_as_tv.setText(getResources().getString(R.string.also_known_as_ans,list_to_string(sandwich.getAlsoKnownAs())));

    }
    private String list_to_string(ArrayList<String> arrayList){
        StringBuilder string=new StringBuilder();
        if(arrayList.equals("")||arrayList.isEmpty()){
            return getResources().getString(R.string.empty_string);
        }
        for(int i=0;i<arrayList.size();i++){
            string.append(arrayList.get(i));
            if(i<arrayList.size()-1){
                string.append(',');
            }
        }

        return string.toString();

    }
}
