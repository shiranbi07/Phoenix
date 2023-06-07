package com.example.springboot;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Scanner;

public class Helper {

    public static CodeResolve getResponse(String path) throws IOException, InterruptedException {
        String srcFile = github(path);
        robertObj gptRes = robert(srcFile);
        String gptAnswer = gptRes.CorrectedCode;//github(path);

        Scanner gptReader = new Scanner(gptAnswer);
        Scanner srcReader = new Scanner(srcFile);

        StringBuilder response = new StringBuilder();
        String gptLine;
        String srcLine;

        response.append("<!DOCTYPE html>").append("\n");
        response.append("<html>").append("\n");
        response.append("<body>").append("\n");

        int counter=0;
        gptReader.nextLine();
        while (srcReader.hasNext() && gptReader.hasNext()) {
            srcLine = srcReader.nextLine();
            gptLine = gptReader.nextLine();
            if (srcLine.equals(gptLine)) {
                response.append("<p>").append(gptLine).append("</p>").append("\n");
            } else {
                response.append("<p style='color:red; font-weight:bold'>").append(srcLine).append("</p>").append("\n");
                response.append("<p style='color:green; font-weight:bold'>").append(gptLine).append("</p>").append("\n");
                counter++;
            }
        }

        response.append("</html>").append("\n");
        response.append("</body>").append("\n");

        srcReader.close();
        gptReader.close();

        return new CodeResolve(response.toString(),gptRes.Remarks,counter);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CodeResolve s = getResponse("https://github.com/shiranbi07/Phoenix/blob/main/python_examples/concatenate_numbers.py");
//        robert(s);
    }

    private static String github(String path) {
        path = path.replace("blob/", "");
        String gitPath = path.replace("github.com", "raw.githubusercontent.com");

        RestTemplate rest = new RestTemplate();
        return rest.getForObject(gitPath, String.class);
    }

    private static robertObj robert(String text) throws IOException, InterruptedException {

        String gitPath = "http://localhost:5000/FindBugs/FromText";

        String encoded64 = Base64.getEncoder().encodeToString(text.getBytes());
        textToSend textEn = new textToSend(encoded64);


        RestTemplate rest = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(textEn),httpHeaders);
        String s = rest.postForObject(gitPath, httpEntity, String.class);


        return new Gson().fromJson(s, robertObj.class);
    }

    public class robertObj{
        String CorrectedCode;
        String Remarks;
    }
}
