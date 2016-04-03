package validation;

import tweet.Tweet;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * ValidateTimeStampTweet class -validates the given tweet to check if it falls in the designated window
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class ValidateTimeStampTweet {
    private long maxTimeStamp;
    private PriorityQueue<Tweet> minPQ ;
    private boolean checkPriorityQueue;
    private static final long WINDOW_GAP = 60000;
    private ArrayList<Tweet> removeTweets;

    /**
     * ValidateTimeStampTweet constructor - initializes the priority queue and list of tweets to be removed
     */
    public ValidateTimeStampTweet(){
        minPQ = new PriorityQueue<Tweet>();
        removeTweets = new ArrayList<Tweet>();
    }

    /**
     * Validates time stamp of tweet
     * @param tweet to validate
     * @return tweet if time stamp falls within the 60 second window
     */
    public Tweet validateTimeStamp(Tweet tweet){
        //check timestamp
        updateMaxTimestamp(tweet);
        if (!exceededWindow(tweet)){
            //check 60 second window
            checkPriorityQueue();
            return tweet;
         }
         return null;
    }

    /**
     * list of Tweets to be removed from graph,
     * list generated through invocation of validateTimeStamp
     * @return Tweet array
     */
    public Tweet[] removeTweetsFromGraph(){
        Tweet[] removeTweetsArr = new Tweet[removeTweets.size()];
        removeTweetsArr = removeTweets.toArray(removeTweetsArr);
        removeTweets = new ArrayList<Tweet>();
        return removeTweetsArr;
    }

    /**
     * Check if tweet has exceeded 60 second window
     * @param tweet instance
     * @return true if exceeded
     */
    private boolean exceededWindow(Tweet tweet){
        long milliSecondDifference = maxTimeStamp - tweet.getTimeStamp();
        if (milliSecondDifference > WINDOW_GAP){
            return true;
        }
        return false;
    }

    /**
     * Update max timestamp of all tweets seen so far
     * @param tweet instance
     */
    private void updateMaxTimestamp(Tweet tweet){
        long currentTimestamp = tweet.getTimeStamp();
        if (currentTimestamp > maxTimeStamp){
            maxTimeStamp = currentTimestamp;
            checkPriorityQueue = true;
            minPQ.add(tweet);
        }
    }

    /**
     * Check priority queue - called when max timestamp is updated
     */
    private void checkPriorityQueue(){
        if (checkPriorityQueue){
            Tweet tweet = minPQ.peek();
            while (tweet != null){
                if (this.exceededWindow(tweet)){
                    //remove tweet from heap
                    minPQ.remove(tweet);
                    removeTweets.add(tweet);
                    tweet = minPQ.peek();
                }
                else{
                    break;
                }
            }
        }
        checkPriorityQueue = false;
    }
}
