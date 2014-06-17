package net.senneco.funlib.sample.app;

import net.senneco.funlib.sample.data.gson.SearchResult;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by senneco on 22.05.2014
 */
public interface GithubApi {

    @GET("/")
    Void signIn(@Header("Authorization") String token);

    @GET("/search/repositories?sort=stars&order=desc")
    SearchResult search(@Query("q") String query);
}
