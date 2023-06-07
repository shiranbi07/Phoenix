package com.example.chatgpt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import com.example.chatgpt.FileHelper;
import org.springframework.web.client.RestTemplate;


public class ChatGPTAPIExample {

    static  String key = "Bearer " ;
    static  String orgKey ="" ;


    public static String getAnswer(String file) {
        return "s";
    }
    public static void listTokens() {
        try {
            String key1 = key + System.getenv("OPENAI_KEY");
            String orgKey1 = orgKey + System.getenv("ORG_KEY");
//This API will fetch the models available.
            URL url = new URL("https://api.openai.com/v1/models");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
//Make sure you put the right Organization key saved earlier.
            con.setRequestProperty("OpenAI-Organization", orgKey1);
            con.setDoOutput(true);
//Make sure you put the right API Key saved earlier.
            con.setRequestProperty("Authorization", key1);
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static HttpURLConnection initializeConnection() throws IOException {
        String key1 = key + System.getenv("OPENAI_KEY");
        String orgKey1 = orgKey + System.getenv("ORG_KEY");
        URL url = new URL("https://api.openai.com/v1/completions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
//Make sure you put the right Organization key saved earlier.
        con.setRequestProperty("OpenAI-Organization", orgKey1);
        con.setDoOutput(true);
//Make sure you put the right API Key saved earlier.
        con.setRequestProperty("Authorization", key1);
        return con;
    }
    public static String prompts(String fileStr) {
        try {
            HttpURLConnection con = initializeConnection();
//Make sure to relace the path of the json file created earlier.
            String jsonInputString = FileHelper.readLinesAsString(new File("C:\\Users\\e177118\\IdeaProjects\\BugFinder\\src\\concurrency.py"));
            String text2 = jsonInputString.replace("\r\n", "\\\\n");//jsonInputString.replace("\r\n", "\\n").replace("\r", "\\n"); //jsonInputString.replace("\r\n","\\\\n");

            //   String newStringWithLines = jsonInputString.replace("\n", "\\n");
            String code = "import threading\\\\n\\\\nclass BankAccount :\\\\n    def __init__(self, balance)\\\\n        self.balance = balance\\\\n        \\\\n    def withdraw(self, amount)\\\\n        if self.balance >= amount :\\\\n            print(f\\\"Wihdrawing ${amount}...\\\")\\\\n            self.balance -= amount\\\\n            print(f\\\"Withdrawl succesful. Current balance: {self.balance}\\\")\\\\n        else :\\\\n            print(\\\"Withdrawl funds!\\\")\\\\n\\\\ndef withdraw_amount(account, amount)  :\\\\n    account.withdraw(amount)\\\\n\\\\ndef concurrent() :\\\\n    account = BankAccount(500)\\\\n    thread1 = threading.Thread(target=withdraw_amount, args=(account,200)\\\\n    thread2 = threading.Thread(target=withdraw_amount, args=(account,400)\\\\n\\\\n     thread1.start()\\\\n     thread2.start()\\\\n\\\\n     thread1.join()\\\\n     thread2.join()";

            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://raw.githubusercontent.com/dapr/dapr/master/utils/host.go"))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Accepts", "application/json")
                    .build();

           // HttpClient httpClient= HttpClient.newHttpClient();
           // httpClient.send(request, )

            String generateDataForEngine = generateDataForEngine2(code, "Python");
            String newStringWithLines = generateDataForEngine; //generateDataForEngine.replace("\r\n", "\\n");
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = newStringWithLines.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
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
    public static void main(String[] args) {
//
        String path = "https://github.com/shiranbi07/Phoenix/blob/main/python_examples/deadlock.py";
        String file = github(path);
        String response = prompts(file);
        System.out.println(response);
    }

    private static String generateDataForEngine(String code, String language) {
            String template = "{\n" +
                    "  \"model\": \"text-davinci-003\",\n" +
                    "  \"prompt\": \"##### Fix bugs in the below function\\n \\n### Buggy LANGUAGE\\r\\nCODE_TOKEN\\n### Fixed LANGUAGE\",\n" +
                    "  \"max_tokens\": 500,\n" +
                    "  \"temperature\": 0,\n" +
                    "  \"top_p\": 1.0,\n" +
                    "  \"frequency_penalty\": 0.0,\n" +
                    "  \"presence_penalty\" :0.0,\n" +
                    "  \"stop\": [\"###\"]\n" +
                    "}\n";
            return template.replace("CODE_TOKEN", code).replace("LANGUAGE", language);
    }

    private static String generateDataForEngine2Work(String code, String language) {
        return "{\n" +
                "  \"model\": \"text-davinci-003\",\n" +
                "  \"prompt\": \"##### Fix bugs in the below function\\n \\n### Buggy Python\\nimport threading\\\\n\\\\nclass BankAccount :\\\\n    def __init__(self, balance)\\\\n        self.balance = balance\\\\n        \\\\n    def withdraw(self, amount)\\\\n        if self.balance >= amount :\\\\n            print(f\\\"Wihdrawing ${amount}...\\\")\\\\n            self.balance -= amount\\\\n            print(f\\\"Withdrawl succesful. Current balance: {self.balance}\\\")\\\\n        else :\\\\n            print(\\\"Withdrawl funds!\\\")\\\\n\\\\ndef withdraw_amount(account, amount)  :\\\\n    account.withdraw(amount)\\\\n\\\\ndef concurrent() :\\\\n    account = BankAccount(500)\\\\n    thread1 = threading.Thread(target=withdraw_amount, args=(account,200)\\\\n    thread2 = threading.Thread(target=withdraw_amount, args=(account,400)\\\\n\\\\n     thread1.start()\\\\n     thread2.start()\\\\n\\\\n     thread1.join()\\\\n     thread2.join()\\n### Fixed Python\",\n" +
                "  \"max_tokens\": 500,\n" +
                "  \"temperature\": 0,\n" +
                "  \"top_p\": 1.0,\n" +
                "  \"frequency_penalty\": 0.0,\n" +
                "  \"presence_penalty\" :0.0,\n" +
                "  \"stop\": [\"###\"]\n" +
                "}\n";
    }

    private static String generateDataForEngine2(String code, String language) {
        return (("{\n" +
                "  \"model\": \"text-davinci-003\",\n" +
                "  \"prompt\": \"##### Fix bugs in the below function\\n \\n### Buggy Python\\nCODE_TOKEN\\n### Fixed Python\",\n" +
                "  \"max_tokens\": 500,\n" +
                "  \"temperature\": 0,\n" +
                "  \"top_p\": 1.0,\n" +
                "  \"frequency_penalty\": 0.0,\n" +
                "  \"presence_penalty\" :0.0,\n" +
                "  \"stop\": [\"###\"]\n" +
                "}\n").replace("CODE_TOKEN", code));
    }

}
