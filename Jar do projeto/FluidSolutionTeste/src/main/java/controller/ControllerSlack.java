/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;



/**
 *
 * @author miguel.assuncao
 */
public class ControllerSlack {
    
    private static HttpClient client = HttpClient.newHttpClient();
    private static final String url = "https://hooks.slack.com/services/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";
    private static HttpResponse<String> response;
    
    public static void enviarMensagem(JSONObject content) throws IOException, InterruptedException{
        
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();
        
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(String.format("Status: %s", response.statusCode()));
        System.out.println(String.format("Response: %s", response.body()));
    }

    public static HttpResponse<String> getResponse() {
        return response;
    }
    
}
