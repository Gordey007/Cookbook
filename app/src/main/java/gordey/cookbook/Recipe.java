package gordey.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by gorde_000 on 19.02.2018.
 */

public class Recipe extends AppCompatActivity {

    TextView Recipe, Energy_value, Products, Time, Ingestion, Kitchen;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        Recipe = (TextView) findViewById(R.id.myrecipe);
        Name = (TextView) findViewById(R.id.name);
        Energy_value = (TextView) findViewById(R.id.energy_value);
        Products = (TextView) findViewById(R.id.products);
        Time = (TextView) findViewById(R.id.time);

        Intent intent = getIntent();

        String recipes = intent.getStringExtra("recipe");
      //  Log.d("HTTP1", recipes+"5555555555555555");
        Gson gson = new Gson();
        RecipesJ[] recipesJ = gson.fromJson(recipes, RecipesJ[].class);
        Recipe.setText(recipesJ[0].recipe);

        Energy_value.setText(recipesJ[0].energy_value);
        Products.setText(recipesJ[0].products);
        Time.setText(recipesJ[0].time);

        String name = intent.getStringExtra("name");
        Name.setText(name);


    }
}
















