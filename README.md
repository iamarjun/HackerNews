# HackerNews

HackerNews is a simple barebone app which serves as a starting point for implementing as well as understanding software arcitecture (MVP in this case), working with recyclerview, basic unit testing in android.

## Getting Started

Just fork the project and open it in Android Studio to get up and running. 

See : How to import an existing project from github into Android Studio.

```
https://stackoverflow.com/questions/25348339/how-to-import-an-existing-project-from-github-into-android-studio
```

### Prerequisites

* Android Studio updated to the latest version (3.1.1 as of doing the project).
* Some basic theroritical understanding of software architcure (MVC, MVP, MVVM).
* Understanding of RecyclerView and implementation of the same.
* Understanding of Unit Testing.

### Project Structure

As per MVP architecture project is broken down into 3 main packages:

## data

Contains all classes related to the data aspect of the project.

```
FakeNews.java - class containing fake data for unit testing and dummy deployment.
MySingleton.java - class containing singleton design pattern for Volley.
News.java - selfexplanatory.
NewsDataSource - class to fetch data from the API using Volley.
NewsDownloadTask - class class to fetch data from the API using AsyncTask.
NewsListerner - interface to get data from callback methods (Volley's onResponse method).
NewsSharedPref - class to store data using SharedPreferences.
NewsSourceInterface - interface to communicate with the contoller/presenter.
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
NewsWebView - webview activity to open the links of the respective headlines.
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

* [Volley](https://developer.android.com/training/volley/index.html) - An HTTP library designed and developed by Google that makes networking for Android apps easier and most importantly, faster. 
* [Mockito](http://site.mockito.org/) - The unit testing framework used.
* [News Icon](https://materialdesignicons.com/) - News icon used.
* [NewsAPI](https://newsapi.org/) - API used to fetch Hacker News headlines.


## Authors

* **Arjun Manoj** - *Application* - [Arjun Manoj](https://github.com/iamarjun)
* **Ryan** - *Initial work* - [BracketCove](https://github.com/BracketCove)

See also the list of [contributors](https://github.com/iamarjun/HackerNews/graphs/contributors) who participated in this project.


## Acknowledgments

* Ryan
* Stackoverflow
* Google
* Me

