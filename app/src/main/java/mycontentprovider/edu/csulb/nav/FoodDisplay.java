package mycontentprovider.edu.csulb.nav;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.ListView;

import java.util.List;

public class FoodDisplay extends AppCompatActivity {

    public void buttonClick(View v) {
        switch (v.getId()) {
            case R.id.button123:
                Intent in = new Intent();
                in.setClassName("mycontentprovider.edu.csulb.nav", "mycontentprovider.edu.csulb.nav.FoodDisplay");
                startActivity(in);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_display);


       /* //ListView list ;
        final String[] web = {
                "ROASTED PEANUTS"
        };
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> listva = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                web
        );
        list.setAdapter(listva);
    } */
}}