package net.senneco.funlib.sample.data.gson;

import net.senneco.funlib.sample.data.Repository;

import java.util.List;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchResult {
    private int mTotalCount;
    private boolean mIncompleteResults;
    private List<Repository> mItems;

    public List<Repository> getRepositories() {
        return mItems;
    }
}
