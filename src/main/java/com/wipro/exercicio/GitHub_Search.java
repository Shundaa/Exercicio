package com.wipro.exercicio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import com.google.gson.JsonArray;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GitHub_Search {
    private static Gson gson;

    private static String GITHUB_API_BASE_URL = "https://api.github.com/";

    private static String GITHUB_API_SEARCH_CODE_PATH = "search/repositories?q=";

    public JsonArray search(String search) throws IOException, URISyntaxException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        return searchFileByFileName(search);
    }

    private static JsonArray searchFileByFileName(String search) throws ClientProtocolException, IOException {
        String codeFileQuery = search;

        Map fileNameSearchResult = makeRESTCall(GITHUB_API_BASE_URL + GITHUB_API_SEARCH_CODE_PATH + codeFileQuery);

        System.out.println("Total number or results = " + fileNameSearchResult.get("total_count"));
        JsonArray result = gson.toJsonTree(fileNameSearchResult).getAsJsonObject().get("items").getAsJsonArray();
        return result;
    }

    private static Map makeRESTCall(String restUrl, String acceptHeaderValue)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);

        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }

        Content content = request.execute().returnContent();
        String jsonString = content.asString();
        // System.out.println("content = " + jsonString);

        // To print response JSON, using GSON. Any other JSON parser can be used here.
        Map jsonMap = gson.fromJson(jsonString, Map.class);
        return jsonMap;
    }

    private static Map makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null);
    }

}
