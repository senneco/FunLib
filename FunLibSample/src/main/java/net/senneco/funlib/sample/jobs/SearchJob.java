package net.senneco.funlib.sample.jobs;

import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.app.GithubApi;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.gson.SearchResult;

import java.util.List;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchJob extends FunJob<List<Repository>> {
    private String mQuery;

    public SearchJob(int id, String query) {
        super(id);
        mQuery = query;
    }

    @Override
    public List<Repository> doJob() throws Throwable {
        SearchResult searchResult = ((GithubApi) getApi()).search(mQuery);
        return searchResult.getRepositories();
    }
}
