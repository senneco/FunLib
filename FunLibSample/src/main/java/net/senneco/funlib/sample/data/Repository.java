package net.senneco.funlib.sample.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by senneco on 31.05.2014
 */
@DatabaseTable
public class Repository {

    public static class Column {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String URL = "url";
        public static final String OWNER = "owner";
        public static final String PUSHED_AT = "pushed_at";
    }

    @DatabaseField(columnName = Column.ID, id = true)
    private int mId;
    @DatabaseField(columnName = Column.NAME)
    private String mName;
    @DatabaseField(columnName = Column.URL)
    private String mUrl;
    @DatabaseField(columnName = Column.OWNER, foreign = true)
    private User mOwner;
    @DatabaseField(columnName = Column.PUSHED_AT, dataType = DataType.DATE)
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
