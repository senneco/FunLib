package net.senneco.funlib.sample.jobs;

import com.j256.ormlite.dao.Dao;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.app.GithubApi;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.Uris;
import net.senneco.funlib.sample.data.User;
import net.senneco.funlib.sample.data.gson.SearchResult;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchJob extends FunJob<List<Repository>> {
    private String mQuery;

    public SearchJob(int id, String query) {
        super(id, Uris.REPOSITORIES);
        mQuery = query;
    }

    @Override
    public List<Repository> doJob() throws Throwable {
        SearchResult searchResult = ((GithubApi) getApi()).search(mQuery);

        List<Repository> repositories = searchResult.getRepositories();

        saveRepositories(repositories);

        return repositories;
    }

    private void saveRepositories(List<Repository> repositories) throws SQLException {
        Dao<User, ?> userDao = getDbHelper().getDao(User.class);
        Dao<Repository, ?> repositoryDao = getDbHelper().getDao(Repository.class);

        userDao.deleteBuilder().delete();
        repositoryDao.deleteBuilder().delete();

        for (Repository repository : repositories) {
            userDao.createOrUpdate(repository.getOwner());
            repositoryDao.createOrUpdate(repository);
        }
    }
}
