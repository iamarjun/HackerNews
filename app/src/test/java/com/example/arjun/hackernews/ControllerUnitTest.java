package com.example.arjun.hackernews;

import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.NewsSourceInterface;
import com.example.arjun.hackernews.logic.Controller;
import com.example.arjun.hackernews.view.ViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ControllerUnitTest {

    @Mock
    ViewInterface viewInterface;

    @Mock
    NewsSourceInterface newsSourceInterface;

    Controller controller;

    private static final News news = new News(

            "2018-04-14T16:51:35Z",
            "Kentucky governor says teachers' strike left children vulnerable to sexual assault",
            "https://www.cnn.com/2018/04/14/politics/kentucky-matt-bevin-teachers-strike/index.html"
    );

    @Before
    public void setUpTest() {

        MockitoAnnotations.initMocks(this);

        controller = new Controller(viewInterface, newsSourceInterface);


    }


    @Test
    public void onGetNewsDataSuccess() {

        //set up data need for the test
        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(news);

        //set up mocks to return data we want
        Mockito.when(newsSourceInterface.getNews())
                .thenReturn(newsList);


        //call the method we are testing
        controller.getListFromNewsSource();

        //check how the tested class response it receives or test its behaviour
        Mockito.verify(viewInterface).setAdapterAndView(newsList);
    }



    @Test
    public void onNewsItemClicked() {

        controller.onNewsItemClicked(news);

        Mockito.verify(viewInterface).startNewsWebViewActivity(
                news.getNewsURL()
        );
    }

}