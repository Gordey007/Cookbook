package gordey.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnfilter;
    Button my;
    ImageButton search;
    AutoCompleteTextView Search_dishes;

    public RecipesJ[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Search_dishes = (AutoCompleteTextView) findViewById(R.id.search_dishes);

        btnfilter = (Button) findViewById(R.id.btnfilter);
        btnfilter.setOnClickListener(this);

        my = (Button) findViewById(R.id.my);
        my.setOnClickListener(this);

        search = (ImageButton) findViewById(R.id.search);
        search.setOnClickListener(this);

        AutoCompleteTextView dish = (AutoCompleteTextView) findViewById(R.id.search_dishes);

        String[] cats = getResources().getStringArray(R.array.dishes);
        List<String> catList = Arrays.asList(cats);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, catList);
                dish.setAdapter(adapter);
    }

//    public void print1 (String s1){
//        Intent intent2 = new Intent(this, Recipe.class);
//        intent2.putExtra("nergy_value", s1);
//        Log.d("HTTP1",s1 +"212211221222222222222222222222222222222222212121");
//    }

    public void print(String s){

        Intent intent = new Intent(this, Recipe.class);
        intent.putExtra("recipe", s);
//        intent.putExtra("nergy_value", s);
//        intent.putExtra("products", s);
//        intent.putExtra("time", s);
        intent.putExtra("name", Search_dishes.getText().toString());
        startActivity(intent);
        Log.d("HTTP1",s);
    }
    @Override
    public void onClick(View v) {

        MyHttpRequestTask myHttpRequestTask = new MyHttpRequestTask() {
            @Override
            void doGet(String s) {
               print(s);
               // print1(s);
            }
        };

        switch (v.getId()) {
            case R.id.btnfilter:
                Intent intent = new Intent(this, Filters.class);
                startActivity(intent);
                break;

            case R.id.search:

               // startActivity(intent1);

                //new MyHttpRequestTask().execute(my_url,my_data);
                try {
                    myHttpRequestTask.execute("http://192.168.1.3:8080/CookBook/cbs?name=" +  URLEncoder.encode(Search_dishes.getText().toString(), "UTF-8")+"&code="+ URLEncoder.encode(Search_dishes.getText().toString(), "UTF-8"), "name=рис");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.my:
                Intent intent2 = new Intent(this, MyRecipes.class);
                startActivity(intent2);
                break;

        }
    }

}


















