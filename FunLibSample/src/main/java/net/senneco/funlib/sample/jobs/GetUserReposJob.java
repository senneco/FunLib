package net.senneco.funlib.sample.jobs;

import com.j256.ormlite.dao.Dao;
import net.senneco.funlib.jobs.FunJob;
import net.senneco.funlib.sample.app.GithubApi;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.Uris;
import net.senneco.funlib.sample.data.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by senneco on 19.06.2014
 */
public class GetUserReposJob extends FunJob<List<Repository>> {

    private String mLogin;

    public GetUserReposJob(String login) {
        super(Uris.REPOSITORIES);
        mLogin = login;
    }

    @Override
    public List<Repository> doJob() throws Throwable {
        List<Repository> repos = ((GithubApi) getApi()).getUserRepos(mLogin);

        saveRepositories(repos);

        return repos;
    }

    private void saveRepositories(List<Repository> repositories) throws SQLException {
        // init DAOs
        Dao<User, Integer> userDao = getDbHelper().getDao(User.class);
        Dao<Repository, ?> repositoryDao = getDbHelper().getDao(Repository.class);

        List<User> users = userDao.queryForEq(User.Column.LOGIN, mLogin);

        // Clear user history
        if (!users.isEmpty()) {
            User user = users.get(0);

            userDao.deleteById(user.getId());
            repositoryDao.deleteBuilder().where().eq(Repository.Column.OWNER, user.getId()).prepare();
        }

        // Save search results
        for (Repository repository : repositories) {
            userDao.createIfNotExists(repository.getOwner());
            repositoryDao.createIfNotExists(repository);
        }
    }
}
