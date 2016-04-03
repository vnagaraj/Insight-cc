package parser;

/**
 * TweetMalformedException  class- for malformed tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class TweetMalformedException extends Exception
{
    public TweetMalformedException(String s)
    {
        super(String.format("Tweet not of valid format"));
    }
}
