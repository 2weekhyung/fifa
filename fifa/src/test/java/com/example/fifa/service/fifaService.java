package com.example.fifa.service;

import com.example.fifa.model.User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class fifaService {

    private String key ="test_ca3e45444908cc35bdbbd1cc718abf0c391a08154b98f3952c4fdbdb49d8038defe8d04e6d233bd35cf2fabdeb93fb0d";

    public User fifaId(String nickName){
            User user = new User();
        try {
            String urlString = "https://open.api.nexon.com//fconline/v1/id";

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", key);
            connection.setRequestProperty("nickName",nickName );
            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if(responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            user.setOuid(Integer.parseInt(response.toString()));
        } catch (Exception exception) {
            System.out.println(exception);
            user.setOuid(9999);
        }
            return user;

    }
}
