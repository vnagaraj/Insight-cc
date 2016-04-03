package parser;

import stream.Reader;
import tweet.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * TweetParser abstract class - mechanism for parsing tweets
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public abstract class TweetParser {
    private Reader reader;

    /**
     * TweetParser constructor
     * @param filename path of file
     */
    public TweetParser(String filename){
        reader = new Reader(filename);
    }

    /**
     * Get tweet
     *
     * @return Tweet
     * @throws TweetMalformedException
     */
    public Tweet getTweet() throws TweetMalformedException{
        String line = null;
        try {
            line = reader.readLine();
            if(line != null) {
                //assumption, each line is a string in JSON format
                return parseLine(line);
            }
        }
        catch(ParseException e){
            System.err.print("Parser error of tweet");
        }
        throw new TweetMalformedException(String.format("%s not of valid format ",(line)));
    }

    //implemented by subclass
    protected abstract Tweet parseLine(String line) throws TweetMalformedException, ParseException ;

    /**
     * Has new tweet
     *
     * @return true else false
     */
    public boolean hasTweet(){
        return reader.hasNext();
    }

    public void close(){
        reader.close();
    }

    /**
     * Static method to getCalendar
     * @param value from json
     * @return Calendar
     * @throws ParseException
     */
    public static Calendar getCalendar(String value) throws ParseException{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(ParserConstants.DATE_FORMAT, Locale.ENGLISH);
        cal.setTime(sdf.parse(value));
        return cal;
    }

}
