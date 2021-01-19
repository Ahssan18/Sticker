package telegram.free.sticker.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestCleint {
    public static Retrofit retrofit = null;
    public static RestCleint restCleint = null;

    public static RestCleint getInstance() {
        if (restCleint == null) {
            restCleint = new RestCleint();
        }
        return restCleint;
    }

    public ApiServices Api() {
        ApiServices service = retrofit.create(ApiServices.class);
        return service;
    }


    private RestCleint() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://smappinc.github.io/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
