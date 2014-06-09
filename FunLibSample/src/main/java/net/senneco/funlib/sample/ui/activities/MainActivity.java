package net.senneco.funlib.sample.ui.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import net.senneco.funlib.loaders.FunLoader;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.jobs.SearchJob;
import net.senneco.funlib.sample.loaders.RepositoriesLoader;
import net.senneco.funlib.sample.ui.adapters.SearchAdapter;
import net.senneco.funlib.ui.FunActivity;


public class MainActivity extends FunActivity implements View.OnClickListener {

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

        initLoader(0, new RepositoriesLoader(this), new FunLoader.LoaderListener<Cursor>() {
            @Override
            public void onLoaderComplete(int loaderId, Cursor result) {
                mSearchAdapter.changeCursor(result);
            }

            @Override
            public void onLoaderFail(int loaderId, Exception exception) {
                // pass
            }

            @Override
            public void onLoaderReset(int loaderId) {
                mSearchAdapter.swapCursor(null);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butt_search:
                //noinspection ConstantConditions
                startJob(new SearchJob(1, mSearchEdit.getText().toString()));
                break;
        }
    }
}