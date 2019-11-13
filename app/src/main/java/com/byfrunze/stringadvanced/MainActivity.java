package com.byfrunze.stringadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnVar1;
    private Button btnVar2;
    private Button btnVar3;
    private Button btnVar4;

    private int truthRand;
    private String site = "http://www.posh24.se/kandisar";

    private RegexSite regexSite;
    private Random random;
    private Button[] btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewAvatar);
        btnVar1 = findViewById(R.id.btnVar1);
        btnVar2 = findViewById(R.id.btnVar2);
        btnVar3 = findViewById(R.id.btnVar3);
        btnVar4 = findViewById(R.id.btnVar4);

        regexSite = new RegexSite();
        regexSite.getInfo(site);

        btnList = new Button[] {btnVar1, btnVar2, btnVar3, btnVar4};

        random = new Random();
        truthRand = random.nextInt(regexSite.getImageArrayList().size() - 1) ;

        imageView.setImageBitmap(setImage(regexSite.getImageArrayList().get(truthRand)));
        for(Button btn: btnList){
            btn.setText(regexSite.getNameArrayList().get(random.nextInt(regexSite.getImageArrayList().size() - 1)));
        }
        btnList[random.nextInt(3)].setText(regexSite.getNameArrayList().get(truthRand));

    }

    private static class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL urls = null;
            HttpURLConnection urlConnection = null;
            try {
                urls = new URL(strings[0]);
                urlConnection = (HttpURLConnection) urls.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }
    }

    private Bitmap setImage(String urlImage){
        DownloadImage downloadImage = new DownloadImage();
        Bitmap bitmap = null;
        try {
            bitmap = downloadImage.execute(urlImage).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public void checkTruth(View view)  {
        switch (view.getId()){
            case R.id.btnVar1:
                if(btnVar1.getText().equals(regexSite.getNameArrayList().get(truthRand))) {
                    btnVar1.setBackgroundColor(Color.BLUE);
                    Toast.makeText(this, "Its True",Toast.LENGTH_SHORT).show();
                    newQuest();
                } else btnVar1.setBackgroundColor(Color.RED);
                break;
            case R.id.btnVar2:
                if(btnVar2.getText().equals(regexSite.getNameArrayList().get(truthRand))) {
                    btnVar2.setBackgroundColor(Color.BLUE);
                        Toast.makeText(this, "Its True",Toast.LENGTH_SHORT).show();
                    newQuest();
                } else btnVar2.setBackgroundColor(Color.RED);
                break;
            case R.id.btnVar3:
                if(btnVar3.getText().equals(regexSite.getNameArrayList().get(truthRand))) {
                    btnVar3.setBackgroundColor(Color.BLUE);
                    Toast.makeText(this, "Its True",Toast.LENGTH_SHORT).show();
                    newQuest();
                }else btnVar3.setBackgroundColor(Color.RED);
                break;
            case R.id.btnVar4:
                if(btnVar4.getText().equals(regexSite.getNameArrayList().get(truthRand))) {
                    btnVar4.setBackgroundColor(Color.BLUE);
                    Toast.makeText(this, "Its True",Toast.LENGTH_SHORT).show();
                    newQuest();
                } else btnVar4.setBackgroundColor(Color.RED);
                break;
        }
    }

    private void newQuest(){
        truthRand = random.nextInt(regexSite.getNameArrayList().size() - 1) ;

        imageView.setImageBitmap(setImage(regexSite.getImageArrayList().get(truthRand)));
        for(Button btn: btnList){
            btn.setText(regexSite.getNameArrayList().get(random.nextInt(regexSite.getNameArrayList().size() - 1)));
        }
        btnList[random.nextInt(3)].setText(regexSite.getNameArrayList().get(truthRand));
        for(Button btn : btnList){
            btn.setBackgroundColor(Color.GRAY);
        }
    }

}
