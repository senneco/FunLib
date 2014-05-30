package net.senneco.funlib.sample.app;

import net.senneco.funlib.sample.data.gson.SearchResult;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by senneco on 22.05.2014
 */
public interface GithubApi {
    @GET("/search/repositories?sort=stars&order=desc")
    SearchResult search(@Query("q") String query);
}
