package com.droidmentor.mouroujnaClean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Formulaire extends AppCompatActivity {
    Button btnform;
    EditText nom,prenom,tel,req;
    RadioGroup rg;
    RadioButton anar,pol,lum,route;
    String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom_prec);
        tel = (EditText) findViewById(R.id.tel);
        req = (EditText) findViewById(R.id.req_rec);
        anar = (RadioButton) findViewById(R.id.radio_anar);
        lum = (RadioButton) findViewById(R.id.radio_lum);
        route = (RadioButton) findViewById(R.id.radio_route);
        pol = (RadioButton) findViewById(R.id.radio_pol);




        btnform = (Button) findViewById(R.id.btnform);
       rg = (RadioGroup) findViewById(R.id.radio_groupe);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_anar:
                        type="1";
                        break;
                    case R.id.radio_pol:
                        // do operations specific to this selection
                        type="2";
                        break;
                    case R.id.radio_lum:
                        // do operations specific to this selection
                        type="3";
                        break;
                    case R.id.radio_route:
                        // do operations specific to this selection
                        type="4";
                        break;
                }

            }
        });
       // final String nom1=nom.getText().toString();

        btnform.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub
        //Envoie des parametre formulaire
                final String nom1=nom.getText().toString();
                final String prenom1=prenom.getText().toString();
                final String tel1=tel.getText().toString();
                final String req1=req.getText().toString();
                Bundle b =getIntent().getExtras();

                String lat=b.getString("lat");
                String lang=b.getString("lang");

                /// Create Intent for SignUpActivity abd Start The Activity
                Intent intentSignUP = new Intent(Formulaire.this, photoActivity.class);
                intentSignUP.putExtra("nom",nom1);
                intentSignUP.putExtra("prenom",prenom1);
                intentSignUP.putExtra("tel",tel1);
                intentSignUP.putExtra("req",req1);
                intentSignUP.putExtra("type",type);

                intentSignUP.putExtra("lat", lat);
                intentSignUP.putExtra("lang", lang);

                startActivity(intentSignUP);
            }
        });


    }
}
