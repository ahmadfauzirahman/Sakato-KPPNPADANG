package com.ahmadfauzirahman.sakato.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.ahmadfauzirahman.sakato.FCMService;
import com.ahmadfauzirahman.sakato.model.UserModel;

import java.util.HashMap;

/**
 * Created by ahmad fauzi rahman to kppn padang
 */

public class
SessionManager {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private Context _context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String penID = "penID";
    public static final String penNama = "penNama";
    public static final String penUsername = "penUsername";
    public static final String penPassword = "penPassword";
    public static final String penLvlAkses = "penLvlAkses";
    public static final String penToken = "penToken";


    public Context get_context() {
        return _context;
    }

    //constuctor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void saveToken(String fcm) {
        editor.putString(penToken, fcm);
        editor.commit();
    }

    public void createLoginSession(UserModel user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(penID, user.getPenID());
        editor.putString(penNama, user.getPenNama());
        editor.putString(penUsername, user.getPenUsername());
        editor.putString(penLvlAkses, user.getPenLvlAkses());
        editor.putString(penPassword, user.getPenPassword());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(penID, sharedPreferences.getString(penID, null));
        user.put(penNama, sharedPreferences.getString(penNama, null));
        user.put(penUsername, sharedPreferences.getString(penUsername, null));
        user.put(penPassword, sharedPreferences.getString(penPassword, null));
        user.put(penLvlAkses, sharedPreferences.getString(penLvlAkses, null));
        return user;
    }

    public String getToken() {
        return sharedPreferences.getString(penToken, "");
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.putString(penID, "");
        editor.putString(penNama, "");
        editor.putString(penUsername, "");
        editor.putString(penLvlAkses, "");
        editor.putString(penPassword, "");
//        editor.commit();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
