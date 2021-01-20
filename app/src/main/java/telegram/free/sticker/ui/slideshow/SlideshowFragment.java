package telegram.free.sticker.ui.slideshow;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import telegram.free.sticker.PrefrenceManager;
import telegram.free.sticker.R;

public class SlideshowFragment extends Fragment {

    private Button rateapp;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
       init();
       clickListerner();
        return root;
    }

    private void clickListerner() {
        rateapp.setOnClickListener(v -> {
            try {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(rateIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void init() {
        rateapp=root.findViewById(R.id.btn_rate);
        if(PrefrenceManager.getInstance(getActivity()).getTheme())
        {
            rateapp.setBackgroundColor(this.getResources().getColor(R.color.night_purple_200));
            rateapp.setTextColor(Color.WHITE);
        }else
        {
            rateapp.setBackgroundColor(this.getResources().getColor(R.color.purple_200));
            rateapp.setTextColor(Color.WHITE);
        }
    }
}