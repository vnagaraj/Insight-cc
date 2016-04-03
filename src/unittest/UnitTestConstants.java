package unittest;

/**
 * UnitTestConstants - comprising of constants to be used for the unit test classes
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class UnitTestConstants {
    //providing only package level visibility
    static final String CURRENT_DIR = System.getProperty("user.dir");
    static final String UNITTEST_PATH = "/src/unittest/files/";
    static final String TWEETS_PARSER_INPUT_FILE  = "tweets.txt";
    static final String EXECUTOR_INPUT_FILE = "tweets_input.txt";
    //deleted on cleanup, used in WriterTest and ExecutorTest
    static final String OUTPUT_FILE = "tweets_output.txt";
    //used in ValidateTimeStampTweetTest
    static final String TIME_STAMP_1 = "Thu Mar 24 17:51:10 +0000 2016";
    static final String TIME_STAMP_2 = "Thu Mar 24 17:51:15 +0000 2016";
    static final String TIME_STAMP_3 = "Thu Mar 24 17:51:30 +0000 2016";
    static final String TIME_STAMP_4 = "Thu Mar 24 17:51:55 +0000 2016";
    static final String TIME_STAMP_5 = "Thu Mar 24 17:51:58 +0000 2016";
    static final String TIME_STAMP_6 = "Thu Mar 24 17:52:12 +0000 2016";
    static final String TIME_STAMP_7 = "Thu Mar 24 17:52:10 +0000 2016";
    static final String TIME_STAMP_8 = "Thu Mar 24 17:51:10 +0000 2016";
    //used in TwitterHashTagGraphTest
    static final String[] HASH_TAGS_1 = {"Spark", "Apache"};
    static final String[] HASH_TAGS_2 = {"Apache", "Hadoop", "Storm"};
    static final String[] HASH_TAGS_3 = {"Apache"};
    static final String[] HASH_TAGS_4 = {"Flink", "Spark"};
    static final String[] HASH_TAGS_5 = {"Spark" , "HBase"};
    static final String[] HASH_TAGS_6 = {"Hadoop", "Apache"};
    static final String[] HASH_TAGS_7 = {"Flink", "HBase"};
    static final String[] HASH_TAGS_8 = {"Kafka", "Apache"};
    static final String[] HASH_TAGS_9 = {"Kafka"};

}
