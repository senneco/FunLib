package net.senneco.funlib.app;

/**
 * Created by senneco on 29.05.2014
 */
public class FunApiProvider<T> {
    private T mApi;

    public FunApiProvider(T api) {
        mApi = api;
    }

    public T getApi() {
        return mApi;
    }
}
