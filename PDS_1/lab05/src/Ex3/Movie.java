package Ex3;

import java.util.List;

public class Movie{
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String>languages;
    private final List<String>genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    public static class MovieBuilder {
        //Required parameters
        private final String movieTitle;
        private final int movieYear;
        private final Person movieDirector;
        //Optional parameters
        private Person movieWriter;
        private String movieSeries;
        private List<Person> movieCast;
        private List<Place> movieLocations;
        private List<String> movieLanguages;
        private List<String> movieGenres;
        private boolean television;
        private boolean netflix;
        private boolean independent;

        public MovieBuilder(String movieTitle, int movieYear, Person movieDirector){
            this.movieTitle=movieTitle;
            this.movieYear=movieYear;
            this.movieDirector = movieDirector;
        }

        public MovieBuilder setMovieWriter(Person movieWriter) {
            this.movieWriter = movieWriter;
            return this;
        }

        public MovieBuilder setMovieSeries(String movieSeries) {
            this.movieSeries = movieSeries;
            return this;
        }

        public MovieBuilder setMovieCast(List<Person> movieCast) {
            this.movieCast = movieCast;
            return this;
        }

        public MovieBuilder setMovieLocations(List<Place> movieLocations) {
            this.movieLocations = movieLocations;
            return this;
        }

        public MovieBuilder setMovieLanguages(List<String> movieLanguages) {
            this.movieLanguages = movieLanguages;
            return this;
        }

        public MovieBuilder setMovieGenres(List<String> movieGenres) {
            this.movieGenres = movieGenres;
            return this;
        }

        public MovieBuilder setTelevision(boolean television) {
            this.television = television;
            return this;
        }

        public MovieBuilder setNetflix(boolean netflix) {
            this.netflix = netflix;
            return this;
        }

        public MovieBuilder setIndependent(boolean independent) {
            this.independent = independent;
            return this;
        }
        public Movie build(){
            return new Movie(this);
        }
    }
    private Movie(MovieBuilder builder){
        title=builder.movieTitle;
        year=builder.movieYear;
        director=builder.movieDirector;
        writer=builder.movieWriter;
        series=builder.movieSeries;
        cast=builder.movieCast;
        locations=builder.movieLocations;
        languages=builder.movieLanguages;
        genres=builder.movieGenres;
        isTelevision=builder.television;
        isNetflix=builder.netflix;
        isIndependent=builder.independent;
    }}