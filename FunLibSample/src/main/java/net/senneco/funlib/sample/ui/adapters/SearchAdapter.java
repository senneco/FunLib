package net.senneco.funlib.sample.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.data.Repository;
import net.senneco.funlib.sample.data.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchAdapter extends CursorAdapter {

    private int mOwnerIndex;
    private int mNameIndex;
    private int mPushedAtIndex;
    private int mAvaterIndex;

    private SimpleDateFormat mDateFormat;

    public SearchAdapter(Context context) {
        super(context, null, false);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void changeCursor(Cursor newCursor) {

        if (newCursor != null) {
            mOwnerIndex = newCursor.getColumnIndex(User.Column.LOGIN);
            mNameIndex = newCursor.getColumnIndex(Repository.Column.NAME);
            mPushedAtIndex = newCursor.getColumnIndex(Repository.Column.PUSHED_AT);
            mAvaterIndex = newCursor.getColumnIndex(User.Column.AVATAR);
        }
        super.changeCursor(newCursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);

        RepositoryHolder holder = new RepositoryHolder();

        holder.avatarImage = (ImageView) view.findViewById(R.id.image_avatar);
        holder.ownerText = (TextView) view.findViewById(R.id.text_owner);
        holder.nameText = (TextView) view.findViewById(R.id.text_name);
        holder.pushedAtText = (TextView) view.findViewById(R.id.text_pushed_at);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        RepositoryHolder holder = (RepositoryHolder) view.getTag();

        Time pushedAtTime = new Time();
        try {
            String pushedAt = cursor.getString(mPushedAtIndex);
            Date pushedAtDate = mDateFormat.parse(pushedAt);
            pushedAtTime.set(pushedAtDate.getTime());
        } catch (ParseException e) {
            // pass
        }

        holder.ownerText.setText(cursor.getString(mOwnerIndex));
        holder.nameText.setText(cursor.getString(mNameIndex));
        holder.pushedAtText.setText(pushedAtTime.format("%d.%m.%y at %R"));

        Picasso.with(context)
                .load(cursor.getString(mAvaterIndex))
                .fit()
                .into(holder.avatarImage);

    }

    private class RepositoryHolder {
        ImageView avatarImage;
        TextView ownerText;
        TextView nameText;
        TextView pushedAtText;
    }
}
