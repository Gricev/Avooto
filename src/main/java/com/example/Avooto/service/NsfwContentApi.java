package com.example.Avooto.service;


import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NsfwContentApi {

    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("application/json");
    String value = "{\r\"url\": \"https://images.unsplash.com/photo-1602911429311-1c56a6c42a81?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80\"\r }";
        RequestBody body = RequestBody.create(mediaType, value);
        Request request = new Request.Builder()
        .url("https://nsfw-images-detection-and-classification.p.rapidapi.com/adult-content")
        .post(body)
        .addHeader("content-type", "application/json")
        .addHeader("X-RapidAPI-Key", "17ba4a85dbmsh45fa0e34c48bdc0p19488ajsne827623d9f28")
        .addHeader("X-RapidAPI-Host", "nsfw-images-detection-and-classification.p.rapidapi.com")
        .build();

        Response response;

    {
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
