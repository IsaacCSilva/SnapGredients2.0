package mycontentprovider.edu.csulb.nav;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import static java.lang.Boolean.TRUE;


public class DetailFrag extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;
    Button btnDelete;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
     Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.inglist_activity,container,false);
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

                    InglistActivity db = new InglistActivity(
                            getContext());

                    db.insertLabel(label);
                   inputLabel.setText("");
                    Toast.makeText(getContext(),"You added " + label,Toast.LENGTH_SHORT).show();
                   // InputMethodManager imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
                    //imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);
                    loadSpinnerData();
               } else {
                    Toast.makeText(getContext(), "Please enter label name", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String label = inputLabel.getText().toString();
                        InglistActivity db = new InglistActivity(
                                getContext());
                       db.delete(label);
                       // c.requery();
                           //   db.getAllLabels();
                            //db.deleteData(label);
                        //if (deletedRows == TRUE) {
                            Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                           // loadSpinnerData();
                       // }
                    }
                }
        );
        return view;
    }


    private void loadSpinnerData() {
        InglistActivity db = new InglistActivity(getContext());
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?>arg0) {

    }
   // public void setText(String item){
     //   TextView view = (TextView) getView().findViewById(R.id.captain);
       // view.setText(item);
    //}
}
