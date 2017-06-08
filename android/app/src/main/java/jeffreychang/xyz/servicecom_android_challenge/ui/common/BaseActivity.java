package jeffreychang.xyz.servicecom_android_challenge.ui.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import jeffreychang.xyz.servicecom_android_challenge.models.User;
import jeffreychang.xyz.servicecom_android_challenge.ui.login.LoginActivity;

/**
 * Created by jeffreychang on 6/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void checkUser() {
        Toast.makeText(getApplicationContext(), mPrefs.getString(User.EMAIL, "address"), Toast.LENGTH_SHORT).show();

        if (mPrefs.contains(User.EMAIL)) {
            Toast.makeText(getApplicationContext(), "Already logged in!", Toast.LENGTH_SHORT).show();
        } else {
            goToLoginActivity();
        }
    }
    public void storeUser(User user) {

        mPrefs.edit()
                .putInt(User.ID, user.getId())
                .putString(User.NAME, user.getName())
                .putString(User.EMAIL, user.getEmail())
                .putString(User.PHONE, user.getPhone())
                .putString(User.ADDRESS, user.getAddress().toString())
                .putString(User.ROLE, user.getRole())
                .apply();
    }
    public void clearUser () {

        mPrefs.edit().clear().apply();
        goToLoginActivity();

    }
    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @TargetApi(21)
    public void changeColorTheme(int id) {
        int color = ContextCompat.getColor(this, id);
        assert getSupportActionBar() != null;
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setStatusBarColor(color);
        }
    }


}
