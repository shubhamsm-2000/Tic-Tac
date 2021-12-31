package com.example.tictac;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.ads.mediationtestsuite.dataobjects.Caption;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
// import edmt.dev.edmtdevcognitivevision.Contract.Caption;
// import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;

public class ImageAnalysis<VisionServiceClient> extends AppCompatActivity {

 /*       ImageView imageView;
        Button btnProcess;
        Button btnCapture;
        TextView txtResult;

        private final String API_KEY = "feb245f588174bb1b468c5931170b3dc";
        private final String API_LINK = "https://celebplayer.cognitiveservices.azure.com/";


        //Declare Vision Client
     private VisionServiceClient visionServiceClient= new VisionServiceRestCLIent(API_KEY, API_LINK);
*/

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_analysis);
    }}
     /*   Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.main);
        ImageView img= findViewById(R.id.celeb);
        img.setImageBitmap(bitmap);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        Button buttonprocess=findViewById(R.id.button3);
        buttonprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak") AsyncTask<InputStream,String,String> recognizeCelebrities= new AsyncTask<InputStream, String, String>() {

                    AlertDialog spotDialog= new SpotsDialog.Builder().setContext(ImageAnalysis.this).build();

                    @Override
                    protected  void onPreExecute(){
                        spotDialog.show();
                    }

                    @Override
                    protected  void onProgressUpdate(String... values){
                        spotDialog.setMessage(values[0]);
                    }

                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected String doInBackground(InputStream... params) {
                        try{
                            publishProgress("Detecting...");
                            String[] features = {"Categories"};
                            String[] details = {"Celebrities"};

                            CelebritiesResult celebritiesResult= visionServiceClient.detectCelebrities(params[0]);
                            String jsonResult=new Gson().toJson(CelebritiesResult);
                            return jsonResult;
                        }
                        catch (Exception ex){
                            return "[ERROR]"+ex.getMessage();
                        }
                    }
                    protected void onPostExecute(String s){
                        if(spotDialog !=null && spotDialog.isShowing())
                            spotDialog.dismiss();
                        if(s.contains("[ERROR]"))
                            Toast.makeText(ImageAnalysis.this,s, Toast.LENGTH_SHORT).show();
                        else
                            CelebritiesResult result= new Gson.fromJson(s, CelebritiesResult.class);
                        TextView txtDescrip=findViewById(R.id.celebResult);

                        StringBuilder stringBuilder= new StringBuilder();

                        for(CelebritiesCategory category:result.categories){

                            for (Celebrity celebrity:category.detail.celebrities){
                                stringBuilder.append(celebrity.name).append("\n");
                            }
                            txtDescrip.setText(stringBuilder.toString());
                        }
                    }
                };
                recognizeCelebrities.execute(inputStream);
            }
        });




    }


        imageView = (ImageView)findViewById(R.id.image_view_object);
            btnCapture = (Button)findViewById(R.id.btn_capture_object);
            btnProcess = (Button)findViewById(R.id.btn_process_object);
            txtResult = (TextView)findViewById(R.id.text_result_object);



            //Get Bitmap and add to Image View
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.healthcare);
            btnProcess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    //Convert Bitmap to ByteArray
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


                    //Use async task to request API
                     @SuppressLint("StaticFieldLeak") AsyncTask<InputStream,String,String> visionTask = new AsyncTask<InputStream, String, String>() {

                        ProgressDialog progressDialog = new ProgressDialog(ImageAnalysis.this);

                        @Override
                        protected void onPreExecute() {
                            progressDialog.show();
                        }

                        @Override
                        protected String doInBackground(InputStream... inputStreams) {
                            publishProgress("Recognizing...");
                            String[] features = {"Description"}; // Get description from API return result
                            String[] details={};
                            AnalysisResult result = visionServiceClient.analyzeimage(inputStreams[0],features,details);
                            String jsonResult = new Gson().toJson(result);
                            return jsonResult;
                        }

                        @Override
                        protected void onPostExecute(String s) {

                            AnalysisResult result = new Gson().fromJson(s,AnalysisResult.class);
                            StringBuilder result_Text = new StringBuilder();
                            for (Caption caption:result.description.captions)
                                result_Text.append(caption.text);
                            txtResult.setText(result_Text.toString());
                        }

                        @Override
                        protected void onProgressUpdate(String... values) {
                            progressDialog.setMessage(values[0]);
                        }
                    };

                    //Run Task Vision
                    visionTask.execute(inputStream);

                }
            });

            //ON CLICK LISTENER FOR "CAPTURE IMAGE" BUTTON//
            btnCapture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);


        }
    }

*/



