package parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ParserConstants class - constants to be used for the TweetParser
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class ParserConstants {
    public static final Charset ENCODING = StandardCharsets.UTF_8;
    //only package level visibility
    static final String KEY_DELIMITER = ":";
    static final String VALUE_DELIMITER = ",";
    static final String HASHTAG_VALUE_DILIMITER = "]}]";
    static final String TIMESTAMP_KEY = "created_at";
    static final String ID_KEY = "id";
    static final String ENTITIES = "entities";
    static final String HASHTAGS = "hashtags";
    static final String TEXT = "text";
    static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
}
