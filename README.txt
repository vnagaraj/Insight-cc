Insight Data Engineering Code Summary:

List of dependencies(located in lib):
1. hamcrest-core-1.3.jar - required for running JUNiT unit tests
    (http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar)
2. junit-4.12.jar - required for running JUNIT unit tests
    (http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar)
3. gson-2.6.2.jar - (optional) - JsonTweetParser class uses this library for parsing the tweets.txt but
   I have provided a alternate working solution( ScannerTweetParser) class
   (http://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/gson-2.6.2-javadoc.jar)


Summary of Packages added:
1. lib - comprises the jars, required for unit test,and another optional jar.
2. scr/launch - comprises the Executor class for launching the program.
3. src/parser - comprises of classes which implement the parser functionality for parsing the text into Tweet instances.
4. src/tweet - comprises of Tweet class to represent the json Tweet text.
5. src/validation - comprises of ValidationTimeStampTweet to validate tweet falls with the designated 60 second window.
6. src/graph - comprises of TwitterHashTagGraph which represents the graph built from hashtags of the tweet.
7. src/stream - comprises of Reader/Writer class to perform operations of reading from file/writing to file.
8. src/unittest - comprises of JUNIT unittests for each of the classes added.
9. doc - javaDoc for all the classes