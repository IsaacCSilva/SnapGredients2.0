package mycontentprovider.edu.csulb.nav;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {



    /* DOCUMENTATION:
    * Here We implemented a navigation view as our bottom menu that you can visually
    * see on our UI. We created cases to switch from different navigation and call the
    * fragments that correspond to the navigation bar (Menu)
    * */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment select = null;
            switch (item.getItemId()) {
                case R.id.navigation_preferences:
                    select = new DetailFrag();
                    break;
                case R.id.navigation_search:
                    select = new SearchFrag();

                    break;
                case R.id.navigation_History:
                    select = new HistoryFrag();
                    break;
                case R.id.navigation_Favorites:
                    select = new FavoritesFrag();
                    break;
                case R.id.navigation_Bar:
                    select = new BarcodeFrag();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_capt, select).commit();
            return true;
        }
    };

/*
* Here we override the onCreate method for our MainActivity and initialize the design for our mainActivity
* We also made the Preferences the main page when you open the app by replacing it with DetailFrag
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_capt, new DetailFrag()).commit();
    }

}
