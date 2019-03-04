/*
 *    Copyright 2017 Duncan "duncte123" Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ml.duncte123.CleverBot4J;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class CleverbotAPI {

    private final String userKey;
    private final String apiKey;
    private final String nickname;

    public CleverbotAPI(String user, String api, String nick) {
        this.userKey = user;
        this.apiKey = api;
        this.nickname = nick;
    }

    /**
     * Returns the bots nickname
     *
     * @return the bots nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This sends a question to the bot
     *
     * @param question
     *         the question that you have for the bot
     *
     * @return what the bot replies to you
     */
    public String askQuestion(String question) {
        String status = "";
        try {
            final JSONObject jsonData = new JSONObject()
                    .put("user", this.userKey)
                    .put("key", this.apiKey)
                    .put("nick", this.nickname)
                    .put("text", question);

            final String response = WebUtils.postJSON(WebUtils.baseUrl + "ask", jsonData).body().string();
            final JSONObject returnJson = new JSONObject(response);

            status = returnJson.getString("status");

            return returnJson.getString("response");
        } catch (JSONException | IOException e) {
            throw new RuntimeException(status, e);
        }
    }

    /**
     * This sends a question to the bot
     *
     * @param question
     *         the question that you have for the bot
     * @param success
     *         The response of the bot
     */
    public void askQuestionAsync(String question, Consumer<String> success) {
        askQuestionAsync(question, success, null);
    }

    /**
     * This sends a question to the bot
     *
     * @param question
     *         the question that you have for the bot
     * @param success
     *         The response of the bot
     * @param failure
     *         Called when an error occurs
     */
    public void askQuestionAsync(String question, Consumer<String> success, Consumer<Throwable> failure) {
        final JSONObject jsonData = new JSONObject()
                .put("user", this.userKey)
                .put("key", this.apiKey)
                .put("nick", this.nickname)
                .put("text", question);

        Consumer<Throwable> throwableConsumer = failure;

        if (throwableConsumer == null) {
            throwableConsumer = Throwable::printStackTrace;
        }

        final Consumer<Throwable> finalErrorConsumer = throwableConsumer;

        WebUtils.postJSONAsync(
                WebUtils.baseUrl + "ask",
                jsonData,
                (response) -> {
                    final JSONObject returnJson = new JSONObject(response);

                    success.accept(returnJson.getString("response"));

                },
                (error) -> finalErrorConsumer.accept(new RuntimeException(error))
        );
    }

}
