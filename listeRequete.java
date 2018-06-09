package com.droidmentor.mouroujnaClean;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listeRequete extends AppCompatActivity {


    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://radhwen.alwaysdata.net/Api.php";
    private ProgressDialog pDialog;
    //a list to store all the products
    List<requete> requeteList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_requete);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        requeteList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        displayLoader();
        loadProducts();
        pDialog.dismiss();
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(listeRequete.this);
        pDialog.setMessage("الرجاء الإنتظار قليلا...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject requete = array.getJSONObject(i);

                                //adding the product to product list
                                requeteList.add(new requete(
                                        requete.getInt("id"),
                                        requete.getString("nom"),
                                        requete.getString("req"),
                                        requete.getString("date"),
                                        requete.getString("etat"),
                                        requete.getString("image").trim()
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            RequeteAdapter adapter = new RequeteAdapter(listeRequete.this, requeteList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
