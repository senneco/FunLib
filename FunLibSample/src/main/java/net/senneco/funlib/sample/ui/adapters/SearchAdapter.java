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
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.data.Repository;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchAdapter extends CursorAdapter {

    private int mNameIndex;
    private int mPushedAtIndex;

    public SearchAdapter(Context context) {
        super(context, null, false);
    }

    @Override
    public void changeCursor(Cursor newCursor) {

        if (newCursor != null) {
            mNameIndex = newCursor.getColumnIndex(Repository.Column.NAME);
            mPushedAtIndex = newCursor.getColumnIndex(Repository.Column.PUSHED_AT);
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

        Time pushedAt = new Time();
        pushedAt.set(cursor.getLong(mPushedAtIndex));

        holder.nameText.setText(cursor.getString(mNameIndex));
        holder.pushedAtText.setText(pushedAt.format("%d.%m.%y at %R"));
    }

    private class RepositoryHolder {
        ImageView avatarImage;
        TextView ownerText;
        TextView nameText;
        TextView pushedAtText;
    }
}
