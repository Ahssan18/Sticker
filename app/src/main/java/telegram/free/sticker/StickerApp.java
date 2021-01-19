package telegram.free.sticker;

import android.app.Application;

import com.asha.nightowllib.NightOwl;

public class StickerApp extends Application {
    @Override
    public void onCreate() {
        NightOwl.builder().defaultMode(0).create();
        super.onCreate();
    }
}
