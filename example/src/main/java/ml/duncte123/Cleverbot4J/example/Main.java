package ml.duncte123.Cleverbot4J.example;

import ml.duncte123.CleverBot4J.*;

public class Main {

    public static void main(String[] args) {
        //Load the api
        CleverbotAPI api = new CleverbotBuilder()
                .setUserKey("CLEVERBOT_USER_KEY")
                .setApiKey("CLEVERBOT_API_KEY")
                .build();

        //some question
        String question = "Just a small town girl";
        //Ask the question
        String answer = api.askQuestion(question);
        System.out.println("User: " + question);
        //Show the answer
        System.out.println(api.getNickname() + ": " + answer);

        /*
        The above program should output something like
            User: Just a small town girl
            tRIj5NEn: Livin in a lonely world!

         the api made up a name for the bot
         */
    }

}
