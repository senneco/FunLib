package net.senneco.funlib.sample.data;

import java.util.Date;

/**
 * Created by senneco on 31.05.2014
 */
public class Repository {
    private int mId;
    private String mName;
    private String mUrl;
    private User mOwner;
    private Date mPushedAt;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public User getOwner() {
        return mOwner;
    }

    public void setOwner(User owner) {
        mOwner = owner;
    }

    public Date getPushedAt() {
        return mPushedAt;
    }

    public void setPushedAt(Date pushedAt) {
        mPushedAt = pushedAt;
    }
}
