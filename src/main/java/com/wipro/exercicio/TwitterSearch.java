package com.wipro.exercicio;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class TwitterSearch {
    //Consumer Key
    static String consumerKeyStr = "rp4ZE8vyHsFAJDaJPPsedZLaR";
    static String consumerSecretStr = "L0JHYJCtOpJQ2hhBvAHLsvLUNyjqBIlBWQpE3u4ecKDjpFmyts";

    //Access Token
    static String accessTokenStr = "176980486-iCToTP7qsxlEZU7Cn6pP2VaXvcW70vsZeNMilYOb";
    static String accessTokenSecretStr = "7PzW8w4pnYv28GVsYaK60e1numHKdOwtNJayyd70oe8nk";


    //Search method that will return all tweets related to the search keyword in a List
    public List<String> searchForWord(String word, int limit){

        //List that will be used to store the resulting tweets
        List<String> tweets = new ArrayList<String>();

        try {

            //Initialize twitter object from factory
            Twitter twitter = new TwitterFactory().getInstance();

            //Define your query object. The parameter to the Query constructor is the word to search
            Query query = new Query(word);

            //Define the resultset size. This example will return 10,000 results
            query.setCount(5);

            //Execute the search method in the twitter object. The results are contained in a QueryResult object that contains one object Status per Tweet
            QueryResult result = twitter.search(query);

            //Add resulting entries to the List that we will return
            for(Status status: result.getTweets()){
                tweets.add(status.getText());
            }


        } catch (TwitterException te) {
            //Print any error that may be associated with this code
            te.printStackTrace();
        }

        //Return List with all the tweets found as part of the search
        return tweets;
    }


    public String tweet(String name) {
        //Instantiating current class to an object
        TwitterSearch search = new TwitterSearch();

        //Preview of the found tweets to main console
        //System.out.println(search.searchForWord(args[0],Integer.parseInt(args[1])));
        return search.searchForWord(name,2).toString();
    }
}
