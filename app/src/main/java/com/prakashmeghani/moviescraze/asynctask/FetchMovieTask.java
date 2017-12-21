package com.prakashmeghani.moviescraze.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.prakashmeghani.moviescraze.Util.NetworkUtils;
import com.prakashmeghani.moviescraze.Util.OpenMovieJsonUtils;
import com.prakashmeghani.moviescraze.model.AsyncTaskCompleteListener;
import com.prakashmeghani.moviescraze.model.Movie;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by prakash.meghani on 12/21/2017.
 */

public class FetchMovieTask extends AsyncTask<Integer, Void, ArrayList<Movie>> {

    private Context context;
    private AsyncTaskCompleteListener<ArrayList<Movie>> listener;

    public FetchMovieTask(Context _context, AsyncTaskCompleteListener<ArrayList<Movie>> _listener){
        this.context = _context;
        this.listener = _listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Integer... params) {

        if (params.length == 0) {
            return null;
        }

        int urlType = params[0];
        URL movieRequestUrl = NetworkUtils.buildUrl(urlType);

        try {
            String jsonMovieResponse = NetworkUtils
                    .getResponseFromHttpUrl(movieRequestUrl);

            ArrayList<Movie> simpleJsonMovieData = OpenMovieJsonUtils
                    .getMovieFromJson(jsonMovieResponse);

            return simpleJsonMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movieData) {
        super.onPostExecute(movieData);
        listener.onTaskComplete(movieData);
    }
}
