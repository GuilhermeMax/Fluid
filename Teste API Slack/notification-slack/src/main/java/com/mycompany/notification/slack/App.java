/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.notification.slack;

import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author guilherme.ssantos
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        JSONObject json = new JSONObject();
        
        json.put("text", "Sou o bot da Fluid, pode me chamar de Fluidinho! :smirk: ");
        Slack.enviarMensagem(json);
    }
}
