package com.example.springboot;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.chatgpt.;
public class Helper {

    public static String getResponse(String path) throws IOException {
//        String file = "C:\\Users\\e177118\\Downloads\\gs-spring-boot-main\\gs-spring-boot-main\\complete\\src\\main\\java\\com\\example\\springboot\\Application.java";
        String file = github(path);
        String answer = ChatGPTAPIExample.getAnswer(file);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder response = new StringBuilder();
        String line;
        response.append("<!DOCTYPE html>").append("\n");
        response.append("<html>").append("\n");
        response.append("<body>").append("\n");

        while ((line = reader.readLine()) !=null) {
            response.append("<p style='color:blue; font-weight:bold'>").append(line).append("</p>").append("\n");
            response.append("<p>").append(line).append("</p>").append("\n");
        }

        response.append("</html>").append("\n");
        response.append("</body>").append("\n");

        reader.close();

        return response.toString();

    }

    public static void main(String[] args) {
        String s = github("https://github.com/shiranbi07/Phoenix/blob/main/hackaton/amat/java/bug/BankAccount.java");
    }

    private static String github(String path){
        String target = "github.com";
        String replacement = "raw.githubusercontent.com";
        path = path.replace("blob/", "");
        String gitPath = path.replace(target, replacement);


        RestTemplate rest = new RestTemplate();
        String res = rest.getForObject(gitPath, String.class);
        return res;
    }
}
