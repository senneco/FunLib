package net.senneco.funlib.sample.ui.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.senneco.funlib.sample.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RepositoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RepositoryFragment extends Fragment {
    private static final String ARG_REPO_ID = "repo_id";

    private int mRepoId;

    public static RepositoryFragment newInstance(int repoId) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_REPO_ID, repoId);
        fragment.setArguments(args);
        return fragment;
    }

    public RepositoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mRepoId = getArguments().getInt(ARG_REPO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repository, container, false);
    }


}
