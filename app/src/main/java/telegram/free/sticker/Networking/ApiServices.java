package telegram.free.sticker.Networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import telegram.free.sticker.Networking.Model.ResponseData;

public interface ApiServices {
    @GET("stickerapi.json")
    Call<List<ResponseData>> getSteaker();
}
