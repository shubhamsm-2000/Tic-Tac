package com.example.tictac;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class computerVision extends AppCompatActivity {

    ImageView imageView;
    Button btnProcess;
    Button btnCapture;
    TextView txtResult;

    private final String API_KEY = "feb245f588174bb1b468c5931170b3dc";
    private final String API_LINK = "https://celebplayer.cognitiveservices.azure.com/";

    VisionServiceClient visionServiceClient = new VisionServiceRestClient(API_KEY,API_LINK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_analysis);

        imageView = (ImageView)findViewById(R.id.image_view_object);
        btnCapture = (Button)findViewById(R.id.btn_capture_object);
        btnProcess = (Button)findViewById(R.id.btn_process_object);
        txtResult = (TextView)findViewById(R.id.text_result_object);


        //Get Bitmap and add to Image View
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img);
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

                    final ProgressDialog progressDialog = new ProgressDialog(computerVision.this);

                    @Override
                    protected void onPreExecute() {
                        progressDialog.show();
                    }

                    @Override
                    protected String doInBackground(InputStream... inputStreams) {
                        publishProgress("Recognizing...");
                        String[] features = {"Description"}; // Get description from API return result
                        String[] details={};
                        AnalysisResult result = null;
                        try {
                            result = visionServiceClient.analyzeImage(inputStreams[0],features,details);
                        } catch (VisionServiceException | IOException e) {
                            e.printStackTrace();
                        }
                        final String s = new Gson().toJson(result);
                        return s;
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

        assert data != null;
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);




    }
}