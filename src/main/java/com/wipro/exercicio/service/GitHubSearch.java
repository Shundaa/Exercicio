package com.wipro.exercicio.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

import com.google.gson.JsonArray;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GitHubSearch {
    private static Gson gson;    

    public JsonArray search(String search) throws IOException, URISyntaxException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        return searchFileByFileName(search);
    }

    private JsonArray searchFileByFileName(String search) throws ClientProtocolException, IOException {
    	
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String gitConfigPath = rootPath + "git.properties";
        Properties gitProps = new Properties();
        gitProps.load(new FileInputStream(gitConfigPath));    	
        String GITHUB_API_SEARCH_CODE_PATH = gitProps.getProperty("GITHUB_API_SEARCH_CODE_PATH");
        String GITHUB_API_BASE_URL = gitProps.getProperty("GITHUB_API_BASE_URL");
        
        String codeFileQuery = search;
        StringBuilder restUrl = new StringBuilder();
        restUrl.append(GITHUB_API_BASE_URL);
        restUrl.append(GITHUB_API_SEARCH_CODE_PATH);
        restUrl.append(codeFileQuery);
        Map<String,String> fileNameSearchResult = makeRESTCall(restUrl.toString());
        //System.out.println("Total number or results = " + fileNameSearchResult.get("total_count"));
        JsonArray result = gson.toJsonTree(fileNameSearchResult).getAsJsonObject().get("items").getAsJsonArray();
        return result;
    }

    private Map<String,String> makeRESTCall(String restUrl, String acceptHeaderValue)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);

        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }

        Content content = request.execute().returnContent();
        String jsonString = content.asString();
        Map<String,String> jsonMap = gson.fromJson(jsonString, Map.class);
        return jsonMap;
    }

    private Map<String,String> makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null);
    }

}
