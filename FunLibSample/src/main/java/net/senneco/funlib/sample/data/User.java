package net.senneco.funlib.sample.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by senneco on 31.05.2014
 */
@DatabaseTable
public class User {

    public static class Column {
        public static final String ID = "_id";
        public static final String LOGIN = "login";
        public static final String AVATAR = "avatar";
    }

    @DatabaseField(columnName = Column.ID, id = true)
    private int mId;
    @DatabaseField(columnName = Column.LOGIN)
    private String mLogin;
    @DatabaseField(columnName = Column.AVATAR)
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
