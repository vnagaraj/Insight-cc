package tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Immutable Tweet class - constructed from tweet json
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public final class Tweet implements Comparable<Tweet>{
    private final long id;
    private final long timestamp; //in milliseconds
    private final List<String> hashtagsList;


    /**
     * Tweet constructor
     * @param id identifier of tweet
     * @param timestamp timestamp of tweet used for validation
     * @param hashtags hashtags of tweet used for graph processing
     */
    public Tweet(long id, long timestamp, ArrayList<String> hashtags){
        this.id = id;
        this.timestamp = timestamp;
        hashtagsList = Collections.unmodifiableList(hashtags);
    }

    /**
     * Get the timestamp of twitter in milliseconds
     * @return timestamp
     */
    public long getTimeStamp(){
        return this.timestamp;
    }

    /**
     * Get the hashtags
     * @return list of hashtags
     */
    public List<String> getHashTags(){
        return hashtagsList;
    }

    /**
     * String representation of the class for debugging
     * @return String
     */
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer = hashtags(stringBuffer, hashtagsList);
        return String.format("id:%d ,timestamp:%d, hashtags:%s", id, timestamp, stringBuffer);
    }

    /**
     * Overriding equals to make sure tweets with same timestamp are treated differently when using
     * Java collections
     * @param o other instance
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Tweet or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Tweet)) {
            return false;
        }

        // typecast o to Tweet so that we can compare data members
        Tweet c = (Tweet) o;
        //compare indiv members
        if (this.id != c.id){
            return false;
        }
        if (this.timestamp != c.timestamp){
            return false;
        }
        String[] thisHashTags = Tweet.convertListToStringArray(this.hashtagsList);
        String[] thatHashTags = Tweet.convertListToStringArray(c.getHashTags());
        if (!Arrays.equals(thisHashTags, thatHashTags)){
            return false;
        }
        return true;
    }

    /**
     *
     * @return hashCode value of Tweet
     */
    @Override
    public int hashCode(){
        int result = 1;
        for (String hashtag: hashtagsList){
            result *= hashtag.hashCode();
        }
        return Long.hashCode(this.timestamp) * Long.hashCode(this.id) * result;
    }

    /**
     * Compare tweets based on timestamp when used in priority queue
     * @param that other tweet
     * @return int based on comparison
     */
    @Override
    public int compareTo(Tweet that) {
        if (this.timestamp< that.timestamp){
            return -1;
        }

        if (this.timestamp > that.timestamp){
            return 1;
        }
        return 0;
    }

    /**
     * Return a string buffer from the list of Strings
     * @param sb string Buffer
     * @param hashtags list of hashtags
     * @return
     */
    private StringBuffer hashtags(StringBuffer sb, List<String> hashtags){
        for (String tag : hashtags){
            sb.append(tag+",");
        }
        return sb;
    }

    /**
     * Utility to convert List to Array
     * @param list of String
     * @return array of String
     */
    private static String[] convertListToStringArray(List<String> list){
        String[] stringArr = new String[list.size()];
        return list.toArray(stringArr);
    }
}
