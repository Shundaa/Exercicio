package com.wipro.exercicio;


import akka.japi.pf.FI;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        InputStream input = FI.UnitApply3.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop=new Properties();
        prop.load(input);
        int limit = Integer.parseInt(prop.getProperty("limit"));


        JsonArray project = new JsonArray();
        GitHub_Search gitSearch =  new GitHub_Search();
        Twitter_Search twitterSearch = new Twitter_Search();
        JsonArray result = gitSearch.search("quantum*computer");
        for(int i=0;i<limit;i++){
            project.add(result.getAsJsonArray().get(i).getAsJsonObject().get("name").toString());
            project.add(twitterSearch.tweet(result.getAsJsonArray().get(i).getAsJsonObject().get("name").toString()));
        }
        System.out.println(project.toString());

    }

}
