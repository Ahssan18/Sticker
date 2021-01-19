package telegram.free.sticker;

import android.content.Context;
import android.content.SharedPreferences;

public  class PrefrenceManager {
    private SharedPreferences sharedPreferences;
    private static PrefrenceManager prefrenceManager;

    public static PrefrenceManager getInstance(Context context) {
        if (prefrenceManager == null) {
            prefrenceManager = new PrefrenceManager(context);
        }
        return prefrenceManager;
    }

    private PrefrenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences("sticker", Context.MODE_PRIVATE);
    }

    public void sateTheme(boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean("theme", value);
        prefsEditor.apply();
    }

    public boolean getTheme() {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("theme", false);
        }
        return false;
    }
}
