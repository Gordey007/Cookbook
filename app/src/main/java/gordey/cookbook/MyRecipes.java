package gordey.cookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by gorde_000 on 17.03.2018.
 */

public class MyRecipes extends AppCompatActivity implements  View.OnClickListener {

    int count=0;

    Button  btnAdd;
    EditText name;
    EditText recipe;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipes);

        name = (EditText) findViewById(R.id.myname);
        recipe = (EditText) findViewById(R.id.myrecipe);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }

    Gson gson = new Gson();

    @Override
    public void onClick(View v) {
        MyHttpRequestTask myHttpRequestTask = new MyHttpRequestTask() {
            @Override
            void doGet(String s) {

                //Log.d("HTTP1",s );
                // print(s);
            }
        };

        switch (v.getId()) {

            case R.id.btnAdd:

                RecipesJ recipesJFilter = new RecipesJ();
                recipesJFilter.name = name.getText().toString();
                recipesJFilter.recipe =recipe.getText().toString();

                try {
                    recipesJFilter.recipe = recipesJFilter.recipe.replace(' ', '+');
                    recipesJFilter.name = recipesJFilter.name.replace(' ', '+');

                    myHttpRequestTask.execute("http://192.168.1.3:8080/CookBook/add?" + "&code="+ URLEncoder.encode("add", "UTF-8" )+ "&recipe=" + gson.toJson(recipesJFilter), "name=рис");

                    String check = "http://192.168.1.3:8080/CookBook/add?" + "&code="+ URLEncoder.encode("add", "UTF-8" )+ "&recipe=" + gson.toJson(recipesJFilter);
                    Log.d("HTTP1",check);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
        }

    }
    
}


