package mycontentprovider.edu.csulb.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FavoritesFrag extends Fragment {
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
        View view = inflater.inflate(R.layout.favorites_fragment,container,false);
        final String[] web = {
                "SKIPPY, PEANUT BUTTER"
        };
        list = (ListView) view.findViewById(R.id.list);
        ArrayAdapter<String> listva = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                web
        );
        list.setAdapter(listva);
        return view;
    }
   // public void setText(String item){
     //   TextView view = (TextView) getView().findViewById(R.id.captain);
       // view.setText(item);
    //}
}
