package net.senneco.funlib.sample.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.common.AuthUtils;
import net.senneco.funlib.sample.common.UserUtils;
import net.senneco.funlib.sample.data.User;
import net.senneco.funlib.sample.jobs.SignInJob;
import net.senneco.funlib.ui.FunActivity;

public class SignInActivity extends FunActivity implements View.OnClickListener {

    private EditText mLoginEdit;
    private EditText mPasswordEdit;
    private Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = AuthUtils.getToken(this);
        String login = UserUtils.getLogin(this);
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(login)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_sign_in);

        mLoginEdit = (EditText) findViewById(R.id.edit_login);
        mPasswordEdit = (EditText) findViewById(R.id.edit_password);

        mSignInButton = (Button) findViewById(R.id.butt_sign_in);
        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butt_sign_in:
                signIn();
                break;
        }
    }

    private void signIn() {
        //noinspection ConstantConditions
        String login = mLoginEdit.getText().toString();
        //noinspection ConstantConditions
        String password = mPasswordEdit.getText().toString();

        String credentials = String.format("%s:%s", login, password);

        final String token = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);

        startJob(new SignInJob(token), new FunJob.OnJobStateChangeListener<User>() {
            @Override
            public void onJobStart(int jobId) {
                mSignInButton.setEnabled(false);

                AuthUtils.setToken(SignInActivity.this, null);
            }

            @Override
            public void onJobComplete(int jobId, User result) {
                mSignInButton.setEnabled(true);

                Context context = SignInActivity.this;

                AuthUtils.setToken(context, token);
                UserUtils.setUser(context, result);

                startActivity(new Intent(context, HomeActivity.class));
                finish();
            }

            @Override
            public void onJobFail(int jobId, Throwable throwable) {
                mSignInButton.setEnabled(true);

                Toast.makeText(SignInActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
