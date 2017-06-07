package jeffreychang.xyz.servicecom_android_challenge.network;

import jeffreychang.xyz.servicecom_android_challenge.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface LoginService {
    @FormUrlEncoded
    @POST("login")
    Call<User> loginWithUserInformation(@Field("email") String username);

}
