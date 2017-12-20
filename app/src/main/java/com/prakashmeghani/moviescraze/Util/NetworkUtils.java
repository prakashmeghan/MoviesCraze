package com.prakashmeghani.moviescraze.Util;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    public static URL buildUrl(int urlType) {
        String buildUrl = "";
        if (urlType == 0)
            buildUrl = Constant.URL_POPULAR;
        else
            buildUrl = Constant.URL_TOP_RATED;

        Uri builtUri = Uri.parse(buildUrl).buildUpon()
                .appendQueryParameter(Constant.QUERY_PARAM_API_KEY, Constant.API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}