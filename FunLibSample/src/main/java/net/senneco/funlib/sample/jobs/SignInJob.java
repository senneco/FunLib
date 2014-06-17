package net.senneco.funlib.sample.jobs;

import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.app.GithubApi;

/**
 * Created by senneco on 17.06.2014
 */
public class SignInJob extends FunJob<Void> {

    private String mToken;

    public SignInJob(String token) {
        mToken = token;
    }

    @Override
    public Void doJob() throws Throwable {

        ((GithubApi) getApi()).signIn(mToken);

        return null;
    }
}
