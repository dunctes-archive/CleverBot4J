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

import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CleverbotBuilder {

    private String userKey;
    private String apiKey;
    private String nickname;

    public CleverbotBuilder setApiUser(String user) {
        this.userKey = user;
        return this;
    }

    public CleverbotBuilder setApiKey(String key) {
        this.apiKey = key;
        return this;
    }

    public CleverbotBuilder setKeys(String user, String api){
        this.userKey = user;
        this.apiKey = api;
        return this;
    }

    public CleverbotBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public CleverbotAPI build() {
        try {
            JSONObject jsonData = new JSONObject()
                    .put("user", userKey)
                    .put("api", apiKey);

            if (nickname != null) {
                jsonData.put("nick", nickname);
            }

            String response = WebUtils.postJSON(WebUtils.baseUrl + "create", jsonData).body().source().readUtf8();
            JSONObject returnJSON = new JSONObject(response);
            String status = returnJSON.getString("status");

            if(!status.equals("success")){
                throw new IOException("Cleverbot responded with unexpected status: "+status);
            }
            String nick = returnJSON.getString("nick");
            return new CleverbotAPI(userKey, apiKey, nick);
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
