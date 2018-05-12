package mycontentprovider.edu.csulb.nav;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class SearchFrag extends Fragment {

    /*
    *DOCUMENTATION:
    * Here we crreate an onCreateView to inflate the view
    * and call all the layout variables
    * We have a textview, a button(Bt2), a progress bar to load the data
    * The we call the 'setOnClickListener' when the button is pressed
    * we then execute 'RetrieveFeedTask
    *
    * */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    TextView t3;
    Button Bt2;
    EditText text2;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.search_fragment,container,false);
         text2 = (EditText) view.findViewById(R.id.FoodText);
        Bt2 = (Button) view.findViewById(R.id.queryButton);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        t3 = (TextView) view.findViewById(R.id.responseView);
        Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
        return view;

    }

    /*
    * DOCUMENTATION:
    * Here we retrieved the REST API of USFA government online database
    * We retrieved a URL adter creating an account and them delivering this API key that can
    * only be used 3,500 times an hour.
    * */
    static final String API_KEY = "VbNcYXdkWBkrPwCOtVcIJbM4T01iQbmVzCpyP5V5";
    static final String API_URL = "https://api.nal.usda.gov/ndb/search/?format=json&q=";

    /*
    * DOCUMENTATION:
    * Here is the 'doTask' method that retrieves the first request of the API and searches
    * for the website along with the food entered by the user
    * */

    protected String doTask(Void... urls) {
        String food = text2.getText().toString();
        try {
            URL url = new URL(API_URL + food + "&sort=n&max=2&offset=0&api_key=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                /*
                * Here we use a bufferreader to stream the URL
                * after opening the url connection on the previous line
                * We then use a stringbuilder to append the lines of the URL so it is not NULL
                * then we close the bufferreader
                * */
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                /*
                * We create JSON object through the JavaScript of the website to navigate through the script
                * so we can retrive the 'ndbno key which is essential to retrive the ingredients information
                * */
                JSONObject jsonTitle = new JSONObject(stringBuilder.toString());
                JSONObject itemObject = jsonTitle.getJSONObject("list");
                JSONArray itemDetails = itemObject.getJSONArray("item");
                String ndbno = itemDetails.getJSONObject(0).getString("ndbno");
                return ndbno;
                // We disconnect from the URL
            } finally {
                urlConnection.disconnect();
            }
            // Throw Exception
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

/*
This Method is to check if the ingredient inputed by the user is in the ingredients of the food inputed by the user
*/
    public static boolean containsAllWords(String word, String ...keywords) {
        for (String k : keywords)
            if (!word.contains(k)) return false;
        return true;
    }


    /*
    * This is the RetrieveFeedTask
    * it is meant to offically abstract the data from the JavaScript and
    * implement with the progres bar
    * */
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;
// This is where it is pre executed  and the progress bar is visible
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            t3.setText("");
        }

// we call the database to retrieve the ingredients inputed by the user
        InglistActivity db = new InglistActivity(getContext());
        List lab = db.getAllLabels();

        // This is how we retrieve the information from the user
        // We set the 'ndbno' key to doTask() as a string to appy it to the URL
        //Then we get the ingredients inputed by the user and retrieve the most recent one to use for the ingredient display

        protected String doInBackground(Void... urls) {
            String ndbno = doTask();
            String ing = lab.get(lab.size()-1).toString().toUpperCase();


            try {
                URL url2 = new URL("https://api.nal.usda.gov/ndb/reports/?ndbno=" + ndbno + "&type=f&format=json&api_key=" + API_KEY);
                HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                try {
                    /*
                * Here we use a bufferreader to stream the URL
                * after opening the url connection on the previous line
                * We then use a stringbuilder to append the lines of the URL so it is not NULL
                * then we close the bufferreader
                * */
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));
                    StringBuilder stringBuilder2 = new StringBuilder();
                    String line2;
                    while ((line2 = bufferedReader2.readLine()) != null) {
                        stringBuilder2.append(line2).append("\n");
                    }
                    bufferedReader2.close();
                                    /*
                * We create JSON object through the JavaScript of the website to navigate through the script
                * so we can retrive the 'ndbno key which is essential to retrive the ingredients information
                * */
                    JSONObject jsonObject = new JSONObject(stringBuilder2.toString());
                    JSONObject jsonTitle2 = jsonObject.getJSONObject("report");
                    JSONObject food = jsonTitle2.getJSONObject("food");
                    JSONObject details = food.getJSONObject("ing");
                    String ingredients = details.getString("desc");
// Here we check to see if any ingredients are in the ingredients list FROM THE MOST RECENT ONE*
                        String[] temp = ing.split("");
                    if (containsAllWords(ingredients.toUpperCase(),temp) == true)
                            return "Your ingredients that match: " + ing;

                        return "Contains No Ingredients of your Interest!";

// Disconnect from the URL
                } finally {
                    urlConnection2.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }


/*
* DOCUMENTATION:
* this is post execute where the progressbar is gine and the information is retrived and displayed as far as this method
* goes
* If there is an error it shall notify you.
* */

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            t3.setText(response);

        }
    }
}


