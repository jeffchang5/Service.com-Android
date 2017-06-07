package jeffreychang.xyz.servicecom_android_challenge.network;

import java.util.List;

import jeffreychang.xyz.servicecom_android_challenge.models.Photo;
import jeffreychang.xyz.servicecom_android_challenge.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by chang on 6/6/2017.
 */

interface UserService {

    @FormUrlEncoded
    @POST("login")
    Call<User> loginWithUserInformation(@Field("email") String username);

    @GET("all")
    Call<List<User>> getAllUserInformation();

    @GET("photos")
    Call<Photo> getPhotoResource(@Query("id") int id);

    @GET
    Call<Photo> fetchPhotoImage(@Url String url);

}
