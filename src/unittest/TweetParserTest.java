package unittest;

import org.junit.Test;
import parser.ScannerTweetParser;
import parser.TweetMalformedException;
import parser.TweetParser;
import tweet.Tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * TweetParserTest class -Junit tests to validate the parsing of tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class TweetParserTest {

    private static String FILE = null;

    @Test
    public void validateTweetCount()
    {
        FILE = UnitTestConstants.CURRENT_DIR + UnitTestConstants.UNITTEST_PATH + UnitTestConstants.TWEETS_PARSER_INPUT_FILE;
        TweetParser parser = new ScannerTweetParser(FILE);
        ArrayList<Tweet> tweets = TweetParserTest.getTweets(parser);
        //verifying tweetCount
        assertTrue(tweets.size() == 2);
        Tweet tweet1 = tweets.get(0);
        Tweet tweet2 = tweets.get(1);
        long seconds = (tweet2.getTimeStamp() - tweet1.getTimeStamp())/1000;
        //verifying timeStamps from tweets
        assertTrue(seconds == 5);
        //verifying hashtags from tweets
        String[] expectedHashTags1 = new String[]{"Spark", "Apache"};
        assertTrue(validateHashTags(expectedHashTags1, tweet1));
        String[] expectedHashTags2 = new String[]{"Apache", "Hadoop", "Storm"};
        assertTrue(validateHashTags(expectedHashTags2, tweet2));
    }

    private static boolean validateHashTags(String[] expectedHashTags, Tweet tweet){
        List<String> actualHashTags = tweet.getHashTags();
        String[] actualHashTagsArr = new String[actualHashTags.size()];
        actualHashTagsArr = actualHashTags.toArray(actualHashTagsArr);
        return Arrays.equals(expectedHashTags, actualHashTagsArr);
    }

    /**
     * Get list of tweets using the TweetParser
     * @param tweetparser
     * @return list of tweets
     */
    private static  ArrayList<Tweet> getTweets(TweetParser tweetparser){
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        while (tweetparser.hasTweet()){
            Tweet tweet = null;
            try {
                tweet = tweetparser.getTweet();
            } catch (TweetMalformedException e) {
                e.printStackTrace();
            }
            //System.out.println(tweet);
            tweets.add(tweet);
        }
        return tweets;
    }

}
