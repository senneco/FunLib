package net.senneco.funlib.sample.jobs;

import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.app.GithubApi;
import net.senneco.funlib.sample.data.User;

/**
 * Created by senneco on 17.06.2014
 */
public class SignInJob extends FunJob<User> {

    private String mToken;

    public SignInJob(String token) {
        mToken = token;
    }

    @Override
    public User doJob() throws Throwable {

        return ((GithubApi) getApi()).signIn(mToken);
    }
}
