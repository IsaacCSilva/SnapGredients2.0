package mycontentprovider.edu.csulb.nav;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class HistoryFrag extends Fragment {
    /* DOCUMENTATION
* Here we have a fragment outline for the history tab of the menu
* We an onCreateView method that is meant to inflate the layout of the menu page and we
* return view
* */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    ListView list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.histrory_fragment,container,false);
        /*
        * This is a sample of what the list of History would look like
        * when added to complete the list for the user
        * A string is created then call the layout from the XML
        * Use an adapterArray that is a string to place it in
        * get the activity then sort it in a list thanks to android's 'simple_list_item_1'
        * Then we set the adapter
        * */
         final String[] web = {
                 "SKIPPY, PEANUT BUTTER",
                 "DORITOS"
        };
      ListView  list = (ListView) view.findViewById(R.id.list);
        ArrayAdapter<String> listva = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                web
        );
        list.setAdapter(listva);
        return view;
    }
}
