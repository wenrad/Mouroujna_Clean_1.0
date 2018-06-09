package com.droidmentor.mouroujnaClean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.btnnew)Button btnnew;
    @BindView(R.id.btnlist)Button btnlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        btnnew.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intentSignUP = new Intent(MenuActivity.this, MyLocationUsingLocationAPI.class);


                startActivity(intentSignUP);

            }
        });
        btnlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intentSignUP = new Intent(MenuActivity.this, listeRequete.class);


                startActivity(intentSignUP);

            }
        });

    }

}
