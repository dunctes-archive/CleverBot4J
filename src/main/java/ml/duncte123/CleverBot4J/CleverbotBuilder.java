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

public class CleverbotBuilder {

    private String userKey;
    private String apiKey;
    private String nickname;

    /**
     * This sets the user token that you should have gotten from the <a href="https://cleverbot.io/">https://cleverbot.io/</a>
     * website
     *
     * @param user
     *         the user key provided by <a href="https://cleverbot.io/keys">https://cleverbot.io/keys</a>
     *
     * @return the current {@link CleverbotBuilder builder}, useful for chaining
     */
    public CleverbotBuilder setUserKey(String user) {
        this.userKey = user;

        return this;
    }

    /**
     * This sets the api token that you should have gotten from the <a href="https://cleverbot.io/">https://cleverbot.io/</a>
     * website
     *
     * @param key
     *         the api key provided by <a href="https://cleverbot.io/keys">https://cleverbot.io/keys</a>
     *
     * @return the current {@link CleverbotBuilder builder}, useful for chaining
     */
    public CleverbotBuilder setApiKey(String key) {
        this.apiKey = key;

        return this;
    }

    /**
     * This sets the user token and the api that you should have gotten from the <a
     * href="https://cleverbot.io/">https://cleverbot.io/</a> website
     *
     * @param user
     *         the user key provided by <a href="https://cleverbot.io/keys">https://cleverbot.io/keys</a>
     * @param api
     *         the api key provided by <a href="https://cleverbot.io/keys">https://cleverbot.io/keys</a>
     *
     * @return the current {@link CleverbotBuilder builder}, useful for chaining
     */
    public CleverbotBuilder setKeys(String user, String api) {
        this.userKey = user;
        this.apiKey = api;

        return this;
    }

    /**
     * This sets the nickname that the bot has, this is optional
     *
     * @param nickname
     *         the nickname that you want your bot to have
     *
     * @return the current {@link CleverbotBuilder builder}, useful for chaining
     */
    public CleverbotBuilder setNickname(String nickname) {
        this.nickname = nickname;

        return this;
    }

    /**
     * This builds the api into the {@link CleverbotAPI CleverbotAPI} for you to send your questions to
     *
     * @return the {@link CleverbotAPI CleverbotAPI} that you can use
     */
    public CleverbotAPI build() {
        try {
            final JSONObject jsonData = new JSONObject()
                    .put("user", this.userKey)
                    .put("key", this.apiKey);

            if (nickname != null) {
                jsonData.put("nick", this.nickname);
            }

            final String response = WebUtils.postJSON(WebUtils.baseUrl + "create", jsonData).body().string();
            final JSONObject returnJSON = new JSONObject(response);
            final String status = returnJSON.getString("status");

            if (!status.equals("success")) {
                throw new IOException("Cleverbot responded with unexpected status: " + status);
            }

            final String nick = returnJSON.getString("nick");

            return new CleverbotAPI(userKey, apiKey, nick);
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
