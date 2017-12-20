package com.prakashmeghani.moviescraze.Util;

import com.prakashmeghani.moviescraze.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class OpenMovieJsonUtils {

    public static ArrayList<Movie> getMovieFromJson(String movieJsonStr)
            throws JSONException {

        ArrayList<Movie> parsedMovieData = new ArrayList<Movie>();

        JSONObject movieJson = new JSONObject(movieJsonStr);

        if (movieJson.has(Constant.NODE_SUCCESS)) {
            Movie movie = new Movie();
            String statusMessage = movieJson.getString(Constant.NODE_STATUS_MESSAGE);
            movie.setSuccess(movieJson.getBoolean(Constant.NODE_SUCCESS));
            movie.setStatusMessage(statusMessage);
            parsedMovieData.clear();
            parsedMovieData.add(movie);
            return parsedMovieData;
        }

        JSONArray resultsArray = movieJson.getJSONArray(Constant.NODE_RESULTS);

        parsedMovieData.clear();

        for (int i = 0; i < resultsArray.length(); i++) {
            String title;
            String posterPath;
            String releaseDate;
            String overview;
            String voteAverage;

            Movie movie = new Movie();
            JSONObject movieObject =
                    resultsArray.getJSONObject(i);
            title = movieObject.getString(Constant.NODE_TITLE);
            posterPath = movieObject.getString(Constant.NODE_POSTER_PATH);
            releaseDate = movieObject.getString(Constant.NODE_RELEASE_DATE);
            overview = movieObject.getString(Constant.NODE_OVERVIEW);
            voteAverage = movieObject.getString(Constant.NODE_VOTE_AVERAGE);

            movie.setTitle(title);
            movie.setPosterPath(posterPath);
            movie.setReleaseDate(releaseDate);
            movie.setOverview(overview);
            movie.setVoteAverage(voteAverage);

            parsedMovieData.add(movie);
        }

        return parsedMovieData;
    }
}