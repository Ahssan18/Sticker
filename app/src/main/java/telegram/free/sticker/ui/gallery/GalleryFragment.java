package telegram.free.sticker.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.asha.nightowllib.NightOwl;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import telegram.free.sticker.PrefrenceManager;
import telegram.free.sticker.R;

public class GalleryFragment extends Fragment {

    View root;
    private AdView adView;
    Switch theme;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        init();
        bannerAdd();
        clickListener();
        return root;
    }

    private void bannerAdd() {
        AdSettings.addTestDevice("75d088ac-cb84-46c6-afd5-abe4445c7ed1");
        adView = new AdView(getActivity(), getString(R.string.banner_id_fb), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) root.findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
    }

    private void clickListener() {
        theme.setChecked(PrefrenceManager.getInstance(getActivity()).getTheme());

        theme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            NightOwl.owlNewDress(getActivity());
            if (isChecked) {
                PrefrenceManager.getInstance(getActivity()).sateTheme(true);
                getActivity().recreate();
            } else {
                getActivity().recreate();
                PrefrenceManager.getInstance(getActivity()).sateTheme(false);
            }
        });
    }

    private void init() {
        final TextView textView = root.findViewById(R.id.text_gallery);
        theme = (Switch) root.findViewById(R.id.switch1);

    }
}