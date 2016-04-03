package unittest;

import org.junit.Test;
import tweet.Tweet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * TweetTest - Junit tests to validate the tweet
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class TweetTest {

    @Test
    public void validateTweet()
    {
        Tweet tweet1 = new Tweet(new Random().nextInt(), Calendar.getInstance().getTimeInMillis(), new ArrayList<String>());
        //System.out.println(tweet1);
        Tweet tweet2 = new Tweet(new Random().nextInt(), Calendar.getInstance().getTimeInMillis(), new ArrayList<String>());
        //System.out.println(tweet2);
        //verifying tweetCount
        assertTrue(tweet1.compareTo(tweet2) == -1);
    }

}
