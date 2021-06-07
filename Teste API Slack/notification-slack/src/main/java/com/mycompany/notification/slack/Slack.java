package com.mycompany.notification.slack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;


public class Slack {
    
    //UTILIZAMOS static PORQUE NÃO PRECISAREMOS INTANCIAR.
    
    //CLIENTE HTTP SERVIRÁ PARA EXECUTAR ALGUMAS REQUISIÇÕES HTTP
    //      E CAPTURAR RESPOSTAS DELAS, SE HOUVEREM...
    private static HttpClient client = HttpClient.newHttpClient();
    
    //ELE SERÁ FINAL PORQUE NÃO SE DEVE MUDAR, SERÁ O MESMO SEMPRE.
    private static final String url = "https://hooks.slack.com/services/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";

    // RECEBENDO UM JSON;
    public static void enviarMensagem(JSONObject content)throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)) //recebe a url do webhook;
                .header("accept", "application/json") //Configuração da requisição;
                .POST(HttpRequest.BodyPublishers.ofString(content.toString())) //Convertendo o JSON para String;
                .build();
        
        //o Send "jogará" a excessão, com esses parametros.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        //Todo HttpRequest que for mandado retrna um código e se tiver um erro, ele trará o erro.
        System.out.println(String.format("Status: %s", response.statusCode()));
        
        //Alguns HttpRequest retornam em JSON e com isso recuperamos a informação necessária.
        System.out.println(String.format("Response %s", response.body()));
        
    }
}
