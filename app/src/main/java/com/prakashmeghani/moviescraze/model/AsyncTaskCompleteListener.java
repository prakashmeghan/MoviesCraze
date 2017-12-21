package com.prakashmeghani.moviescraze.model;

/**
 * Created by prakash.meghani on 12/21/2017.
 */

public interface AsyncTaskCompleteListener<T> {
    public void onTaskComplete(T result);
}
