package parser;
import tweet.Tweet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * ScannerTweetParser class - scanner implementation of the TweetParser
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class ScannerTweetParser extends TweetParser {

    /**
     * Constructor for ScannerTweetParser- calls parent constructor
     *
     * @param filename path of file
     */
    public ScannerTweetParser(String filename){
        super(filename);
    }


    /**
     * line scanner to parse the contents of the line looking for specific attributes,
     * creating a Tweet instance
     * @param line of text
     * @return Tweet
     * @throws TweetMalformedException
     * @throws ParseException
     */
    protected Tweet parseLine(String line) throws TweetMalformedException, ParseException {
        //use a second Scanner to parse the content of each line looking for specific attributes
        Scanner scanner = new Scanner(line);
        if (scanner.hasNext()){
            Calendar cal = getCalendar(scanner);
            if (cal == null){
                throw new TweetMalformedException("Unable to process timestamp");
            }
            Long id = getId(scanner);
            if (id == null){
                throw new TweetMalformedException("Unable to process id");
            }
            ArrayList<String> hashtags = getHashTags(scanner);
            return new Tweet(id, cal.getTimeInMillis(), hashtags);
        }
        else {
            throw new TweetMalformedException("Unable to process Tweet");
        }
    }

    /**
     * Get calendar object associated with "created_at" from the json Tweet
     * @param scanner instance
     * @return Calendar instance
     * @throws ParseException
     */
    private Calendar getCalendar(Scanner scanner) throws ParseException{
        Calendar cal = null;
        String key = getKey(scanner);
        String value = getValue(scanner, ParserConstants.VALUE_DELIMITER);
        if (key.equals(ParserConstants.TIMESTAMP_KEY)){
            cal = TweetParser.getCalendar(value);
        }
        return cal;
    }

    /**
     * Get id attribute from the Tweet
     * @param scanner instance
     * @return long
     */
    private Long getId(Scanner scanner){
        Long id = null;
        String key = getKey(scanner);
        String value = getValue(scanner, ParserConstants.VALUE_DELIMITER);
        if (key.equals(ParserConstants.ID_KEY)){
            id = Long.parseLong(value);
        }
        return id;
    }

    /**
     * Get the list of hashTags from the Tweet
     * @param scanner instance
     * @return list of hashtags
     */
    private ArrayList<String> getHashTags(Scanner scanner){
        String key = "";
        String value = null;
        while (!key.equals(ParserConstants.ENTITIES)){
            key = getKey(scanner);
            if (key == null){
                break;
            }
            if (key.equals(ParserConstants.ENTITIES)){
                value = getValue(scanner, ParserConstants.HASHTAG_VALUE_DILIMITER);
            }
            else {
                value = getValue(scanner, ParserConstants.VALUE_DELIMITER);
            }
            if (value == null){
                break;
            }
        }
        return parseHashTagValue(value);

    }

    /**
     * Private helper to parse the string to return the hash tags list
     * @param value comprising the hashtags
     * @return list of hashtags
     */
    private ArrayList<String> parseHashTagValue(String value){
        ArrayList<String> result = new ArrayList<String>();
        int index  = value.indexOf("text");
        while (index != -1){
            int end_index = value.indexOf(",",index);
            String val = value.substring(index+"text".length()+1, end_index);
            result.add(val);
            value = value.substring(end_index+1);
            index  = value.indexOf("text");
        }

        return result;
    }

    /**
     * Get the key from the line using the scanner
     * @param scanner instance
     * @return String
     */
    private String getKey(Scanner scanner){
        scanner.useDelimiter(ParserConstants.KEY_DELIMITER);
        while (scanner.hasNext()) {
            String key = scanner.next();
            return trimKey(key);
        }
        return null;
    }

    /**
     * Get the value from the line for the corresponding key
     * @param scanner instance
     * @param delimiter for the value
     * @return
     */
    private String getValue(Scanner scanner, String delimiter){
        scanner.useDelimiter(delimiter);
        while (scanner.hasNext()) {
            String value = scanner.next();
            return trimValue(value);
        }
        return null;
    }

    /**
     * Trim key to remove unnecessary characters
     * @param key from the json
     * @return key
     */
    private String trimKey(String key){
        key = key.replaceAll("\"", "");
        key = key.replaceAll(",","");
        return key.replaceAll("\\{", "");
    }

    /**
     * Trim value to remove unnecessary characters
     * @param value from json
     * @return value
     */
    private String trimValue(String value){
        value = value.replaceAll("\"", "");
        return value.replaceFirst(":","");
    }
}
