package net.senneco.funlib.sample.ui.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import net.senneco.funlib.loaders.LoaderResult;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.jobs.SearchJob;
import net.senneco.funlib.sample.loaders.RepositoriesLoader;
import net.senneco.funlib.sample.ui.adapters.SearchAdapter;
import net.senneco.funlib.ui.FunActivity;


public class MainActivity extends FunActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks {

    private EditText mSearchEdit;
    private SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mSearchAdapter = new SearchAdapter(this);

        mSearchEdit = (EditText) findViewById(R.id.edit_search);

        ListView resultsList = (ListView) findViewById(R.id.list_results);
        resultsList.setAdapter(mSearchAdapter);

        findViewById(R.id.butt_search).setOnClickListener(this);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butt_search:
                startJob(new SearchJob(1, mSearchEdit.getText().toString()));
                break;
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new RepositoriesLoader(this);
    }

    @Override
    public void onLoadFinished (Loader loader,  Object result){
         mSearchAdapter.changeCursor((Cursor) ((LoaderResult) result).getData());
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mSearchAdapter.swapCursor(null);
    }
}
