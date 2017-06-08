package jeffreychang.xyz.servicecom_android_challenge.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import jeffreychang.xyz.servicecom_android_challenge.models.Photo;
import jeffreychang.xyz.servicecom_android_challenge.models.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeffreychang on 6/5/17.
 */

public class RestClient {
    // 54.174.101.197
    private final String BASE_URL = "http://ec2-54-174-101-197.compute-1.amazonaws.com:8080/users/";
    private final UserService mUserService;

    private RestClient() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient client= new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new StethoInterceptor())
                .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mUserService = retrofit.create(UserService.class);

    }
    public static RestClient getInstance() {
        return new RestClient();
    }

    public void login(String username, final BaseCallback<User> callback) {
        mUserService.loginWithUserInformation(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) { callback.success(response.body()); }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        callback.failure(t);
                    }
                });
    }
    public void getAllUsers(final BaseCallback<List<User>> callback) {
        mUserService.getAllUserInformation()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) { callback.success(response.body()); }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) { callback.failure(t); }
                });

    }
}