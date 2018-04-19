package com.example.arjun.hackernews.data;

import java.util.ArrayList;

public class FakeNews implements NewsSourceInterface {

    private static final int sizeOfCollection = 6;

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

    @Override
    public ArrayList<News> getNews() {

        ArrayList<News> newsData = new ArrayList<>();

        for (int i = 0; i < sizeOfCollection ; i++) {

            News news = new News(

                    dateTimes[i],
                    headlines[i],
                    newsURL[i]
            );

            newsData.add(news);

        }

        return newsData;
    }


}
