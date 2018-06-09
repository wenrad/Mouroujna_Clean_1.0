package com.droidmentor.mouroujnaClean;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.droidmentor.mouroujnaClean.R.id.alt_req;
import static com.droidmentor.mouroujnaClean.R.id.nom_rec;
import static com.droidmentor.mouroujnaClean.R.id.req_rec;
import static com.droidmentor.mouroujnaClean.R.id.tel_rec;
import static com.droidmentor.mouroujnaClean.R.layout.activity_confirmation;

public class ConfirmationActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    TextView nom_rec1, prenom_rec1, tel_rec1, req_rec1, alt_rec, lg_rec;
    Button btnconf;


    // url du web service de l'ajout

    // private static String url_create = "radhwen.alwaysdata.net/ajout.php";

    //private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_confirmation);
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        String req = b.getString("req");
        String prenom = b.getString("prenom");
        String tel = b.getString("tel");
        String alt = b.getString("lat");
        String lg = b.getString("lang");

        nom_rec1 = (TextView) findViewById(nom_rec);
        prenom_rec1 = (TextView) findViewById(R.id.prenom_rec);
        tel_rec1 = (TextView) findViewById(tel_rec);
        req_rec1 = (TextView) findViewById(req_rec);
        alt_rec = (TextView) findViewById(alt_req);
        lg_rec = (TextView) findViewById(R.id.lg_rec);
        btnconf = (Button) findViewById(R.id.btnconf);


        nom_rec1.setText(nom);
        prenom_rec1.setText(prenom);
        tel_rec1.setText(tel);
        req_rec1.setText(req);
        alt_rec.setText(alt);
        lg_rec.setText(lg);

        //inputName = (EditText) findViewById(R.id.editText);
        //inputPrice = (EditText) findViewById(R.id.editText2);


        btnconf.setOnClickListener(

                new View.OnClickListener()

                {

                    @Override

                    public void onClick(View view) {
                        Bundle b = getIntent().getExtras();
                        String nom = b.getString("nom");
                        String req = b.getString("req");
                        String prenom = b.getString("prenom");
                        String tel = b.getString("tel");
                        String alt = b.getString("lat");
                        String lg = b.getString("lang");
                        String image = b.getString("image");

                        new CreateNewTree().execute(nom,req,prenom,tel,alt,lg,"1","1","12",image,"1");
                    }
                });
    }


    class CreateNewTree extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... arg0) {


            String nom = arg0[0];
            String req = arg0[1];
            String prenom =arg0[2];
            String tel = arg0[3];
            String alt = arg0[4];
            String lg = arg0[5];
            String type = arg0[6];
            String etat = arg0[7];
            String date = arg0[8];
            String image = arg0[9];
            String id_pers = arg0[10];
            String link;
            String data;
            BufferedReader bufferedReader;
            String result;

            try {

                data = "?nom=" + URLEncoder.encode(nom, "UTF-8");
                data += "&prenom=" + URLEncoder.encode(prenom, "UTF-8");
                data += "&req=" + URLEncoder.encode(req, "UTF-8");
                data += "&tel=" + URLEncoder.encode(tel, "UTF-8");
                data += "&alt=" + URLEncoder.encode(alt, "UTF-8");
                data += "&lg=" + URLEncoder.encode(lg, "UTF-8");
                data += "&type=" + URLEncoder.encode(type, "UTF-8");
                data += "&etat=" + URLEncoder.encode(etat, "UTF-8");
                data += "&date=" + URLEncoder.encode(date, "UTF-8");
                data += "&image="+ URLEncoder.encode(image, "UTF-8");
                data += "&id_pers=" + URLEncoder.encode(id_pers, "UTF-8");



                link = "http://radhwen.alwaysdata.net/ajout.php" + data;
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                result = bufferedReader.readLine();
                return result;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String jsonStr = result;

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString("query_result");

                    if (query_result.equals("SUCCESS")) {
                        Toast.makeText(ConfirmationActivity.this, "Data inserted successfully. Signup successful.", Toast.LENGTH_SHORT).show();
                    } else if (query_result.equals("FAILURE")) {
                        Toast.makeText(ConfirmationActivity.this, "Data could not be inserted. Signup failed.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConfirmationActivity.this, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ConfirmationActivity.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ConfirmationActivity.this, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}