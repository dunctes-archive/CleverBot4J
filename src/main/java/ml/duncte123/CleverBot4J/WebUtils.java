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

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class WebUtils {

    public static final String baseUrl = "https://cleverbot.io/1.0/";

    private static OkHttpClient client = new OkHttpClient();

    /**
     * This is a util to send json data to apie
     * @param url the url to post to
     * @param data a {@link org.json.JSONObject JSONObject} with the data that you want to send
     * @return the response from the server wrapped in the okhttp {@link okhttp3.Response Response} class
     */
    public static Response postJSON(String url, JSONObject data) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("User-Agent", "")
                .build();

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
