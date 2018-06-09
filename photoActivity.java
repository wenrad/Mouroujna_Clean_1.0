package com.droidmentor.mouroujnaClean;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
public class photoActivity extends AppCompatActivity implements View.OnClickListener {

    //String img;
    ImageView ivAttachment;
    Button bUpload, btnphoto;
    TextView tvFileName;
    ProgressDialog dialog;
    public static final String UPLOAD_URL = "http://radhwen.alwaysdata.net/ajout.php";
    public static final String UPLOAD_KEY1 = "nom";
    public static final String UPLOAD_KEY2 = "prenom";
    public static final String UPLOAD_KEY3 = "req";
    public static final String UPLOAD_KEY4 = "tel";
    public static final String UPLOAD_KEY5 = "lg";
    public static final String UPLOAD_KEY6= "alt";

    public static final String UPLOAD_KEY7 = "type";
    public static final String UPLOAD_KEY8 = "etat";
    public static final String UPLOAD_KEY9 = "id_pers";
    public static final String UPLOAD_KEY10 = "date";
    public static final String UPLOAD_KEY11 = "image";



    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

// String img;
    private Bitmap bitmap;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        bUpload = (Button) findViewById(R.id.b_upload);
        btnphoto = (Button) findViewById(R.id.btnphoto);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        ivAttachment.setOnClickListener(this);
        bUpload.setOnClickListener(this);
        btnphoto.setOnClickListener(this);
    }
    public void getCurrentDate(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= mdformat.format(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        if (v == ivAttachment) {

            //on attachment icon click
            showFileChooser();
        }
        if (v == btnphoto) {
            Intent prendrePhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            startActivity(prendrePhoto);

        }
        if (v == bUpload) {
            Bundle b = getIntent().getExtras();
            String nom = b.getString("nom");
            String req = b.getString("req");
            String prenom = b.getString("prenom");
            String tel = b.getString("tel");
            String alt = b.getString("lat");
            String lg = b.getString("lang");
            String type = b.getString("type");
            //String image = b.getString("image");
           // Toast.makeText(photoActivity.this, img, Toast.LENGTH_SHORT).show();
            String img = getStringImage(bitmap);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate= mdformat.format(calendar.getTime());
            new CreateNewTree().execute(nom,req,prenom,tel,alt,lg,type,"1",strDate,img,"1");

            Intent i = new Intent(getApplicationContext(), finishActivity.class);
            startActivity(i);


            //on upload button Click
           // uploadImage();


        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivAttachment.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    class CreateNewTree extends AsyncTask<String, Void, String> {
        ProgressDialog loading;
        RequestHandler rh = new RequestHandler();
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
            //String data;

            BufferedReader bufferedReader;
            String result;

            HashMap<String,String> data = new HashMap<>();
            data.put(UPLOAD_KEY1, nom);
            data.put(UPLOAD_KEY2, prenom);
            data.put(UPLOAD_KEY3, req);
            data.put(UPLOAD_KEY4, tel);
            data.put(UPLOAD_KEY5, lg);
            data.put(UPLOAD_KEY6, alt);
            data.put(UPLOAD_KEY7, type);
            data.put(UPLOAD_KEY8, etat);
            data.put(UPLOAD_KEY9, id_pers);
            data.put(UPLOAD_KEY10, date);
            data.put(UPLOAD_KEY11, image);


                 result = rh.sendPostRequest(UPLOAD_URL,data);
               /* data = "?nom=" + URLEncoder.encode(nom, "UTF-8");
                data += "&prenom=" + URLEncoder.encode(prenom, "UTF-8");
                data += "&req=" + URLEncoder.encode(req, "UTF-8");
                data += "&tel=" + URLEncoder.encode(tel, "UTF-8");
                data += "&alt=" + URLEncoder.encode(alt, "UTF-8");
                data += "&lg=" + URLEncoder.encode(lg, "UTF-8");
                data += "&type=" + URLEncoder.encode(type, "UTF-8");
                data += "&etat=" + URLEncoder.encode(etat, "UTF-8");
                data += "&date=" + URLEncoder.encode(date, "UTF-8");
                data += "&image=" + URLEncoder.encode(image, "UTF-8");
                data += "&id_pers=" + URLEncoder.encode(id_pers, "UTF-8");

                Toast.makeText(photoActivity.this, data, Toast.LENGTH_SHORT).show();


                link = "http://radhwen.alwaysdata.net/ajout.php" + data;
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                result = bufferedReader.readLine();*/
                return result;
            /*} catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }*/
        }

        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(photoActivity.this, "Uploading Image", "Please wait...",true,true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading.dismiss();

        }
    }

                 //finalResult=s;
                //Toast.makeText(ConfirmationActivity.this, image, Toast.LENGTH_SHORT).show();



           /* public String getFinalResult() {
                return finalResult;
            }*/


          /*  protected String doInBackground(Bitmap... params) {
          ;
                String result = getStringImage(bitmap);



                return result;

            }*/





}

