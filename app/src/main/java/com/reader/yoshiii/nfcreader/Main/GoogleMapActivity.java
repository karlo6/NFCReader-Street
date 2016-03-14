package com.reader.yoshiii.nfcreader.Main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.reader.yoshiii.nfcreader.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


public class GoogleMapActivity extends Activity {

    // Google Map
    private GoogleMap googleMap;
    /**Spinner mSprPlaceType;*/

    String[] mPlaceType=null;
    String[] mPlaceTypeName=null;

    double placeLat, placeLon;
    String currentLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        try {
            // Loading map
            initializeMap();
            initializeVariables();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Initializes variables of the class

    private void initializeVariables(){

        // Array of place types
        mPlaceType = getResources().getStringArray(R.array.place_type);

        // Array of place type names
        mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);

        // Creating an array adapter with an array of Place types
        // to populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);

        // Getting reference to the Spinner
        /**mSprPlaceType = (Spinner) findViewById(R.id.spr_place_type);*/

        // Setting adapter on Spinner to set place types
        /**mSprPlaceType.setAdapter(adapter);*/

       Button btnFind;

        // Getting reference to Find Button
        btnFind = ( Button ) findViewById(R.id.btn_find);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected()) {

                    /**int selectedPosition = mSprPlaceType.getSelectedItemPosition();
                    String type = mPlaceType[selectedPosition];*/

                    StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                    sb.append("location=" + placeLat + "," + placeLon);
                    sb.append("&radius=1000");
                    /**sb.append("&types=" + type);*/
                    sb.append("&sensor=true");
                    sb.append("&key=AIzaSyCrDazss1GrzgPtH5wQS1-IV0h9TAhdfPw");

                    Log.e("Place finder: ", sb.toString());

                    // Creating a new non-ui thread task to download json data
                    PlacesTask placesTask = new PlacesTask();

                    // Invokes the "doInBackground()" method of the class PlaceTask
                    placesTask.execute(sb.toString());

                }

                else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(GoogleMapActivity.this);
                    dialog.setTitle("Notice");
                    dialog.setMessage("Must be connected to the internet.");
                    dialog.setNeutralButton("Okay, I got it.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }

            }
        });

    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.e("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    /** A class, to download Google Places */
    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.e("Background Task",e.toString());
            }

            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result){
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParseTask
            parserTask.execute(result);
        }

    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            PlaceJsonParser placeJsonParser = new PlaceJsonParser();

            try{
                jObject = new JSONObject(jsonData[0]);

                /** Getting the parsed data as a List construct */
                places = placeJsonParser.parse(jObject);

                Log.e("Places: ", places.toString());

            }catch(Exception e){
                Log.e("Exception",e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list){

            googleMap.clear();
            placeMarker(placeLat, placeLon, currentLoc);

            // Clears all the existing markers

            for(int i=0;i<list.size();i++){
                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);

                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");

                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");

                LatLng latLng = new LatLng(lat, lng);

                // Setting the position for the marker
                markerOptions.position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                // Setting the title for the marker.
                //This will be displayed on taping the marker
                markerOptions.title(name + " : " + vicinity);

                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);


            }
        }
    }

    /**
     * Function to load map. If map is not created it will create it for you
     * */

    private void initializeMap() {

        placeLat = 14.5906;
        placeLon = 120.9761;
        currentLoc = "Intramuros";



        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }

        else {

            Log.e("Google MAP: ", "Available");

            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();

                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    placeMarker(placeLat, placeLon, currentLoc);

                }

            }

        }
    }

    //Method for creating markers on the map

    private void placeMarker(double latitude, double longitude, String placeName){

        // Create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(placeName);


        // Adding marker
        googleMap.addMarker(marker);
        LatLng FortSan = new LatLng(14.5950,120.9694);
        googleMap.addMarker(new MarkerOptions().position(FortSan).title("Fort Santiago").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        // Landmarks
        LatLng MnlCth = new LatLng(14.5915,120.9736);
        googleMap.addMarker(new MarkerOptions().position(MnlCth).title("Manila Cathedral").snippet("Operating Hours:7:00am-7:00pm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng SnAgus = new LatLng(14.5889,120.9753);
        googleMap.addMarker(new MarkerOptions().position(SnAgus).title("San Agustin Church").snippet("Operating Hours:8:00am-6:00pm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng Plza = new LatLng(14.5945,120.9701);
        googleMap.addMarker(new MarkerOptions().position(Plza).title("Plaza De Armas").snippet("Opens daily").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng Adu = new LatLng(14.5938, 120.9744);
        googleMap.addMarker(new MarkerOptions().position(Adu).title("Aduana Building").snippet("Opens daily").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng Par = new LatLng(14.5922, 120.9780);
        googleMap.addMarker(new MarkerOptions().position(Par).title("Puerta del Parian").snippet("Opens daily").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng Cas = new LatLng(14.5884, 120.9737);
        googleMap.addMarker(new MarkerOptions().position(Cas).title("Cuartel de Santa Lucia").snippet("Opens daily").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        // Universities & School
        LatLng let = new LatLng(14.5932, 120.9766);
        googleMap.addMarker(new MarkerOptions().position(let).title("Colegio De San Juan De Letran").snippet("Contact no. (02) 525 0398").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        LatLng lyc = new LatLng(14.5913, 120.9775);
        googleMap.addMarker(new MarkerOptions().position(lyc).title("Lyceum of the Philippines University").snippet("Contact no. (02) 527 8251").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        LatLng map = new LatLng(14.5902, 120.9786);
        googleMap.addMarker(new MarkerOptions().position(map).title("MAPUA Institute of Technology").snippet("Contact no. (02) 247 5000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        LatLng plm = new LatLng(14.5873, 120.9758);
        googleMap.addMarker(new MarkerOptions().position(plm).title("Pamantasan Lungsod ng Maynila").snippet("Contact no. (02) 527 7941").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        LatLng mhs = new LatLng(14.5896, 120.9788);
        googleMap.addMarker(new MarkerOptions().position(mhs).title("Manila High School").snippet("Opens: Monday - Friday").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        // Establishment
        LatLng mnb = new LatLng(14.5877, 120.9783);
        googleMap.addMarker(new MarkerOptions().position(mnb).title("Manila Bulletin").snippet("Contact no: (632)527-8121").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng bay = new LatLng(14.5900, 120.9786);
        googleMap.addMarker(new MarkerOptions().position(bay).title("Bayleaf").snippet("Contact no: +632 318-5000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        animateCamera(new LatLng(latitude, longitude));

        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(1000)
                .strokeWidth(0f)
                .fillColor(0x550000FF));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Log.e("ASD", marker.getTitle());

                if (!"Intramuros".equals(marker.getTitle())) {

                    googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(final Marker marker) {

                            View v = getLayoutInflater().inflate(R.layout.info_snippet_window, null);
                            ImageView photo = (ImageView) v.findViewById(R.id.photoPlace);
                            TextView title = (TextView) v.findViewById(R.id.titlePlace);
                            TextView snippet = (TextView) v.findViewById(R.id.snippetPlace);

                            switch (marker.getTitle()) {

                                case "Fort Santiago":

                                    photo.setImageResource(R.drawable.forx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Manila Cathedral":

                                    photo.setImageResource(R.drawable.manx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "San Agustin Church":

                                    photo.setImageResource(R.drawable.sanx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Plaza De Armas":

                                    photo.setImageResource(R.drawable.plazax);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Aduana Building":

                                    photo.setImageResource(R.drawable.adux);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Puerta del Parian":

                                    photo.setImageResource(R.drawable.parx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Cuartel de Santa Lucia":

                                    photo.setImageResource(R.drawable.casx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Colegio De San Juan De Letran":

                                    photo.setImageResource(R.drawable.letx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Lyceum of the Philippines University":

                                    photo.setImageResource(R.drawable.lpux);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "MAPUA Institute of Technology":

                                    photo.setImageResource(R.drawable.mapx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Pamantasan Lungsod ng Maynila":

                                    photo.setImageResource(R.drawable.plmx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Manila High School":

                                    photo.setImageResource(R.drawable.mhsx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Manila Bulletin":

                                    photo.setImageResource(R.drawable.mnbx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                                case "Bayleaf":

                                    photo.setImageResource(R.drawable.bayx);
                                    title.setText(marker.getTitle());
                                    snippet.setText(marker.getSnippet());

                                    break;

                            }

                            return v;
                        }
                    });

                    return false;

                } else {

                    return true;

                }
            }


        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                StreetViewActivity.streetView = marker.getPosition();

                Intent intent = new Intent(GoogleMapActivity.this, StreetViewActivity.class);
                startActivity(intent);
            }
        });

    }


    //Moves the camera to pinpointed location

    private void animateCamera(LatLng location){
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    //Checks internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeMap();
    }
}
