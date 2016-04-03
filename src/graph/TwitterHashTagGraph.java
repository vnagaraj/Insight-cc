package graph;

import tweet.Tweet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * TwitterHashTagGraph class - builds a graph comprising of hashtags from incoming tweets and computes average degree
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class TwitterHashTagGraph {
    //adj list representation of graph mapping hashTag to a linklist of edges and  and the no of connections per edge
    private HashMap<String, LinkedHashMap<String,Integer>> hashTagEdgesMap;
    //required for computing average
    private int nodeCount;
    private int edgeCount;

    /**
     * TwitterHashTagGraph constructor - initializes hashTagEdgesMap
     */
    public TwitterHashTagGraph(){
        hashTagEdgesMap = new HashMap<String, LinkedHashMap<String, Integer>>();
    }

    /**
     * Updated the edges on the graph
     * @param tweet hashtags of the incoming tweet to be added to graph
     * @param removeTweets hashtags of tweets to be removed from graph
     */
    public void updateEdges(Tweet tweet, Tweet[] removeTweets){
        List<String> hashtags = tweet.getHashTags();
        //iterate through list to create edges between the hashtags
        addEdges(hashtags);
        //iterate through tweets to remove edges for discarded tweets
        removeEdges(removeTweets);
    }

    /**
     * Computes average degree of the graph
     * @return average degree
     */
    public double averageDegree(){
        Double val = edgeCount/(double)(nodeCount);
        if (val.equals(Double.NaN)){
            return 0.00;
        }
        return val;
    }

    /**
     * Adds edges to graph from the list of hashtags
     * @param hashtags list
     */
    private void addEdges(List<String> hashtags){
        //corner case of 0 or 1 hashtags
        for (int i =0; i < hashtags.size(); i++) {
            for (int j = i + 1; j < hashtags.size(); j++) {
                String hashTag1 = hashtags.get(i);
                String hashTag2 = hashtags.get(j);
                addEdge(hashTag1, hashTag2);
                addEdge(hashTag2, hashTag1);
            }
        }
    }

    /**
     * Adds edge to the graph between nodes hashtag1 and hashtag2
     * @param hashTag1 node of graph
     * @param hashTag2 node of graph
     */
    private void addEdge(String hashTag1, String hashTag2){
        if (!hashTagEdgesMap.containsKey(hashTag1)){
            //hashtag node not present
            LinkedHashMap<String,Integer> value = new LinkedHashMap<String, Integer>();
            value.put(hashTag2,1);
            hashTagEdgesMap.put(hashTag1, value);
            nodeCount +=1;
            edgeCount +=1;
        }
        else{
            //hashtag node present
            LinkedHashMap<String, Integer> value = hashTagEdgesMap.get(hashTag1);
            //check if hashtag2 present in linkedlist
            if (!value.containsKey(hashTag2)){
                value.put(hashTag2, 1);
                edgeCount +=1;
            }
            //else add count of no of edges , useful for deletion
            else{
                int count = value.get(hashTag2);
                value.put(hashTag2, count+1);
            }
        }
    }

    /**
     * Remove edges in the graph from the array of tweets
     * @param removeTweets array of tweets
     */
    private void removeEdges(Tweet[] removeTweets){
        for (Tweet tweet: removeTweets){
            removeEdges(tweet);
        }
    }

    /**
     * Remove edges in the graph from a tweet
     * @param removeTweet tweet instance
     */
    private void removeEdges(Tweet removeTweet){
        List<String> hashtags = removeTweet.getHashTags();
        for (int i =0; i < hashtags.size(); i++) {
            for (int j = i + 1; j < hashtags.size(); j++) {
                String hashTag1 = hashtags.get(i);
                String hashTag2 = hashtags.get(j);
                removeEdge(hashTag1, hashTag2);
                removeEdge(hashTag2, hashTag1);
            }
        }
    }

    /**
     * Remove edge between nodes hashtag1 and hashtag2
     * @param hashTag1 node
     * @param hashTag2 node
     */
    private void removeEdge(String hashTag1, String hashTag2){
        if (hashTagEdgesMap.containsKey(hashTag1)){
            //hashtag node not present
            //hashtag node present
            LinkedHashMap<String, Integer> value = hashTagEdgesMap.get(hashTag1);
            //check if hashtag2 present in linkedlist
            if (value.containsKey(hashTag2)){
                int count = value.get(hashTag2);
                if (count == 1){
                    //remove edge
                    value.remove(hashTag2);
                    edgeCount -=1;
                }
                else{
                    value.put(hashTag2, count -1);
                }
                if (value.size() == 0){
                    //delete the node as node has no edges
                    hashTagEdgesMap.remove(hashTag1);
                    nodeCount -=1;
                }
            }
        }
    }

}
