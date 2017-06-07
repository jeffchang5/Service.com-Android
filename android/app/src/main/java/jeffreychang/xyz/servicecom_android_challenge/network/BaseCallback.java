package jeffreychang.xyz.servicecom_android_challenge.network;

/**
 * Created by jeffreychang on 6/5/17.
 */

public interface BaseCallback<T> {
    abstract void success(T response);
    abstract void failure(Throwable error);
}
