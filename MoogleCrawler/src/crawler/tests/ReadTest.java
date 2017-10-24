package crawler.tests;

import crawler.model.Feed;
import crawler.model.FeedMessage;
import crawler.read.RSSFeedParser;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "http://www.vogella.com/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}