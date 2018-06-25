package com.example.arjun.hackernews.data;

import com.example.arjun.hackernews.R;

import java.util.ArrayList;
import java.util.Random;

public class FakeNews implements NewsSourceInterface {

    private static final int sizeOfCollection = 12;
    private Random random;

    private final String[] dateTimes = {

            "2018-04-14T16:51:35Z",
            "2018-04-14T16:50:24Z",
            "2018-04-14T16:33:00Z",
            "2018-04-14T15:43:00Z",
            "2018-04-14T15:33:11Z",
            "2018-04-14T15:20:35Z"
    };

    private final String[] headlines = {

            "Kentucky governor says teachers' strike left children vulnerable to sexual assault",
            "War's Game Theory Is Too Complex to Predict",
            "Carjacking Suspect Arrested in Mill Park Neighborhood",
            "Who's to blame for the late Dez Bryant release?",
            "Michigan's Wagner to enter draft, hire agent",
            "China to Encourage Horse Racing, Expand Sports Lottery in Hainan"

    };

    private final String[] newsURL = {

            "https://www.cnn.com/2018/04/14/politics/kentucky-matt-bevin-teachers-strike/index.html",
            "https://www.bloomberg.com/view/articles/2018-04-14/u-s-syria-strike-strategy-is-too-complex-to-predict",
            "http://www.kgw.com/article/news/local/carjacking-suspect-arrested-in-mill-park-neighborhood/283-538192421",
            "http://profootballtalk.nbcsports.com/2018/04/14/whos-to-blame-for-the-late-dez-bryant-release/",
            "http://www.espn.com/nba/story/_/id/23169381/michigan-wolverines-junior-moe-wagner-enters-nba-draft-sign-agent",
            "https://www.bloomberg.com/news/articles/2018-04-14/china-to-encourage-horse-racing-expand-sports-lottery-in-hainan"

    };

    private final int[] drawables = {
            R.drawable.red_drawable,
            R.drawable.blue_drawable,
            R.drawable.green_drawable,
            R.drawable.yellow_drawable
    };

    public FakeNews() {
        random = new Random();
    }

    @Override
    public ArrayList<News> getNews() {

        ArrayList<News> newsData = new ArrayList<>();

        for (int i = 0; i < sizeOfCollection ; i++) {

            newsData.add(createNewsList());
        }
        return newsData;
    }

    @Override
    public News createNewsList() {

        int one = random.nextInt(4);
        int two = random.nextInt(4);
        int three = random.nextInt(4);
        int four = random.nextInt(4);

        return new News(

                dateTimes[one],
                headlines[two],
                newsURL[three],
                drawables[four]
        );
    }

    @Override
    public void deleteNewsItem(News news) {

    }

    @Override
    public void insertNewsItem(News temporaryNewsItem) {

    }
}
