package com.wipro.exercicio.service;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TwitterSearch {
    public List<String> searchForWord(String word, int limit){
        List<String> tweets = new ArrayList<>();
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(word);
            query.setCount(5);
            QueryResult result = twitter.search(query);
            for(Status status: result.getTweets()){
                tweets.add(status.getText());
            }

        } catch (TwitterException te) {
        	te.printStackTrace();
        }
        return tweets;
    }
    public String tweet(String name) {
        TwitterSearch search = new TwitterSearch();
        return search.searchForWord(name,2).toString();
    }
}
