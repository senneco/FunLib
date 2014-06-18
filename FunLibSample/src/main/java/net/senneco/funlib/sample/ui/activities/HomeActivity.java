package net.senneco.funlib.sample.ui.activities;

import android.os.Bundle;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.ui.fragments.RepositoriesFragment;
import net.senneco.funlib.ui.FunActivity;


public class HomeActivity extends FunActivity implements RepositoriesFragment.OnRepositorySelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            RepositoriesFragment firstFragment = new RepositoriesFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit();
        }
    }

    @Override
    public void onRepositorySelect(long repositoryId) {

    }
}