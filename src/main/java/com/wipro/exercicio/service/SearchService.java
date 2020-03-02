package com.wipro.exercicio.service;


import com.google.gson.JsonArray;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	private GitHubSearch gitSearch;
	@Autowired
	private TwitterSearch twitterSearch;

	public StringBuilder getSearch() {
		return search;
	}

	public void setSearch(StringBuilder search) {
		this.search = search;
	}

	private StringBuilder search;
	
     public JsonArray obterSearch() throws  URISyntaxException{
    	 
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String configPath = rootPath + "config.properties";
        Properties configProps = new Properties();
        
        JsonArray project = new JsonArray(); 
        if(search==null) {
         	search=new StringBuilder();
        	search.append("quantum*computer");
        }
        try {
            JsonArray result = gitSearch.search(search.toString());        	
			configProps.load(new FileInputStream(configPath));
	        int limit = Integer.parseInt(configProps.getProperty("limit"));
	        for(int i=0;i<limit;i++){
	            project.add("Projeto: "+result.getAsJsonArray().get(i).getAsJsonObject().get("name").toString());
	            project.add("Twitters: "+twitterSearch.searchForWord(result.getAsJsonArray().get(i).getAsJsonObject().get("name").toString()).toString());
	        }
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
        return project;
    }

}
