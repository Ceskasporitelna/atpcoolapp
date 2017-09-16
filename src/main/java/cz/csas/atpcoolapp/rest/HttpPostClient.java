package cz.csas.atpcoolapp.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// import javax.net.ssl.HttpsURLConnection;

public class HttpPostClient {

    private final String USER_AGENT = "AgroFART agent";

    // HTTP POST request
    public void sendPost(String url, String data) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("cache-control", "no-cache");
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("postman-token", "6bc4b2d3-6c41-b4ad-15c1-a52375cc8bd0");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}