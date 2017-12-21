package com.prakashmeghani.moviescraze.Util;

import com.prakashmeghani.moviescraze.BuildConfig;

/**
 * Created by prakash.meghani on 12/15/2017.
 */

public class Constant {
    public static final String API_KEY = BuildConfig.API_KEY;;
    public static final String QUERY_PARAM_API_KEY = "api_key";
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    public static final String URL_POPULAR = BASE_URL + "popular";
    public static final String URL_TOP_RATED = BASE_URL + "top_rated";
    public static final String URL_POSTER = "http://image.tmdb.org/t/p/w185/";

    public static final int URL_TYPE_POPULAR = 0;
    public static final int URL_TYPE_TOP_RATED = 1;

    public static final String PREF_NAME = "movies_craze";
    public static final String PREF_URL_TYPE = "url_type";

    public static final String NODE_SUCCESS = "success";
    public static final String NODE_STATUS_MESSAGE = "status_message";
    public static final String NODE_RESULTS = "results";
    public static final String NODE_TITLE = "title";
    public static final String NODE_POSTER_PATH = "poster_path";
    public static final String NODE_OVERVIEW = "overview";
    public static final String NODE_RELEASE_DATE = "release_date";
    public static final String NODE_VOTE_AVERAGE = "vote_average";

    public static final String EXTRA_MOVIE = "extra_movie";
}
