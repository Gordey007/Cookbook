package gordey.cookbook;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gorde_000 on 27.04.2018.
 */

public abstract class MyHttpRequestTaskPost extends AsyncTask<String,Integer,String> {

    abstract void doGet(String s);

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        doGet(s);

//        for (RecipesJ recipesJ: array) {
//
//            Log.d("HTTP", "from json : " + recipesJ.recipe);
//        }
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("HTTP", "Start");
        String my_url = params[0];
        String my_data = params[1];
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(my_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // setting the Request Method Type
            httpURLConnection.setRequestMethod("POST");
            // adding the headers for request
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            try{
                //to tell the connection object that we will be wrting some data on the server and then will fetch the output result
                httpURLConnection.setDoOutput(true);
                // this is used for just in case we don't know about the data size associated with our request
                httpURLConnection.setChunkedStreamingMode(0);

                // to write tha data in our request
                OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(my_data);
                outputStreamWriter.flush();
                outputStreamWriter.close();

                // to log the response code of your request
                Log.d("HTTP", "MyHttpRequestTask getResponseCode : " +httpURLConnection.getResponseCode());
                // to log the response message from your server after you have tried the request.
                Log.d("HTTP", "MyHttpRequestTask getResponseMessage : " +httpURLConnection.getResponseMessage());

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()), 8);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();

                Log.d("HTTP", "ResponseMessage : " + content.toString());


            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return content.toString();
    }

}
