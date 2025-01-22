# IMDBMovieSearch

This application uses the IMDB Non-comercial datasets, titles.basic.tsv.gz and titles.ratings.tsv.gz from https://developer.imdb.com/non-commercial-datasets/ to load in all IMDB titles that have a rating, then by navigating to https:/localhost:8080 you can search for a movie by primary title and it will return all of the movies that contain your query in order of number of votes. These datasets are required to be downloaded and placed inside of the app folder in order for the application to work. 

It also uses an API from themoviedb to fetch the poster of the movies and display it in the search result. This requires access to an API key that can be obtained by going to https://developer.themoviedb.org/, creating an account and then filling out a form. This API key is then input inside the API_KEY variable under the TMDBApiService class. Without this API KEY the images will just be replaced with a template "No image" image.

Examples:

[Homepage](https://github.com/tudormunteanu3/IMDBMovieSearch/blob/main/IMDB%20Website%20Index%20page.png)

[Search results when searching "Avengers"](https://github.com/tudormunteanu3/IMDBMovieSearch/blob/main/Search%20results%20example.png)

[Search page pagination example](https://github.com/tudormunteanu3/IMDBMovieSearch/blob/main/Pagination%20example.png)
