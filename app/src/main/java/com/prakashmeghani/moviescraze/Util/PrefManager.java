package com.prakashmeghani.moviescraze.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by prakash.meghani on 12/11/2017.
 */

public class PrefManager {
    private static final String TAG = "PrefsManager";
    // Shared Preferences reference
    private SharedPreferences mPreference;
    // Editor for Shared preferences
    private SharedPreferences.Editor mEditor;
    // Context
    private Context _context;
    // Sharedpref file name

    // Constructor
    public PrefManager(Context context) {
        try {
            this._context = context;
            mPreference = _context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
            mEditor = mPreference.edit();
        } catch (Exception e) {
            Log.i(TAG, "Exception: " + e.getMessage());
            e.printStackTrace();
        }

    }


    public void setUrlType(int urlType) {
        mEditor
                .putInt(Constant.PREF_URL_TYPE, urlType).apply();
        mEditor.commit();
    }

    public int getUrlType() {
        int urlType = mPreference.getInt(Constant.PREF_URL_TYPE, Constant.URL_TYPE_POPULAR);
        return urlType;
    }
}
