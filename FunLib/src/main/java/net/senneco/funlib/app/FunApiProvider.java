package net.senneco.funlib.app;

/**
 * Created by senneco on 29.05.2014
 */
public class FunApiProvider {
    private Object mApi;

    public FunApiProvider(Object api) {
        mApi = api;
    }

    public Object getApi() {
        return mApi;
    }
}
