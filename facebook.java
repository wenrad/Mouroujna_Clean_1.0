package com.droidmentor.mouroujnaClean;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class facebook extends AppCompatActivity {


    @BindView(R.id.btnconnect)
    Button btnconnect;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.txterror)
    TextView txterror;
    @BindView(R.id.rlPickLocation)
    RelativeLayout rlPick;
    @BindView(R.id.pswd)
    EditText pswd;
    @BindView(R.id.login_button)
    LoginButton loginButton;


        private CallbackManager callbackManager;
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

        static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //  setContentView(R.layout.activity_facebook);
        ButterKnife.bind(this);
        /*FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               /* info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );



            }

            @Override
            public void onCancel() {
                //info.setText("Authentification annulée .");
            }

            @Override
            public void onError(FacebookException e) {

            }



            //LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"));
        });
       if ((!email.getText().toString().isEmpty())&&(!pswd.getText().toString().isEmpty())){
            btnconnect.setEnabled(true);
        }

    }


    public void btnClick(View v){
        if((email.getText().toString().equals("radhwen.khadhri@gmail.com"))&&(pswd.getText().toString().equals("123456"))){
            Intent Intent1 = new Intent(facebook.this,MyLocationUsingLocationAPI.class);
            startActivity(Intent1);
        }else{
            txterror.setVisibility(View.VISIBLE);
        }


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
*/
    }
}
