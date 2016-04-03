package launch;

import graph.TwitterHashTagGraph;
import parser.ScannerTweetParser;
import parser.TweetMalformedException;
import parser.TweetParser;
import stream.Writer;
import tweet.Tweet;
import validation.ValidateTimeStampTweet;

/**
 * Executor class - class which invokes others classes to execute the program
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class Executor {

    private static Executor instance = null;

    /**
     * Get instance of Executor
     * Uses singleton design pattern (only 1 instance of this class is required)
     * @param inputFilePath path of file comprising of json tweets
     * @param outputFilePath path to outfile for computing rolling average
     * @return instance of the Executor
     */
    public static Executor getInstance(String inputFilePath, String outputFilePath) {
        if(instance == null) {
            instance = new Executor(inputFilePath, outputFilePath);
        }
        return instance;
    }

    /**
     * Private constructor for Executor
     * @param inputFilePath path of file comprising of json tweets
     * @param outputFilePath path to outfile for computing rolling average
     */
    private Executor(String inputFilePath, String outputFilePath){
        execute(inputFilePath, outputFilePath);
    }

    /**
     * Method which computes the rolling average and writes to file path
     * specified in outPutFilePath
     *
     * @param inputFilePath path of file comprising of json tweets
     * @param outPutFilePath outputFilePath path to outfile for computing rolling average
     */
    private void execute(String inputFilePath, String outPutFilePath){
        TweetParser parser = new ScannerTweetParser(inputFilePath);
        TwitterHashTagGraph graph = new TwitterHashTagGraph();
        ValidateTimeStampTweet valTweet = new ValidateTimeStampTweet();
        double averageDegree = 0;
        while (parser.hasTweet()){
            try {
                Tweet tweet = parser.getTweet();
                //System.out.println(tweet);
                tweet = valTweet.validateTimeStamp(tweet);
                if (tweet != null){
                    //tweet within 60 sec time gap, send it to graph for processing
                    Tweet[] removeTweets = valTweet.removeTweetsFromGraph();
                    graph.updateEdges(tweet, removeTweets);
                    averageDegree = graph.averageDegree();
                    //System.out.println(outPutFilePath);
                    new Writer(outPutFilePath).write(averageDegree);
                }
            }
            catch(TweetMalformedException e){
                //ignoring tweet
                //System.err.print("Malformed tweet");
            }
        }
        parser.close();
    }

    /**
     * Method which launches the Java application
     * Requires 2 parameters at command line
     *
     * @param args inputFilePath, outPutFilePath
     */
    public static void main(String[] args){
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        Executor.getInstance(inputFilePath, outputFilePath);

    }
}
