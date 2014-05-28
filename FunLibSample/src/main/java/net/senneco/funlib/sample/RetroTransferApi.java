package net.senneco.funlib.sample;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by senneco on 22.05.2014
 */
public interface RetroTransferApi {
    @GET("/auth")
    void signIn(@Header("Authorization") String authorization, Callback<?> callback);

    @GET("/auth")
    Object signIn(@Header("Authorization1") String authorization);
}
