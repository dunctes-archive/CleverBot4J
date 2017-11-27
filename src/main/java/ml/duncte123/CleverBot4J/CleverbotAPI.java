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
     * @return the bots nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This sends a question to the bot
     * @param question the question that you have for the bot
     * @return what the bot replies to you
     */
    public String askQuestion(String question) {
        String status = "";
        try {
            JSONObject jsonData = new JSONObject()
                    .put("user", userKey)
                    .put("api", apiKey)
                    .put("nick", nickname)
                    .put("text", question);

            String response = WebUtils.postJSON(WebUtils.baseUrl + "ask", jsonData).body().source().readUtf8();
            JSONObject returnJson = new JSONObject(response);
            status = returnJson.getString("status");
            return returnJson.getString("response");
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException(status, e);
        }
    }

}
