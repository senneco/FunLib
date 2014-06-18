package net.senneco.funlib.sample.ui.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import net.senneco.funlib.loaders.FunLoader;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.common.UserUtils;
import net.senneco.funlib.sample.jobs.GetUserReposJob;
import net.senneco.funlib.sample.loaders.RepositoriesLoader;
import net.senneco.funlib.sample.ui.adapters.RepositoriesAdapter;
import net.senneco.funlib.ui.FunFragment;

public class RepositoriesFragment extends FunFragment implements AdapterView.OnItemClickListener {

    private static final String ARG_LOGIN = "login";

    private String mLogin;
    private OnRepositorySelectListener mListener;
    private RepositoriesAdapter mReposAdapter;

    public static RepositoriesFragment newInstance(String login) {
        RepositoriesFragment fragment = new RepositoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOGIN, login);
        fragment.setArguments(args);
        return fragment;
    }

    public RepositoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mLogin = getArguments().getString(ARG_LOGIN);

        }

        if (TextUtils.isEmpty(mLogin)) {
            mLogin = UserUtils.getLogin(getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repositories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mReposAdapter = new RepositoriesAdapter(getActivity());

        ListView resultsList = (ListView) view.findViewById(R.id.list_repos);
        resultsList.setAdapter(mReposAdapter);
        resultsList.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLoader(new RepositoriesLoader(getActivity()).forLogin(mLogin), new FunLoader.LoaderListener<Cursor>() {
            @Override
            public void onLoaderComplete(int loaderId, Cursor result) {
                mReposAdapter.changeCursor(result);
            }

            @Override
            public void onLoaderFail(int loaderId, Exception exception) {
                // pass
            }

            @Override
            public void onLoaderReset(int loaderId) {
                mReposAdapter.swapCursor(null);
            }
        });

        startJob(new GetUserReposJob(mLogin));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRepositorySelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onRepositorySelect(id);
        }
    }

    public interface OnRepositorySelectListener {
        public void onRepositorySelect(long repositoryId);
    }

}
