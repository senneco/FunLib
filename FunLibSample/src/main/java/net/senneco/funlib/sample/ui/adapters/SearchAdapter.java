package net.senneco.funlib.sample.ui.adapters;

import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import net.senneco.funlib.sample.R;
import net.senneco.funlib.sample.data.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senneco on 31.05.2014
 */
public class SearchAdapter extends BaseAdapter {

    private List<Repository> mRepositories = new ArrayList<Repository>();

    public void setData(List<Repository> repositories) {
        mRepositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRepositories.size();
    }

    @Override
    public Repository getItem(int position) {
        return mRepositories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepositoryHolder holder;

        if (convertView == null) {
            //noinspection ConstantConditions
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);

            holder = new RepositoryHolder();

            holder.avatarImage = (ImageView) convertView.findViewById(R.id.image_avatar);
            holder.ownerText = (TextView) convertView.findViewById(R.id.text_owner);
            holder.nameText = (TextView) convertView.findViewById(R.id.text_name);
            holder.pushedAtText = (TextView) convertView.findViewById(R.id.text_pushed_at);

            convertView.setTag(holder);
        } else {
            holder = (RepositoryHolder) convertView.getTag();
        }

        Repository repository = getItem(position);

        Time pushedAt = new Time();
        pushedAt.set(repository.getPushedAt().getTime());

        holder.ownerText.setText(repository.getOwner().getName());
        holder.nameText.setText(repository.getName());
        holder.pushedAtText.setText(pushedAt.format("%d.%m.%y at %R"));
        Picasso.with(convertView.getContext())
                .load(repository.getOwner().getAvatarUrl())
                .fit()
                .into(holder.avatarImage);

        return convertView;
    }

    private class RepositoryHolder {
        ImageView avatarImage;
        TextView ownerText;
        TextView nameText;
        TextView pushedAtText;
    }
}
