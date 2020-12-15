package gordey.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gorde_000 on 19.02.2018.
 */

public class Filters extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnsearch;
    //ScrollView scrollview;
    LinearLayout llMain;
    AutoCompleteTextView product;
    ImageButton btnadd;
    ImageButton btndel;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        product = (AutoCompleteTextView) findViewById(R.id.product);

        btnadd = (ImageButton) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(this);
        btndel = (ImageButton) findViewById(R.id.btndel);
        btndel.setOnClickListener(this);

        btnsearch = (ImageButton) findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(this);

        AutoCompleteTextView product = (AutoCompleteTextView) findViewById(R.id.product);

        String[] cats = getResources().getStringArray(R.array.products);
        List<String> catList = Arrays.asList(cats);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, catList);
        product.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnadd:
                //Создание LayoutParams c шириной и высотой по содержимому
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                // переменная для хранения значения выравнивания
                // по умолчанию пусть будет LEFT
                int btnGravity = Gravity.LEFT;
                // переносим полученное значение выравнивания в LayoutParams
                lParams.gravity = btnGravity;

                // создаем Button, пишем текст и добавляем в LinearLayout
                TextView txtNew = new TextView(this);
                txtNew.setText(product.getText().toString());
                txtNew.setTextColor(0xff66ff00);
                txtNew.setTextSize(18);
                llMain.addView(txtNew, lParams);
                break;

            case R.id.btndel:
                //Создание LayoutParams c шириной и высотой по содержимому
                LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                // переменная для хранения значения выравнивания
                // по умолчанию пусть будет LEFT
                int btnGravity1 = Gravity.LEFT;
                // переносим полученное значение выравнивания в LayoutParams
                lParams1.gravity = btnGravity1;

                // создаем Button, пишем текст и добавляем в LinearLayout
                TextView txtNew1 = new TextView(this);
                txtNew1.setText(product.getText().toString());
                txtNew1.setTextColor(0xffCC0605);
                txtNew1.setTextSize(18);
                llMain.addView(txtNew1, lParams1);
                break;

            case R.id.btnsearch:
                //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Recipes.class);
                startActivity(intent);
                break;

        }
    }
}

