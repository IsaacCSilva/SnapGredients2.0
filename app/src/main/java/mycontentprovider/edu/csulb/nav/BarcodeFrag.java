package mycontentprovider.edu.csulb.nav;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BarcodeFrag extends Fragment{
    /* DOCUMENTATION
    * Here we have a fragment outline for the Barcode tab of the menu
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
        View view = inflater.inflate(R.layout.food_display,container,false);
        return view;
    }

}


