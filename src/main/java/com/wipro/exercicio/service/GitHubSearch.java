package com.wipro.exercicio.service;

import java.util.Map;
import com.google.gson.JsonArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GitHubSearch {
    private Gson gson;    
    private JsonArray result;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${github.api.base.url}")
    private String gitHubApiBaseUrl;
    
    @Value("${github.api.search.code.path}")
    private String gitHubApiSearchCodePath;
	
    public GitHubSearch() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    	
    @SuppressWarnings("unchecked")
	public JsonArray search(String search){
        Map<String,String> fileNameSearchResult = restTemplate.exchange(buildUrl(search),HttpMethod.GET,HttpEntity.EMPTY,Map.class).getBody();
        result = gson.toJsonTree(fileNameSearchResult).getAsJsonObject().get("items").getAsJsonArray();
        return result;
    }

	private String buildUrl(String search) {
		StringBuilder restUrl = new StringBuilder();
        restUrl.append(gitHubApiBaseUrl);
        restUrl.append(gitHubApiSearchCodePath);
        restUrl.append(search);
		return restUrl.toString();
	}
    
}
