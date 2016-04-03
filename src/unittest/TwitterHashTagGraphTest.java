package unittest;

import graph.TwitterHashTagGraph;
import org.junit.Test;
import tweet.Tweet;

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
public class TwitterHashTagGraphTest {

    @Test
    public void validateTwitterHashTagGraph()
    {

        TwitterHashTagGraph graph = new TwitterHashTagGraph();
        double averageDegree = 0.00;
        try {
            //adding hastags from tweet1
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_1, graph, new Tweet[]{});
            assertTrue(averageDegree == 1.00);
            //adding hastags from tweet2
            averageDegree= computeAverageDegree(UnitTestConstants.HASH_TAGS_2, graph, new Tweet[]{});
            assertTrue(averageDegree == 2.00);
            //adding hastags from tweet3
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_3, graph, new Tweet[]{});
            assertTrue(averageDegree == 2.00);
            //adding hastags from tweet4
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_4, graph, new Tweet[]{});
            assertTrue(averageDegree == 2.00);
            //adding hastags from tweet5
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_5, graph, new Tweet[]{});
            assertTrue(averageDegree == 2.00);
            //adding hastags from tweet6 amd removing hashtags from tweet1
            Tweet tweet = new Tweet(new Random().nextInt(), new Random().nextInt(), new ArrayList( Arrays.asList(UnitTestConstants.HASH_TAGS_1)));
            Tweet[] tweets = {tweet};
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_6, graph, tweets);
            assertTrue(averageDegree == 10/(double)6);
            //adding hashtags from tweet7
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_7, graph, new Tweet[]{});
            assertTrue(averageDegree == 2.00);
            //adding hashtags from tweet8 and removing hashtags from tweet2
            tweet = new Tweet(new Random().nextInt(), new Random().nextInt(), new ArrayList( Arrays.asList(UnitTestConstants.HASH_TAGS_2)));
            tweets = new Tweet[]{tweet};
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_8, graph, tweets);
            assertTrue(averageDegree == 10/(double)6);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkOneHashTag()
    {
        TwitterHashTagGraph graph = new TwitterHashTagGraph();
        double averageDegree = 0.00;
        try {
            //adding hastags from tweet1
            averageDegree = computeAverageDegree(UnitTestConstants.HASH_TAGS_9, graph, new Tweet[]{});
            System.out.println(averageDegree);
            assertTrue(averageDegree == 0.00);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private double computeAverageDegree(String[] hashtags, TwitterHashTagGraph graph, Tweet[] removeTweets) throws ParseException{
        Tweet tweet = new Tweet(new Random().nextInt(), new Random().nextInt(), new ArrayList( Arrays.asList(hashtags)));
        graph.updateEdges(tweet, removeTweets);
        return graph.averageDegree();
    }
}
