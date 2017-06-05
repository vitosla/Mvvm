package com.vitos.mvvm.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.R;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.tools.CircleTransform;

import java.util.List;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListAdapter extends BaseAdapter{

    private Context mAppContext;
    private List<User> mUsers;

    public UserListAdapter(List<User> users) {
        mAppContext = MvvmApp.getAppComponent().getContext();
        mUsers = users;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public User getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mAppContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mAppContext)
                .load(getItem(position).getThumb())
                .centerCrop()
                .transform(new CircleTransform())
                .error(R.drawable.detect)
                .fit()
                .into(viewHolder.mImage);

        viewHolder.mId.setText(getItem(position).getId());
        viewHolder.mProvider.setText(getItem(position).getProvider());
        viewHolder.mEmail.setText(getItem(position).getEmail());

        return convertView;
    }

    private class ViewHolder{

        ImageView mImage;
        TextView mId;
        TextView mProvider;
        TextView mEmail;

        ViewHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.iv_image);
            mId = (TextView) view.findViewById(R.id.tv_id);
            mProvider = (TextView) view.findViewById(R.id.tv_provider);
            mEmail = (TextView) view.findViewById(R.id.tv_email);
        }
    }
}
