package com.example.michelle.veranos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class webService {
    public static String getData(String urlString){
        URL url;
        HttpURLConnection conn=null;
        try {
            url = new URL(urlString);
            conn =(HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.connect();
            InputStream stream= conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine())!=null)
                response.append(inputLine);
            in.close();
            return response.toString();
        }catch (IOException e){
            e.printStackTrace();

        }finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }
}
