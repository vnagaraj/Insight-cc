package parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tweet.Tweet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


/**
 * JsonParser class - uses (../lib/gson-2.6.2.jar)library for implementation of the TweetParser
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class JsonTweetParser extends TweetParser {

    /**
     * Constructor for JsonTweetParser- calls parent constructor
     *
     * @param filename path of file
     */
    public JsonTweetParser(String filename){
        super(filename);
    }

    /**
     * JsonParser to convert the json document to a map,
     * creating a Tweet instance
     * @param line of the text file
     * @return Tweet
     * @throws java.text.ParseException
     * @throws parser.TweetMalformedException
     */
    protected Tweet parseLine(String line) throws ParseException, TweetMalformedException {
        if (line == null || line.trim().isEmpty()){
            throw new TweetMalformedException("Tweet is empty");
        }
        JsonElement root = new JsonParser().parse(line);
        if (root == null){
            throw new TweetMalformedException("Tweet is malformed");
        }
        JsonObject jsonObject = root.getAsJsonObject();
        Calendar cal= getCalendar(jsonObject);
        if (cal == null){
            throw new TweetMalformedException("Not able to extract timestamp from tweet");
        }
        Long id = getId(jsonObject);
        if (id == null){
            throw new TweetMalformedException("Not able to extract id from tweet");
        }
        ArrayList<String> hashtags = getHashTags(jsonObject);
        return new Tweet(id, cal.getTimeInMillis(), hashtags);
    }

    /**
     * Get calendar object associated with "created_at" from the json Tweet
     *
     * @param  jsonObject instance
     * @return Calendar instance
     * @throws java.text.ParseException
     */
    private Calendar getCalendar(JsonObject jsonObject) throws ParseException{
        String value = jsonObject.get(ParserConstants.TIMESTAMP_KEY).getAsString();
        Calendar cal = TweetParser.getCalendar(value);
        return cal;
    }

    /**
     * Get id attribute from the Tweet
     *
     * @param  jsonObject instance
     * @return long
     */
    private Long getId(JsonObject jsonObject){
        String value = jsonObject.get(ParserConstants.ID_KEY).getAsString();
        return Long.parseLong(value);
    }

    /**
     * Get the list of hashTags from the Tweet
     *
     * @param  jsonObject instance
     * @return list of hashtags
     */
    private ArrayList<String> getHashTags(JsonObject jsonObject){
        ArrayList<String> result = new ArrayList<String>();
        JsonElement jsonObj = jsonObject.get(ParserConstants.ENTITIES);
        JsonArray hashtagsList = jsonObj.getAsJsonObject().get(ParserConstants.HASHTAGS).getAsJsonArray();
        Iterator itr = hashtagsList.iterator();
        while(itr.hasNext()) {
            JsonObject element = (JsonObject) itr.next();
            String hashtag =  element.get(ParserConstants.TEXT).getAsString();
            result.add(hashtag);
        }
        return result;
    }
}
