package jeffreychang.xyz.servicecom_android_challenge.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jeffreychang.xyz.servicecom_android_challenge.MainActivity;
import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;
import jeffreychang.xyz.servicecom_android_challenge.network.BaseCallback;
import jeffreychang.xyz.servicecom_android_challenge.network.RestClient;
import jeffreychang.xyz.servicecom_android_challenge.ui.common.BaseActivity;

/**
 * Created by jeffreychang on 6/5/17.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private TextInputLayout mUsernameTextInputLayout;
    private TextInputLayout mPasswordTextInputLayout;

    private Button mSignInButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEditText = (EditText) findViewById(R.id.username);
        mPasswordEditText = (EditText) findViewById(R.id.password);

        mUsernameTextInputLayout = (TextInputLayout) findViewById(R.id.usernameTextInputLayout);
        mPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);

        mSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mSignInButton.setOnClickListener(this);
        mUsernameEditText.setOnFocusChangeListener(this);
        mPasswordEditText.setOnFocusChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                attemptLogin();
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mUsernameTextInputLayout.setError(null);
        mPasswordTextInputLayout.setError(null);
    }


    private void attemptLogin() {
        if (isEmailValid(mUsernameEditText.getText().toString()) && isPasswordValid(mPasswordEditText.getText().toString())) {
            RestClient.getInstance().login(mUsernameEditText.getText().toString(), new BaseCallback<User>() {
                @Override
                public void success(User response) {
                    storeUser(response);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                @Override
                public void failure(Throwable error) {
                    Toast.makeText(getApplicationContext(), "Login credentials failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private Boolean isEmailValid(String email) {
        if (email.contains("@")) return true;
        mUsernameTextInputLayout.setError(getString(R.string.error_textinputlayout_email));
        return false;
    }

    private Boolean isPasswordValid(String password) {
        if (password.length() > 5) return true;
        mPasswordTextInputLayout.setError(getString(R.string.error_textinputlayout_password));
        return false;
    }
}
