package mycontentprovider.edu.csulb.nav;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;

   // HomeFragment home = new HomeFragment();

   public void buttonClick(View v){
       switch (v.getId()){
           case R.id.button123:
               Intent in = new Intent();
               in.setClassName("mycontentprovider.edu.csulb.nav","mycontentprovider.edu.csulb.nav.FoodDisplay");
               startActivity(in);
               break;
       }
   }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment select = null;
            switch (item.getItemId()) {
                case R.id.navigation_preferences:
                    select = new DetailFrag();
                   // mTextMessage.setText(R.string.title_home);
                    break;
                case R.id.navigation_search:
                    select = new SearchFrag();
                    //mTextMessage.setText(R.string.title_dashboard);
                   // return true;
                    break;
                case R.id.navigation_History:
                    select = new HistoryFrag();
                    //mTextMessage.setText(R.string.title_notifications);
                    //return true;
                    break;
                case R.id.navigation_Favorites:
                    select = new FavoritesFrag();
                    break;
                case R.id.navigation_Bar:
                    //select = new InfoFrag();
                    //mTextMessage.setText("Hey");
                    return true;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_capt, select).commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_capt, new DetailFrag()).commit();
    }

}
