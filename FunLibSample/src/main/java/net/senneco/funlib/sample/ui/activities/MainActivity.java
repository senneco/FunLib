package net.senneco.funlib.sample.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.jobs.SearchJob;
import net.senneco.funlib.sample.ui.adapters.SearchAdapter;
import net.senneco.funlib.ui.FunActivity;

import java.util.List;


public class MainActivity extends FunActivity implements View.OnClickListener {

    private EditText mSearchEdit;
    private SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mSearchAdapter = new SearchAdapter();

        mSearchEdit = (EditText) findViewById(R.id.edit_search);

        ListView resultsList = (ListView) findViewById(R.id.list_results);
        resultsList.setAdapter(mSearchAdapter);

        findViewById(R.id.butt_search).setOnClickListener(this);
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
    public void onComplete(FunJob job, Object result) {
        super.onComplete(job, result);

        if (job.getId() == 1) {
            mSearchAdapter.setData((List<Repository>) result);
        }
    }
}
