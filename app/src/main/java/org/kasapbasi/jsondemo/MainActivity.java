package org.kasapbasi.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class GetJson extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(strings[0]);

                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                InputStream is= con.getInputStream();
                InputStreamReader reader= new InputStreamReader(is);
                StringBuilder sb= new StringBuilder("");
               int a=reader.read();
                while(a!=-1){
                    sb.append((char) a);
                    a=reader.read();
                }

                return  sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str= "";
        GetJson gj= new GetJson();
//api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
        try {
            str= gj.execute("https://api.openweathermap.org/data/2.5/weather?q=trabzon&appid=1d8e22761ea89c1ff5f3229a813eee9c").get();
        Log.d("HAVA_JSON",str);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
