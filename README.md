# HackerNews

HackerNews is a simple barebone app which serves as a starting point for implementing as well as understanding 3 layer software arcitecture, working with recyclerview, basic unit testing in android.

## Getting Started

Just fork the project and open it in Android Studio to get up and running. 

See : How to import an existing project from github into Android Studio.

```
https://stackoverflow.com/questions/25348339/how-to-import-an-existing-project-from-github-into-android-studio
```

### Prerequisites

* Android Studio updated to the latest version (3.1.1 as of doing the project).
* Some basic theroritical understanding of 3 layer software architcure (MVC, MVP, MVVM).
* Understanding of RecyclerView and implementation of the same.
* Understanding of Unit Testing.

### Project Structure

As per 3 layer architecture, project is broken down into 3 main packages:

## data

Contains all classes related to the data aspect of the project.

```
FakeNews.java - class containing fake data for unit testing and dummy deployment.
News.java - selfexplanatory.
NewsSourceInterface - interface to communicate with the contoller/presenter.
OnDownloadComplete - callback for when download is completed.
```

## logic

Contains a class to control data and the view packages

```
Contoller - class that contolls the data and the view.
```

## view

Contains activities related to UI aspects.

```
NewsActivity - main activity of the app.
DetailActivity - detailed view of the info.
ViewInterface - interface to communicate with the contoller/presenter.  
```


## Running the tests

2 unit tests implemented to test :

### onGetNewsDataSuccess()

```
RecyclerView getting populated.
```

### onNewsItemClicked()

```
Links open up in WebView when clicked on the desired Headine. 
```

## Built With

* [Mockito](http://site.mockito.org/) - The unit testing framework used.
* [News Icon](https://materialdesignicons.com/) - News icon used.
* [NewsAPI](https://newsapi.org/) - API used to fetch Hacker News headlines.
