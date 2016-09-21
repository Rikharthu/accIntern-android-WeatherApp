package com.accintern.ricardarmankuodis.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.accintern.ricardarmankuodis.weatherapp.weather.Currently;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import static com.accintern.ricardarmankuodis.weatherapp.R.id.textView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG=MainActivity.class.getSimpleName();
    public static final String API_KEY="f001bb783cc27374e9f473499f3b196f";

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,isNetworkAvailable()+"");
        mTextView= (TextView) findViewById(textView);
        new DownloadWebpageTask().execute(String.format(Locale.US,"https://api.darksky.net/forecast/%s/%f,%f",API_KEY,37.8627,-122.4233));

    }


    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
//            mTextView.setText(result);
            Log.d(TAG,"onPostExecute()");
            try {
                mTextView.setText(getCurrently(result));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        Log.d(TAG,"starting download");

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            // TODO check response
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            Log.d(TAG,"download finished");
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException{
        InputStreamReader inputStreamReader =  new InputStreamReader(stream, "UTF-8");
        BufferedReader bReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while((line = bReader.readLine()) != null){
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

    /** Check network availability */
    private boolean isNetworkAvailable() {
        // Get reference to connectivity service
        // Class that answers queries about the state of network connectivity.
        // It also notifies applications when network connectivity changes.
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // This method requires the caller to hold the permission ACCESS_NETWORK_STATE. (manifest)
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo !=null && networkInfo.isConnected()){
            isAvailable=true;
        }
        return isAvailable;
    }

    private String getCurrently(String str) throws JSONException {
//        String json = getResources().getString(R.string.mock_currently_json);
        JSONObject jsonForecast = new JSONObject(str);
        JSONObject jsonCurrently = jsonForecast.getJSONObject("currently");
        return jsonCurrently.getString("summary");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void parseForecast(String strForecast) throws JSONException{
        JSONObject jsonForecast = new JSONObject(strForecast);
        JSONObject jsonCurrently = jsonForecast.getJSONObject("currently");
    }

    private void parseCurrently(JSONObject jsonCurrently){

    }
}
