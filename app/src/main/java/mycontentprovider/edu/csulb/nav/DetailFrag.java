package mycontentprovider.edu.csulb.nav;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class DetailFrag extends Fragment implements AdapterView.OnItemSelectedListener {

    // Declare variables for Detail Frag
    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;
    Button btnDelete;
    View view;
    InglistActivity db;
    Context mContext;

    /**
     * On class call, load the current instance of the app
     * @param savedInstanceState    The current instance of the app
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    /**
     * When the activity is created load the saved instance of the app
     * @param savedInstanceState    The saved instance of the app
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * When the view is created, show the labels and buttons
     * @param inflater  Inflater for the view
     * @param container The place where the list of labels are stored
     * @param savedInstanceState    The saved instance of the app
     * @return  The view for the ingredient list
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        view = inflater.inflate(R.layout.inglist_activity,container,false);
        btnDelete = (Button) view.findViewById(R.id.clrhist);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        btnAdd = (Button) view.findViewById(R.id.btn_add);
        inputLabel = (EditText) view.findViewById(R.id.input_label);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String label = inputLabel.getText().toString();
                if (label.trim().length() > 0) {

                    db = new InglistActivity(
                            getContext());

                    db.insertLabel(label);
                    inputLabel.setText("");
                    Toast.makeText(getContext(),"You added " + label,Toast.LENGTH_SHORT).show();

                    loadSpinnerData();
                } else {
                    Toast.makeText(getContext(), "Please enter label name", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         * Listener for buttons that are clicked. On click, it perf
         */
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        spinner = (Spinner) view.findViewById(R.id.spinner);

                        String label = inputLabel.getText().toString();
                        db = new InglistActivity(
                                getContext());
                        List<String> labels = db.getAllLabels();
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, labels);
                        db.delete(label);
                        dataAdapter.clear();
                        dataAdapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return view;
    }

    /**
     * Loads the spinner data when called
     */
    private void loadSpinnerData() {
        InglistActivity db = new InglistActivity(getContext());
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * When item is selected display to the user that the item was selected
     * @param parent    Parent adapter
     * @param view      Current view of the app
     * @param position  Position of the view
     * @param id    Id of the layout
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
    }

    /**
     * When nothing is selected, run this method
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?>arg0) {

    }

}