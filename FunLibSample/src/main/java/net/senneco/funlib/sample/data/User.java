package net.senneco.funlib.sample.data;

/**
 * Created by senneco on 31.05.2014
 */
public class User {
    private int mId;
    private String mLogin;
    private String mAvatarUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mLogin;
    }

    public void setName(String login) {
        mLogin = login;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
