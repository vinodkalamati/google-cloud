package com.stackroute.datapopulator.service;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@PropertySource("classpath:application.properties")
public class QueryTriggerService {
    @Value("${url}")
    private String url;
    public void queryServiceTrigger(String query,String domain) throws IOException {
        URL object=new URL(url);
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        JSONObject cred   = new JSONObject();
        cred.put("searchTerm",query);
        cred.put("domain",domain);
        OutputStreamWriter outputStreamer= new OutputStreamWriter(con.getOutputStream());
        outputStreamer.write(cred.toString());
        outputStreamer.flush();
        outputStreamer.close();

    }
}
