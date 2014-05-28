package net.senneco.funlib.sample;

import net.senneco.funlib.app.FunApp;
import retrofit.RestAdapter;

/**
 * Created by senneco on 29.05.2014
 */
public class MainApp extends FunApp {

    protected RestAdapter initRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint("http://s.itransfers.ru/api")
                .build();
    }

    @Override
    protected Class getApiClass() {
        return RetroTransferApi.class;
    }
}
