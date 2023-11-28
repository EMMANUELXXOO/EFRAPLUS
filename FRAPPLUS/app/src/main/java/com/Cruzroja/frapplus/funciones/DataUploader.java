package com.Cruzroja.frapplus.funciones;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DataUploader {

    public static void uploadData(String jsonData) {
        String url = "http://tu_servidor/tu_script.php";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) apiUrl.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write("json_data=" + URLEncoder.encode(jsonData, "UTF-8"));
            writer.flush();
            writer.close();
            outputStream.close();

            // Aquí puedes manejar la respuesta del servidor, si es necesario
            int responseCode = urlConnection.getResponseCode();
            // ...

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



