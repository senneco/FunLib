package net.senneco.funlib.sample.app;

import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.User;
import net.senneco.funlib.sample.data.gson.SearchResult;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by senneco on 22.05.2014
 */
public interface GithubApi {

    @GET("/user")
    User signIn(@Header("Authorization") String token);

    @GET("/search/repositories?sort=stars&order=desc")
    SearchResult search(@Query("q") String query);

    @GET("/users/{login}/repos")
    List<Repository> getUserRepos(@Path("login") String login);
}
