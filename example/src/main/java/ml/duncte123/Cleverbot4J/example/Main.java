package ml.duncte123.Cleverbot4J.example;

import ml.duncte123.CleverBot4J.*;

public class Main {

    public static void main(String[] args) {
        //Load the api
        CleverbotAPI api = new CleverbotBuilder()
                .setApiUser("CLEVERBOT_USER_KEY")
                .setApiKey("CLEVERBOT_API_KEY")
                .build();

        //some question
        String question = "Just a small town girl";
        //Ask the question
        String answer = api.askQuestion(question);
        System.out.println("User: " + question);
        //Show the answer
        System.out.println(api.getNickname() + ": " + answer);
    }

}
