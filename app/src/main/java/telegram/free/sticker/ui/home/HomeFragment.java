package telegram.free.sticker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asha.nightowllib.NightOwl;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telegram.free.sticker.Adapter.StickerAdapter;
import telegram.free.sticker.Networking.Model.ResponseData;
import telegram.free.sticker.Networking.RestCleint;
import telegram.free.sticker.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<ResponseData> list;
    private RecyclerView recyclerView;
    private AdView adView;
    private ShimmerFrameLayout shimmerFrameLayout;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NightOwl.owlBeforeCreate(getActivity());
        root = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        bannerAdd();
        callApi();
        return root;
    }

    private void bannerAdd() {
        AdSettings.addTestDevice("75d088ac-cb84-46c6-afd5-abe4445c7ed1");
        adView = new AdView(getActivity(), getString(R.string.banner_id_fb), AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout)root.findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
    }

    private void callApi() {
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        RestCleint.getInstance().Api().getSteaker().enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, Response<List<ResponseData>> response) {
                shimmerFrameLayout.setVisibility(View.GONE);
                list.addAll(response.body());
                setAdapter();
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {

            }
        });
    }

    private void init() {
        NightOwl.owlAfterCreate(getActivity());
        recyclerView = root.findViewById(R.id.recycle_stickers);
        shimmerFrameLayout=root.findViewById(R.id.shimmer_view_container);
        list = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        NightOwl.owlResume(getActivity());
    }

    public String getAssetJsonData() throws JSONException {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("res.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("stickers");
        Log.e("data", json);
        for (int i = 0; i < jsonArray.length(); i++) {
//            ResponceData data = new ResponceData();
//            JSONObject obj = jsonArray.getJSONObject(i);
//            data.setName(obj.getString("name"));
//            data.setUrl(obj.getString("url"));
//            data.setImageurl(obj.getString("imageurl"));
//            data.setId(obj.getInt("id"));
//            list.add(data);
            setAdapter();

        }

        return json;

    }
    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StickerAdapter adapter = new StickerAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}