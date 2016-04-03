package unittest;

import org.junit.Test;
import parser.TweetParser;
import tweet.Tweet;
import validation.ValidateTimeStampTweet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * ValidateTweetTest class -Junit tests to validate the timestamp of tweets to be sent to graph for processing
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class ValidateTimeStampTweetTest {

    @Test
    public void validateTimeStampTweetTest()
    {

        ValidateTimeStampTweet validateTweet = new ValidateTimeStampTweet();;
        Tweet tweet = null;
        try {
            //timestamp1
            int id = new Random().nextInt();
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_1, validateTweet, id);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp2
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_2, validateTweet, null);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp3
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_3, validateTweet, null);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp4
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_4, validateTweet, null);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp5
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_5, validateTweet, null);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp6 causing timestamp1 to be evicted since it falls outside 60 sec window
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_6, validateTweet, null);
            assertTrue(tweet != null);
            Tweet[] removalTweetList = validateTweet.removeTweetsFromGraph();
            assertTrue(removalTweetList.length == 1);
            tweet = new Tweet(id, TweetParser.getCalendar(UnitTestConstants.TIME_STAMP_1).getTimeInMillis(), new ArrayList());
            assertTrue(removalTweetList[0].equals(tweet));
            //timestamp7 out of order but within 60 second window
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_7, validateTweet, null);
            assertTrue(tweet != null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));
            //timestamp8 out of order but outside 60 second window
            tweet = validateTweetData(UnitTestConstants.TIME_STAMP_8, validateTweet, null);
            assertTrue(tweet == null);
            assertTrue(Arrays.equals(validateTweet.removeTweetsFromGraph(),new Tweet[0]));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Tweet validateTweetData(String timeStamp, ValidateTimeStampTweet validateTweet, Integer id) throws ParseException{
        if (id == null){
            id = new Random().nextInt();
        }
        Tweet tweet = new Tweet(id, TweetParser.getCalendar(timeStamp).getTimeInMillis(),new ArrayList<String>());
        return validateTweet.validateTimeStamp(tweet);
    }
}
